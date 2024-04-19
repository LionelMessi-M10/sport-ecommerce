package com.sport.controller.seller;

import com.sport.entity.CategoryEntity;
import com.sport.entity.OrderEntity;
import com.sport.entity.ProductEntity;
import com.sport.service.CategoryService;
import com.sport.service.OrderService;
import com.sport.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public SellerController(ProductService productService, CategoryService categoryService, OrderService orderService){
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping(value = { "", "/home" })
    public String home() {
        return "seller/index";
    }

    @GetMapping("/product-list")
    public ModelAndView productList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mav = new ModelAndView("seller/product-list");

        Page<ProductEntity> productPage = productService.findAll(pageNo, 9);
        
        mav.addObject("totalPage", productPage.getTotalPages());
        mav.addObject("currentPage", pageNo);
        mav.addObject("productPage", productPage);
        
        return mav;
    }

    @GetMapping("/category-list")
    public ModelAndView categoryList(CategoryEntity categoryEntity, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mav = new ModelAndView("seller/category");

        Page<CategoryEntity> categoryPage = categoryService.findAll(pageNo, 3);
        
        mav.addObject("category", categoryEntity);
        mav.addObject("categoryPage", categoryPage);
        mav.addObject("totalPage", categoryPage.getTotalPages());
        mav.addObject("currentPage", pageNo);
        
        return mav;
    }

    @GetMapping("/edit-category")
    public ModelAndView getEditCategoryPage(@RequestParam("id") Long id){
        ModelAndView mav = new ModelAndView("seller/update-category");
        CategoryEntity categoryEntity = this.categoryService.findByCategoryId(id);
        mav.addObject("category", categoryEntity);
        return mav;
    }

    @GetMapping("/order-list")
    public ModelAndView approvedOrder(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mav = new ModelAndView("seller/approved-order");
        
        Page<OrderEntity> orderPage = orderService.findAll(pageNo, 10);
        
        mav.addObject("totalPage", orderPage.getTotalPages());
        mav.addObject("currentPage", pageNo);
        mav.addObject("orderPage", orderPage);
        
        return mav;
    }

}
