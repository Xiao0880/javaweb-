package Listener;

import Dao.BookDao;
import Dao.BorrowDao;
import Dao.Impl.BookDaoImpl;
import Dao.Impl.BorrowDaoImpl;
import Dao.Impl.ReaderDaoImpl;
import Dao.Impl.ViewBorrowInfoDaoImpl;
import Dao.ReaderDao;
import Dao.ViewBorrowInfoDao;

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
        BorrowDao borrowDao = new BorrowDaoImpl();
        ViewBorrowInfoDao view_borrow_infoDao = new ViewBorrowInfoDaoImpl();

        ReaderService readerService = new ReaderServiceImpl();
        BookService bookService = new BookServiceImpl();
        BorrowService borrowService = new BorrowServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        readerService.setReaderDao(readerDao);
        readerService.setViewBorrowInfoDao(view_borrow_infoDao);
        readerService.setBorrowDao(borrowDao);
        bookService.setBookDao(bookDao);
        borrowService.setBorrowDao(borrowDao);
        borrowService.setBookDao(bookDao);

        Map<String,Object> container=new HashMap<>();
        container.put("readerService",readerService);
        container.put("bookService",bookService);
        container.put("borrowService",borrowService);
        container.put("adminService",adminService);

        sce.getServletContext().setAttribute("container",container);

    }
    public void contextDestroyed(ServletContextEvent sce) {}
}