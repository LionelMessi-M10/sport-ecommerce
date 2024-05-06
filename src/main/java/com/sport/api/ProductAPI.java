package com.sport.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sport.service.ProductService;

@RestController
@RequestMapping("/seller")
public class ProductAPI {

  private final ProductService productService;

  public ProductAPI(ProductService productService) {
    this.productService = productService;
  }

  @DeleteMapping("/delete-product/{id}")
  public void hanldeDeleteProduct(@PathVariable("id") Long id) {
    this.productService.deleteById(id);
  }
}
