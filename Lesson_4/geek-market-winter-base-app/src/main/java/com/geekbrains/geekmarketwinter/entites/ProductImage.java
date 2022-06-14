package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products_images")
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    public ProductImage(Long id, Product product, String path) {
        this.id = id;
        this.product = product;
        this.path = path;
    }

    public ProductImage() {

    }
}
