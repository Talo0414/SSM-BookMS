package com.ruoyu.controller;

import com.ruoyu.bean.BookInfo;
import com.ruoyu.bean.LendList;
import com.ruoyu.bean.ReaderCard;
import com.ruoyu.service.BookInfoService;
import com.ruoyu.service.LendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    @Qualifier("bookInfoServiceImpl")
    private BookInfoService bookInfoService;

    @Autowired
    @Qualifier("lendListServiceImpl")
    private LendListService lendListService;

    private Date getDate(String pubstr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(pubstr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/querybook")
    public String queryBookDo(String searchWord, Model model) {
        if (bookInfoService.matchBook(searchWord) > 0) {
            List<BookInfo> books = bookInfoService.queryBook(searchWord);
            model.addAttribute("books", books);
            return "admin_books";
        } else {
            model.addAttribute("error","没有匹配的图书");
            return "admin_books";
        }
    }

    @RequestMapping("/reader_querybook_do")
    public String readerQueryBookDo(String searchWord,Model model) {
        if (bookInfoService.matchBook(searchWord) > 0) {
            List<BookInfo> books = bookInfoService.queryBook(searchWord);
            model.addAttribute("books", books);
            return "reader_books";
        } else {
            model.addAttribute("error","没有匹配的图书");
            return "reader_books";
        }
    }

    @RequestMapping("/admin_books")
    public String adminBooks(Model model) {
        List<BookInfo> books = bookInfoService.queryAllBook();
        model.addAttribute("books", books);
        return "admin_books";
    }

    @RequestMapping("/book_add")
    public String  addBook() {
        return "admin_book_add";
    }

    @RequestMapping("/book_add_do")
    public String addBookDo(@RequestParam(value = "pubstr") String pubstr, BookInfo book, RedirectAttributes redirectAttributes) {
        book.setPubDate(getDate(pubstr));
        if (bookInfoService.addBook(book) > 0) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books";
    }

    @RequestMapping("/updatebook")
    public String bookEdit(HttpServletRequest request,Model model) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        BookInfo book = bookInfoService.getBook(bookId);
        model.addAttribute("detail", book);
        return "admin_book_edit";
    }

    @RequestMapping("/book_edit_do")
    public String bookEditDo(@RequestParam(value = "pubstr") String pubstr, BookInfo book, RedirectAttributes redirectAttributes) {
        book.setPubDate(getDate(pubstr));
        if (bookInfoService.addBook(book) > 0) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books";
    }

    @RequestMapping("/admin_book_detail")
    public String  adminBookDetail(HttpServletRequest request,Model model) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        BookInfo book = bookInfoService.getBook(bookId);
        model.addAttribute("detail", book);
        return "admin_book_detail";
    }

    @RequestMapping("/reader_book_detail")
    public String readerBookDetail(HttpServletRequest request,Model model) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        BookInfo book = bookInfoService.getBook(bookId);
        model.addAttribute("detail", book);
        return "reader_book_detail";
    }

    @RequestMapping("/admin_header")
    public String admin_header() {
        return "admin_header";
    }

    @RequestMapping("/reader_header")
    public String reader_header() {
        return "reader_header";
    }

    @RequestMapping("/reader_books")
    public String readerBooks(HttpServletRequest request,Model model) {
        List<BookInfo> books = bookInfoService.queryAllBook();
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        List<LendList> myAllLendList = lendListService.myLendList(readerCard.getReaderId());
        List<Long> myLendList = new ArrayList<>();
        for (LendList lend : myAllLendList) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend.getBookId());
            }
        }
        model.addAttribute("books", books);
        model.addAttribute("myLendList", myLendList);
        return "reader_books";
    }


}
