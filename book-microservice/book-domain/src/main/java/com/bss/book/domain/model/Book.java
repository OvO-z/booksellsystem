package com.bss.book.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Long isbn;

    private String authorName;

    private String bookName;

    private String description;

    private Integer pages;

    private String pic;

    private String press;

    private Float price;

    private Integer publishStatus;

    private Date publishTime;

    private Integer sale;

    private Integer stock;

    private Integer verifyStatus;

    private String authorId;

    private Integer categoryId;

    private String albumPic;

    private static final long serialVersionUID = 1L;

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAlbumPic() {
        return albumPic;
    }

    public Book setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", isbn=").append(isbn);
        sb.append(", authorName=").append(authorName);
        sb.append(", bookName=").append(bookName);
        sb.append(", description=").append(description);
        sb.append(", pages=").append(pages);
        sb.append(", pic=").append(pic);
        sb.append(", press=").append(press);
        sb.append(", price=").append(price);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", sale=").append(sale);
        sb.append(", stock=").append(stock);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", authorId=").append(authorId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", albumPic=").append(albumPic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}