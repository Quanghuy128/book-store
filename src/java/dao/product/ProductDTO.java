/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.product;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author huy
 */
public class ProductDTO implements Serializable{
    private String productId;
    private String title;
    private String author;
    private Date public_date;
    private double price;
    private int available_quantity;

    public ProductDTO() {
    }

    public ProductDTO(String productId, String title, String author, Date public_date, double price, int available_quantity) {
        this.productId = productId;
        this.title = title;
        this.author = author;
        this.public_date = public_date;
        this.price = price;
        this.available_quantity = available_quantity;
    }

    
    
    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the public_date
     */
    public Date getPublic_date() {
        return public_date;
    }

    /**
     * @param public_date the public_date to set
     */
    public void setPublic_date(Date public_date) {
        this.public_date = public_date;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the available_quantity
     */
    public int getAvailable_quantity() {
        return available_quantity;
    }

    /**
     * @param available_quantity the available_quantity to set
     */
    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }
    
    
    
}
