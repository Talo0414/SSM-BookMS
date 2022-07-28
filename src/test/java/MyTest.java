import com.ruoyu.bean.BookInfo;
import com.ruoyu.service.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class MyTest {
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminService adminService = context.getBean("adminServiceImpl", AdminService.class);
        BookInfoService bookInfoService = context.getBean("bookInfoServiceImpl", BookInfoService.class);
        LendListService lendListService = context.getBean("lendListServiceImpl", LendListService.class);
        ReaderCardService readerCardService = context.getBean("readerCardServiceImpl", ReaderCardService.class);
        ReaderInfoService readerInfoService = context.getBean("readerInfoServiceImpl", ReaderInfoService.class);

        Map<String, Object> map = new HashMap<>();
        map.put("readerId",10002);
        map.put("password","123456");
        System.out.println(readerCardService.findReaderByReaderId(10000));

    }
}
