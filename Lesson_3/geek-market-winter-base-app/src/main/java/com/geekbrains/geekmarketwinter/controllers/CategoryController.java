package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isEmpty()).orElse("id")));
        return "categories";
    }

    @GetMapping("/new")
    public String newCategoryForm (Model model) {

        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/new")
    public String update (@Valid @ModelAttribute ("category") Category newCategory) {
        categoryService.save(newCategory);
        return "admin-panel";
    }
}
