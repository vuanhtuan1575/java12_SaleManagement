package java12.projectsalemanagement.product.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UpdateProductDto {
    @NotNull
    @Column
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
