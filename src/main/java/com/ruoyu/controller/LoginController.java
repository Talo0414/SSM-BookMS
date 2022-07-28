package com.ruoyu.controller;

import com.ruoyu.bean.Admin;
import com.ruoyu.bean.ReaderCard;
import com.ruoyu.service.AdminService;
import com.ruoyu.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    @Qualifier("adminServiceImpl")
    private AdminService adminService;

    @Autowired
    @Qualifier("readerCardServiceImpl")
    private ReaderCardService readerCardService;

    @RequestMapping(value = {"/", "/login"})
    public String toLogin(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("/logout")
    public String toLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String pwd = request.getParameter("passwd");
        Map<String, Object> map = new HashMap<>();
        map.put("adminId", id);
        map.put("readerId", id);
        map.put("password", pwd);
        HashMap<String, String> res = new HashMap<>();
        if (adminService.getMatchCount(map) == 1) {
            Admin admin = adminService.getAdmin(id);
            request.getSession().setAttribute("admin", admin);
            res.put("stateCode", "1");
            res.put("msg", "管理员登陆成功！");
        } else if (readerCardService.getIdMatchCount(map) > 0) {
            ReaderCard readerCard = readerCardService.findReaderByReaderId(id);
            request.getSession().setAttribute("readercard", readerCard);
            res.put("stateCode", "2");
            res.put("msg", "读者登陆成功！");
        }else {
            res.put("stateCode", "0");
            res.put("msg", "账号或密码错误！");
        }
        return res;
    }

    @RequestMapping("/admin_main")
    public String toAdminMain(){
        return "admin_main";
    }

    @RequestMapping("/reader_main")
    public String toReaderMain(){
        return "reader_main";
    }

    @RequestMapping("/admin_repasswd")
    public String reAdminPasswd(){
        return "admin_repasswd";
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        long id = admin.getAdminId();
        String password = adminService.getPassword(id);
        Map<String,Object> map = new HashMap<>();
        map.put("adminId",id);
        map.put("password",newPasswd);
        if (password.equals(oldPasswd)) {
            if (adminService.resetPassword(map) > 0) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin_repasswd";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd";
        }
    }

    @RequestMapping("/reader_repasswd")
    public String reReaderPasswd(){
        return "reader_repasswd";
    }

    @RequestMapping("/reader_repasswd_do")
    public String reReaderPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
        long id = reader.getReaderId();
        String password = readerCardService.getPassword(id);
        Map<String,Object> map = new HashMap<>();
        map.put("readerId",id);
        map.put("password",newPasswd);
        if (password.equals(oldPasswd)) {
            if (readerCardService.resetPassword(map) > 0) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/reader_repasswd";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/reader_repasswd";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/reader_repasswd";
        }
    }

    @RequestMapping("*")
    public String notFind() {
        return "404";
    }

}
