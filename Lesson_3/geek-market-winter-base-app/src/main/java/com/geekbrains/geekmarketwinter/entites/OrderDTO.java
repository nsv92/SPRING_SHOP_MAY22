package com.geekbrains.geekmarketwinter.entites;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;

    private String user;

    private List<OrderItem> orderItems;

    private OrderStatus status;

    private Double price;

    private Double deliveryPrice;

    private String deliveryAddress;

    private String phoneNumber;

    private LocalDateTime deliveryDate;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private boolean confirmed;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String user, List<OrderItem> orderItems,
                    OrderStatus status, Double price,
                    Double deliveryPrice, String deliveryAddress,
                    String phoneNumber, LocalDateTime deliveryDate,
                    LocalDateTime createAt, LocalDateTime updateAt,
                    boolean confirmed) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.status = status;
        this.price = price;
        this.deliveryPrice = deliveryPrice;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.confirmed = confirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", orderItems=" + orderItems +
                ", status=" + status +
                ", price=" + price +
                ", deliveryPrice=" + deliveryPrice +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", confirmed=" + confirmed +
                '}';
    }
}
