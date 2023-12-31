package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.model.Product;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private final UserRepository userRepository;

    public ProductMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Product convertToEntity(Product product, ProductRequest request) throws ProductException, UserException {
        validateRequest(request);
        product.setName((request.getName()));
        product.setDescription((request.getDescription()));
        validateNumbers(request);
        product.setPrice(request.getPrice());
        int quantity = Integer.parseInt(request.getQuantity());
        product.setQuantity(quantity);
        product.setImage((request.getImage()));
        if (product.getId() != null)
            product.setModificationDate(new Date());
        product.setUser(getUser(request));
        return product;
    }

    public ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());

        String formattedPrice = String.format("%.2f", product.getPrice());
        response.setPrice(formattedPrice);

        response.setQuantity(product.getQuantity());
        response.setImage(product.getImage());
        response.setUser(getUserResponse(product));
        response.setCreationDate(product.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(product.getCreationDate()) : null);
        response.setModificationDate(product.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(product.getModificationDate()) : null);
        response.setDeleted(String.valueOf(product.isDeleted()));
        return response;
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
        String price = Double.toString(request.getPrice());
        String quantity = request.getQuantity();
        String image = request.getImage();
        if (StringUtils.isBlank(name))
            throw new ProductException(EExceptionMessage.THE_PRODUCT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(description))
            throw new ProductException(EExceptionMessage.THE_PRODUCT_DESCRIPTION_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(price))
            throw new ProductException(EExceptionMessage.THE_PRODUCT_PRICE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(quantity))
            throw new ProductException(EExceptionMessage.THE_PRODUCT_QUANTITY_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        if (StringUtils.isBlank(image))
            throw new ProductException(EExceptionMessage.THE_PRODUCT_IMAGE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
    }

    private User getUser(ProductRequest request) throws UserException {
        validateRequestUser(request);
        String idUser = request.getIdUser();
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.getMessage());
        }
    }

    private String getUserResponse(Product product) {
        Optional<User> optionalUser = userRepository.findById(product.getUser().getId());
        return optionalUser.map(User::getFullName).orElse(null);
    }

    private void validateRequestUser(ProductRequest request) throws UserException {
        String idUser = request.getIdUser();
        boolean userExist = userRepository.existsById(idUser);
        if (!userExist) {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
    }

    public void validateNumbers(ProductRequest request) throws ProductException {
        String stringPrice = Double.toString(request.getPrice());
        String stringQuantity = request.getQuantity();
        double price = Double.parseDouble(stringPrice);
        int quantity = Integer.parseInt(stringQuantity);
        if (price <= 0)
            throw new ProductException(EExceptionMessage.THE_PRODUCT_PRICE_MUST_BE_POSITIVE.toString());
        if (quantity <= 0)
            throw new ProductException(EExceptionMessage.THE_PRODUCT_QUANTITY_MUST_BE_POSITIVE.toString());
    }
}