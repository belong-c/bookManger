package com.shuangyulin.po;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty; 

public class BookType {
    /*ͼ�����*/
    private Integer bookTypeId;
    public Integer getBookTypeId() {
        return bookTypeId;
    }
    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    /*�������*/
    @NotEmpty(message="ͼ�����Ͳ���Ϊ��")  
    private String bookTypeName;
    public String getBookTypeName() {
        return bookTypeName;
    }
    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    /*�ɽ�������*/
    @NotNull(message="���������������")  
    private Integer days;
    public Integer getDays() {
        return days;
    }
    public void setDays(Integer days) {
        this.days = days;
    }

}