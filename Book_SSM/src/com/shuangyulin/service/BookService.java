package com.shuangyulin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shuangyulin.mapper.BookMapper;
import com.shuangyulin.po.Book;
import com.shuangyulin.po.BookType;

@Service @Transactional
public class BookService {

	@Resource BookMapper bookMapper;
    /*ÿҳ��ʾ��¼��Ŀ*/
    private final int PAGE_SIZE = 5;

    /*�����ѯ���ܵ�ҳ��*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*�����ѯ�����ܼ�¼��*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*���Book��Ϣ*/
    public void addBook(Book book) throws Exception {
    	bookMapper.addBook(book); 
    }

    /*��ѯBook��Ϣ*/
    public ArrayList<Book> queryBook(String bookName,BookType bookType,String barcode,String publishDate,int currentPage) throws Exception { 
    	String where = "where 1=1";
    	if(!bookName.equals("")) where = where + " and t_book.bookName like '%" + bookName + "%'";
    	if(null != bookType && bookType.getBookTypeId() != null && bookType.getBookTypeId()!=0) where += " and t_book.bookType=" + bookType.getBookTypeId();
    	if(!barcode.equals("")) where = where + " and t_book.barcode like '%" + barcode + "%'";
    	if(!publishDate.equals("")) where = where + " and t_book.publishDate like '%" + publishDate + "%'"; 
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	return bookMapper.queryBook(where, startIndex, this.PAGE_SIZE);
    	
    }


    //��ѯͼ����Ϣ
    public ArrayList<Book> queryBook(String bookName,BookType bookType,String barcode,String publishDate) throws Exception { 
    	String where = "where 1=1";
    	if(!bookName.equals("")) where = where + " and t_book.bookName like '%" + bookName + "%'";
    	if(null != bookType && bookType.getBookTypeId() != null && bookType.getBookTypeId()!=0) where += " and t_book.bookType=" + bookType.getBookTypeId();
    	if(!barcode.equals("")) where = where + " and t_book.barcode like '%" + barcode + "%'";
    	if(!publishDate.equals("")) where = where + " and t_book.publishDate like '%" + publishDate + "%'"; 
    	return bookMapper.queryBookList(where);
    }

    
    public ArrayList<Book> queryAllBookInfo() throws Exception { 
        return bookMapper.queryBookList("where 1=1");
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void queryTotalPageAndRecordNumber(String bookName,BookType bookType,String barcode,String publishDate) throws Exception {
    	String where = "where 1=1";
    	if(!bookName.equals("")) where = where + " and t_book.bookName like '%" + bookName + "%'";
    	if(null != bookType && bookType.getBookTypeId() != null && bookType.getBookTypeId()!=0) where += " and t_book.bookType=" + bookType.getBookTypeId();
    	if(!barcode.equals("")) where = where + " and t_book.barcode like '%" + barcode + "%'";
    	if(!publishDate.equals("")) where = where + " and t_book.publishDate like '%" + publishDate + "%'"; 
    	
        recordNumber = bookMapper.queryBookCount(where);
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*����������ȡ����*/
    public Book getBookByBarcode(String barcode) throws Exception {
        Book book = bookMapper.getBook(barcode); 
        return book;
    }

    /*����Book��Ϣ*/
    public void updateBook(Book book) throws Exception {
        bookMapper.updateBook(book);
    }

    /*ɾ��Book��Ϣ*/
    public void deleteBook (String barcode) throws Exception {
        bookMapper.deleteBook(barcode);
    }

}
