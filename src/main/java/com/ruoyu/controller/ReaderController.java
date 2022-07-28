package com.ruoyu.controller;

import com.ruoyu.bean.ReaderCard;
import com.ruoyu.bean.ReaderInfo;
import com.ruoyu.service.ReaderCardService;
import com.ruoyu.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReaderController {

    @Autowired
    @Qualifier("readerInfoServiceImpl")
    private ReaderInfoService readerInfoService;

    @Autowired
    @Qualifier("readerCardServiceImpl")
    private ReaderCardService readerCardService;

    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    @RequestMapping("/allreaders")
    public String allBooks(Model model) {
        List<ReaderInfo> readers = readerInfoService.getAllReaderInfo();
        model.addAttribute("readers", readers);
        return "admin_readers";
    }

    @RequestMapping("/reader_delete")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        if ((readerInfoService.deleteReaderInfo(readerId)>0) && (readerCardService.deleteReaderCard(readerId)>0)) {
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:/allreaders";
    }

    @RequestMapping("/reader_info")
    public String  toReaderInfo(HttpServletRequest request,Model model) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.findReaderInfoByReaderId(readerCard.getReaderId());
        model.addAttribute("readerinfo", readerInfo);
        return "reader_info";
    }

    @RequestMapping("/reader_edit")
    public String readerInfoEdit(HttpServletRequest request,Model model) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerInfoService.findReaderInfoByReaderId(readerId);
        model.addAttribute("readerInfo", readerInfo);
        return "admin_reader_edit";
    }

    @RequestMapping("/reader_edit_do")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        if ((readerInfoService.editReaderInfo(readerInfo)>0) && (readerInfoService.editReaderCard(readerInfo)>0)) {
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/allreaders";
    }

    @RequestMapping("/reader_add")
    public String readerInfoAdd() {
        return "admin_reader_add";
    }

    @RequestMapping("/reader_add_do")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        long readerId = readerInfoService.addReaderInfo(readerInfo);
        readerInfo.setReaderId(readerId);
        Map<String,Object> map = new HashMap<>();
        map.put("readerInfo",readerInfo);
        map.put("password",password);
        if (readerId > 0 && readerCardService.addReaderCard(map)>0) {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
        }
        return "redirect:/allreaders";
    }

    @RequestMapping("/reader_info_edit")
    public String readerInfoEditReader(HttpServletRequest request,Model model) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.findReaderInfoByReaderId(readerCard.getReaderId());
        model.addAttribute("readerinfo", readerInfo);
        return "reader_info_edit";
    }

    @RequestMapping("/reader_edit_do_r")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        if ((readerInfoService.editReaderInfo(readerInfo)>0) && (readerInfoService.editReaderCard(readerInfo)>0)) {
            ReaderCard readerCardNew = readerCardService.findReaderByReaderId(readerCard.getReaderId());
            request.getSession().setAttribute("readercard", readerCardNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/reader_info";
    }







}
