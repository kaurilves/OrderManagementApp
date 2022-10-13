package com.example.ordermanagement.apis;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.product.Product;
import com.example.ordermanagement.dtos.product.ProductCreate;
import com.example.ordermanagement.dtos.product.ProductUpdate;
import com.example.ordermanagement.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = "Product: management")
@RequestMapping("/product")
public class ProductController {

  @Resource
  private ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "add new product")
    public Product addProduct (@Valid @RequestBody ProductCreate productCreate){
        return productService.addProduct(productCreate);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "get product from database")
    public Product getProduct (@PathVariable  Integer productId){
        return productService.getProduct(productId);
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "update product info")
    public Customer updateProduct (@PathVariable Integer productId, @Valid @RequestBody ProductUpdate productUpdate){
        return productService.productUpdate(productId, productUpdate);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "delete product")
    public void deleteProduct (@PathVariable Integer productId){
        productService.deleteProduct(productId);
    }



}
