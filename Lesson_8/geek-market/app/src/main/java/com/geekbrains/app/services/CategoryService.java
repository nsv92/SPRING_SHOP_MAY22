package com.geekbrains.app.services;


import com.geekbrains.app.entites.Category;
import com.geekbrains.app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    public List<Category> getAllCategories(Integer page, Integer size, String sortField) {
        return (List<Category>)categoryRepository.findAll();
    }

    public void save (Category category) {
        categoryRepository.save(category);
    }


}
