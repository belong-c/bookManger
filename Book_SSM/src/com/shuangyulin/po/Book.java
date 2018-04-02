package com.shuangyulin.po;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Book {
    /*ͼ������*/
	@NotEmpty(message="ͼ�����Ʋ���Ϊ��")  
    private String bookName;
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /*ͼ���������*/
    private BookType bookType;
    public BookType getBookType() {
        return bookType;
    }
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    /*ͼ��۸�*/
    @NotNull(message="��������۸�") 
    private Float price;
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    /*���*/
    @NotNull(message="����������")  
    private Integer count;
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    /*������*/
    private String publish;
    public String getPublish() {
        return publish;
    }
    public void setPublish(String publish) {
        this.publish = publish;
    }
    
    /*��������*/
    @NotEmpty(message="������������")  
    private String publishDate;
    public String getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    

    /*ͼ��������*/
    @NotEmpty(message="ͼ�������벻��Ϊ��") 
    private String barcode;
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /*ͼ��ͼƬ*/
    private String photoBook;
    public String getPhotoBook() {
        return photoBook;
    }
    public void setPhotoBook(String photoBook) {
        this.photoBook = photoBook;
    }

}