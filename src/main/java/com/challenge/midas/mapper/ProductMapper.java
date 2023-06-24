package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.Product.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductMapper {
    public Product convertToEntity(Product product, ProductRequest request) throws ProductException {
        validateRequest(request);
        product.setName((request.getName()));
        product.setDescription((request.getDescription()));
        product.setPrice((request.getPrice()));
        product.setQuantity((request.getQuantity()));
        product.setImage((request.getImage()));
        if (product.getId() != null)
            product.setModificationDate(new Date());
        return product;
    }

    public ProductResponse convertToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setImage(product.getImage());
        productResponse.setUser(product.getUser());
        productResponse.setOrders(product.getOrders());
        productResponse.setShoppingCarts(product.getShoppingCarts());
        productResponse.setCreationDate(String.valueOf(product.getCreationDate()));
        productResponse.setModificationData(String.valueOf(product.getModificationDate()));
        productResponse.setDeleted(String.valueOf(product.isDeleted()));
        return productResponse;
    }

    public List<ProductResponse> convertToResponseList(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product entity : productList) {
            productResponseList.add(this.convertToResponse(entity));
        }
        return productResponseList;
    }

    private void validateRequest(ProductRequest request) throws ProductException {
        String name = request.getName();
        String description = request.getDescription();
        Double price = request.getPrice();
        Integer quantity = request.getQuantity();
        String image = request.getImage();
        if (StringUtils.isBlank(name)) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (StringUtils.isBlank(description)) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_DESCRIPTION_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (price == null || price.isNaN()) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_PRICE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (quantity == null) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_QUANTITY_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (StringUtils.isBlank(image)) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_IMAGE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
    }
}