package com.sport.controller.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sport.entity.CategoryEntity;
import com.sport.service.CategoryService;

@Controller
@RequestMapping("/seller")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public String handleAddCategory(@ModelAttribute("category") CategoryEntity categoryEntity){
        this.categoryService.handleAddCategory(categoryEntity);
        return "redirect:/seller/category-list";
    }

    @PostMapping("/edit-category")
    public String handleEditCategory(@ModelAttribute("category") CategoryEntity categoryEntity){
        this.categoryService.handleEditCategory(categoryEntity);
        return "redirect:/seller/category-list";
    }

    @GetMapping("/delete-category")
    public String handleDeleteCategory(@RequestParam("id") Long id){
        this.categoryService.deleteByCategoryId(id);
        return "redirect:/seller/category-list";
    }
}
