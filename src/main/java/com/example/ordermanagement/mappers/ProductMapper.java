package com.example.ordermanagement.mappers;

import com.example.ordermanagement.dtos.product.Product;
import com.example.ordermanagement.dtos.product.ProductCreate;
import com.example.ordermanagement.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    ProductEntity productCreateToProductEntity(ProductCreate productCreate);

    Product productEntityToProduct(ProductEntity productEntity);

    ProductEntity productToProductEntity(Product product);
}
