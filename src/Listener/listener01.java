package Listener;

import Dao.*;
import Dao.Impl.*;

import Service.AdminService;
import Service.BookService;
import Service.BorrowService;
import Service.Impl.AdminServiceImpl;
import Service.Impl.BookServiceImpl;
import Service.Impl.BorrowServiceImpl;
import Service.Impl.ReaderServiceImpl;
import Service.ReaderService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class listener01 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ReaderDao readerDao = new ReaderDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        BookTypeDao bookTypeDao = new BookTypeDaoImpl();
        BorrowDao borrowDao = new BorrowDaoImpl();
        ViewBorrowInfoDao view_borrow_infoDao = new ViewBorrowInfoDaoImpl();
        ViewAdminStatisticsDao viewAdminStatisticsDao = new ViewAdminStatisticsDaoImpl();

        ReaderService readerService = new ReaderServiceImpl();
        BookService bookService = new BookServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        readerService.setReaderDao(readerDao);
        readerService.setViewBorrowInfoDao(view_borrow_infoDao);
        readerService.setBorrowDao(borrowDao);
        bookService.setBookDao(bookDao);
        bookService.setBookTypeDao(bookTypeDao);
        borrowService.setBorrowDao(borrowDao);
        borrowService.setBookDao(bookDao);
        adminService.setViewAdminStatisticsDao(viewAdminStatisticsDao);

        Map<String,Object> container=new HashMap<>();
        container.put("readerService",readerService);
        container.put("bookService",bookService);
        container.put("borrowService",borrowService);
        container.put("adminService",adminService);

        sce.getServletContext().setAttribute("container",container);

    }
    public void contextDestroyed(ServletContextEvent sce) {}
}