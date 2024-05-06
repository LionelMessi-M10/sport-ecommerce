package com.sport.controller.seller;

import java.text.ParseException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sport.converter.ProductConverter;
import com.sport.entity.CategoryEntity;
import com.sport.entity.ProductEntity;
import com.sport.entity.UserEntity;
import com.sport.model.dto.ProductDTO;
import com.sport.service.CategoryService;
import com.sport.service.ProductService;
import com.sport.service.UserService;

@Controller
@RequestMapping("/seller")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final ProductConverter productConverter;

    public ProductController(CategoryService categoryService, ProductService productService, UserService userService, ProductConverter productConverter) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.productConverter = productConverter;
    }

    @GetMapping("/add-product")
    public ModelAndView getAddProductPage(ProductDTO productDTO) {
        ModelAndView mav = new ModelAndView("seller/add-product");
        List<CategoryEntity> categories = categoryService.findAll();
        mav.addObject("categories", categories);
        mav.addObject("productDTO", productDTO);
        return mav;
    }

    @PostMapping("/add-product")
    public String handleSaveProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                            @RequestParam("imageProduct") MultipartFile[] imageProducts) throws ParseException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()){
            String userName = authentication.getName();
            UserEntity userEntity = this.userService.getByUsername(userName);
            this.productService.save(imageProducts, productDTO, userEntity);
        }
        else return "redirect:/shop/login";
        
        return "redirect:/seller/product-list";
    }

    @GetMapping("/edit-product")
    public ModelAndView editProduct(@RequestParam("id") Long id){
        ModelAndView mav = new ModelAndView("seller/edit-product");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication != null && authentication.isAuthenticated()){
            UserEntity userEntity = this.userService.getByUsername(authentication.getName());
            ProductEntity product = productService.findById(id);
            ProductDTO productDTO = this.productConverter.convertToDTO(product, userEntity);
            List<CategoryEntity> categories = categoryService.findAll();
            mav.addObject("productDTO", productDTO);
            mav.addObject("categories", categories);
        }
        else mav = new ModelAndView("shop/login");
        
        return mav;
    }

    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                                @RequestParam("imageProduct") MultipartFile[] imageProducts){
        productService.updateProduct(imageProducts, productDTO);
        return "redirect:/seller/product-list";
    }

    @GetMapping("/seller/delete-product")
    public String deleteProduct(@RequestParam("id") Long id){
        productService.deleteById(id);
        return "redirect:/seller/product-list";
    }
}
