package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductDTO;
import com.geekbrains.geekmarketwinter.entites.ProductImage;
import com.geekbrains.geekmarketwinter.repositories.CategoryRepository;
import com.geekbrains.geekmarketwinter.repositories.ProductRepository;
import com.geekbrains.geekmarketwinter.repositories.specifications.ProductSpecs;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private ImageSaverService imageSaverService;



    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ImageSaverService imageSaverService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageSaverService = imageSaverService;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setImageSaverService(ImageSaverService imageSaverService) {
        this.imageSaverService = imageSaverService;
    }

    public List<Product> getAllProducts() {
        return (List<Product>)(productRepository.findAll());
    }

    public List<Product> getAllProductsWithFilter(Specification<Product> productSpecs) {
        return (List<Product>)(productRepository.findAll(productSpecs));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getAllProductsByPage(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

    public boolean isProductWithTitleExists(String productTitle) {
        return productRepository.findOneByTitle(productTitle) != null;
    }

//    public void saveProduct(Product product) {
//        productRepository.save(product);
//    }

    private ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product.getId(),
                product.getTitle(),
                product.getCategory(),
                product.getVendorCode(),
                product.getPrice(),
                product.getShortDescription(),
                product.getFullDescription(),
                product.getCreateAt(),
                product.getUpdateAt(),
                product.getImages().stream().map(ProductImage::getId).collect(Collectors.toList()));
    }

    @Transactional
    public void save(ProductDTO productDTO) throws NotFoundException {
        Product product = (productDTO.getId() != null) ? productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new NotFoundException("")) : new Product();
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setTitle(productDTO.getTitle());
        product.setCategory(category);
        product.setVendorCode(productDTO.getVendorCode());
        product.setPrice(productDTO.getPrice());
        product.setShortDescription(productDTO.getShortDescription());
        product.setFullDescription(productDTO.getFullDescription());
        product.setCreateAt(productDTO.getCreateAt());
        product.setUpdateAt(productDTO.getUpdateAt());

        if (productDTO.getNewImage() != null) {
            for (MultipartFile newImage : productDTO.getNewImage()) {
                try {
                    product.getImages().add(new ProductImage(null, product, imageSaverService.saveFile(newImage)));
                } catch (NullPointerException ex) {
                    throw new RuntimeException(ex);
                }


            }
        }

        productRepository.save(product);
    }
}
