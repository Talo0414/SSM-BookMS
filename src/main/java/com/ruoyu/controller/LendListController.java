package com.ruoyu.controller;

import com.ruoyu.bean.LendList;
import com.ruoyu.bean.ReaderCard;
import com.ruoyu.service.BookInfoService;
import com.ruoyu.service.LendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LendListController {
    @Autowired
    @Qualifier("lendListServiceImpl")
    private LendListService lendListService;

    @Autowired
    @Qualifier("bookInfoServiceImpl")
    private BookInfoService bookInfoService;

    @RequestMapping({"/deletebook"})
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        if (this.bookInfoService.deleteBook(bookId) > 0) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
        }

        return "redirect:/admin_books";
    }

    @RequestMapping({"/lendlist"})
    public String  lendList(HttpServletRequest request, Model model) {
        List<LendList> list = lendListService.lendList();
        model.addAttribute("list",list);
        return "admin_lend_list";
    }

    @RequestMapping({"/mylend"})
    public String myLend(HttpServletRequest request,Model model) {
        ReaderCard readerCard = (ReaderCard)request.getSession().getAttribute("readercard");
        List<LendList> list = lendListService.myLendList(readerCard.getReaderId());
        model.addAttribute("list", list);
        return "reader_lend_list";
    }

    @RequestMapping({"/deletelend"})
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));
        if (lendListService.deleteLend(serNum) > 0) {
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }

        return "redirect:/lendlist";
    }

    @RequestMapping({"/lendbook"})
    public String bookLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard)request.getSession().getAttribute("readercard")).getReaderId();
        Map<String,Object> map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("readerId",readerId);
        if (lendListService.lendBookOne(map)>0&&lendListService.lendBookTwo(bookId)>0) {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        }

        return "redirect:/reader_books";
    }

    @RequestMapping({"/returnbook"})
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard)request.getSession().getAttribute("readercard")).getReaderId();
        Map<String,Object> map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("readerId",readerId);
        if (lendListService.returnBookOne(map)>0&&lendListService.returnBookTwo(bookId)>0) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
        }

        return "redirect:/reader_books";
    }

}
