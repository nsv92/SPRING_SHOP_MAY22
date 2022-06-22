package com.geekbrains.app.entites;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDTO {

    private Long id;

    private String title;

    private Category category;

    private String vendorCode;

    private double price;

    private String shortDescription;

    private String fullDescription;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private List<Long> images;

    private MultipartFile[] newImage;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, Category category,
                      String vendorCode, double price,
                      String shortDescription, String fullDescription,
                      LocalDateTime createAt, LocalDateTime updateAt,
                      List<Long> images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.vendorCode = vendorCode;
        this.price = price;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }

    public MultipartFile[] getNewImage() {
        return newImage;
    }

    public void setNewImage(MultipartFile[] newImage) {
        this.newImage = newImage;
    }
}
