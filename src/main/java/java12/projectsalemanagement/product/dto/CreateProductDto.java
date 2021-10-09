package java12.projectsalemanagement.product.dto;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;



public class CreateProductDto {
    @NotNull
    @Column
    private String name;

    @Column
    private String imageUlr;

    @Column
    private String description;
    @Column
    private String review;
    @Column
    private double price;
    @Column
    private String trademark;

    public CreateProductDto(String name, String imageUlr, String description, String review, double price, String trademark) {
        this.name = name;
        this.imageUlr = imageUlr;
        this.description = description;
        this.review = review;
        this.price = price;
        this.trademark = trademark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUlr() {
        return imageUlr;
    }

    public void setImageUlr(String imageUlr) {
        this.imageUlr = imageUlr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }
}
