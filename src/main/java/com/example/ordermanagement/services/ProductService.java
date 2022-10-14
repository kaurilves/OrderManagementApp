package com.example.ordermanagement.services;

import com.example.ordermanagement.dtos.product.Product;
import com.example.ordermanagement.dtos.product.ProductCreate;
import com.example.ordermanagement.dtos.product.ProductUpdate;

import com.example.ordermanagement.entities.ProductEntity;
import com.example.ordermanagement.mappers.ProductMapper;
import com.example.ordermanagement.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService {

    @Resource
    ProductRepository productRepository;

    @Resource
    ProductMapper productMapper;

    public Product addProduct(ProductCreate productCreate) {
        ProductEntity productEntity = productMapper.productCreateToProductEntity(productCreate);
        productRepository.save(productEntity);
        return productMapper.productEntityToProduct(productEntity);
    }

    public Product getProduct(Integer productId) {
        return productMapper.productEntityToProduct(getProductEntity(productId));
    }


    public Product productUpdate(Integer productId, ProductUpdate productUpdate) {
        ProductEntity productEntity = getProductEntity(productId);
        productEntity.setName(productUpdate.getName());
        productEntity.setSkuCode(productUpdate.getSkuCode());
        productEntity.setPrice(productUpdate.getPrice());
        productRepository.save(productEntity);
        return productMapper.productEntityToProduct(productEntity);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public ProductEntity getProductEntity(Integer productId) {
        return productRepository.findById(productId).get();
    }

}
