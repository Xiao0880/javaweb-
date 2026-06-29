package Dao.Impl;
import Dao.ViewBorrowInfoDao;
import Entity.ViewBorrowInfo;
import JDBC.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewBorrowInfoDaoImpl implements ViewBorrowInfoDao {
    @Override
    public List<ViewBorrowInfo> selectBorrowInfoByReaderName(String readerName) throws SQLException {

        List<ViewBorrowInfo> view_Borrow_InfoList = new ArrayList<>();
        ViewBorrowInfo view_Borrow_Info;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        // 获取用户输入的搜索关键词
        String sql = "SELECT * FROM v_borrow_info WHERE reader_name=?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, readerName);
        rs=pstmt.executeQuery();
        while(rs.next()){
            view_Borrow_Info = new ViewBorrowInfo();
            view_Borrow_Info.setBorrowId(rs.getInt("borrow_id"));
            view_Borrow_Info.setReaderName(rs.getString("reader_name"));
            view_Borrow_Info.setBookName(rs.getString("book_name"));
            view_Borrow_Info.setBorrowDate(rs.getDate("borrow_date"));
            view_Borrow_Info.setReturnDate(rs.getDate("return_date"));
            view_Borrow_InfoList.add(view_Borrow_Info);
            System.out.println(1);
        }

        return view_Borrow_InfoList;
    }

    @Override
    public List<ViewBorrowInfo> selectBorrowInfoByBorrowId(int borrowId) throws SQLException {
        List<ViewBorrowInfo> view_Borrow_InfoList = new ArrayList<>();
        ViewBorrowInfo view_Borrow_Info;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        // 获取用户输入的搜索关键词
        String sql = "SELECT * FROM v_borrow_info WHERE borrow_id=?";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1, borrowId);
        rs=pstmt.executeQuery();
        if(rs.next()){

            view_Borrow_Info = new ViewBorrowInfo();
            view_Borrow_Info.setBorrowId(rs.getInt("borrow_id"));
            view_Borrow_Info.setReaderName(rs.getString("reader_name"));
            view_Borrow_Info.setBookName(rs.getString("book_name"));
            view_Borrow_Info.setBorrowDate(rs.getDate("borrow_date"));
            view_Borrow_Info.setReturnDate(rs.getDate("return_date"));
            view_Borrow_InfoList.add(view_Borrow_Info);
        }
        return view_Borrow_InfoList;
    }

    private void getViewBorrowInfo(List<ViewBorrowInfo> view_Borrow_InfoList, ResultSet rs) throws SQLException {
        ViewBorrowInfo view_Borrow_Info;
        view_Borrow_Info = new ViewBorrowInfo();
        view_Borrow_Info.setBorrowId(rs.getInt("borrow_id"));
        view_Borrow_Info.setReaderName(rs.getString("reader_name"));
        view_Borrow_Info.setBookName(rs.getString("book_name"));
        view_Borrow_Info.setBorrowDate(rs.getDate("borrow_date"));
        view_Borrow_Info.setReturnDate(rs.getDate("return_date"));
        view_Borrow_InfoList.add(view_Borrow_Info);
    }

    @Override
    public List<ViewBorrowInfo> selectAllBorrowInfo() throws SQLException {
        List<ViewBorrowInfo> view_Borrow_InfoList = new ArrayList<>();
        ViewBorrowInfo view_Borrow_Info;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        // 获取用户输入的搜索关键词
        String sql = "SELECT * FROM v_borrow_info";
        conn= DBUtil.getConnection();
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        while(rs.next()){
            getViewBorrowInfo(view_Borrow_InfoList, rs);
        }

        return view_Borrow_InfoList;
    }

    @Override
    public boolean insertBorrowInfo(ViewBorrowInfo borrowInfo) throws SQLException {
        return false;
    }
}