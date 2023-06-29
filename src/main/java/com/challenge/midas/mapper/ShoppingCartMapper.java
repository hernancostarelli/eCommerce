package com.challenge.midas.mapper;

import com.challenge.midas.dto.request.ShoppingCartRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.dto.response.ShoppingCartResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.model.Product;
import com.challenge.midas.model.ShoppingCart;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.ProductRepository;
import com.challenge.midas.repository.ShoppingCartRepository;
import com.challenge.midas.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository repository;

    public ShoppingCartMapper(UserRepository userRepository, ProductRepository productRepository, ShoppingCartRepository repository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.repository = repository;
    }

    public ShoppingCart convertToEntity(ShoppingCart shoppingCart, ShoppingCartRequest request) throws UserException, ProductException {
        User user = userRepository.findById(request.getIdUser())
                .orElseThrow(() -> new UserException(EExceptionMessage.USER_NOT_FOUND.getMessage()));
        List<Product> productList = new ArrayList<>();
        for (String idProduct : request.getIdProducts()) {
            Product product = productRepository.findById(idProduct)
                    .orElseThrow(() -> new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.getMessage()));
            productList.add(product);
        }
        shoppingCart.setUser(user);
        user.getShoppingCarts().add(shoppingCart);
        userRepository.save(user);
        shoppingCart.setProducts(productList);
        if (shoppingCart.getId() != null) {
            shoppingCart.setModificationDate(new Date());
        }
        repository.save(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCartResponse convertToResponse(ShoppingCart shoppingCart) {
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
        shoppingCartResponse.setId(shoppingCart.getId());
        shoppingCartResponse.setUser(shoppingCart.getUser().getFullName());
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : shoppingCart.getProducts()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setImage(product.getImage());
            productResponse.setCreationDate(product.getCreationDate() != null ?
                    new SimpleDateFormat(DD_MM_YYYY).format(product.getCreationDate()) : null);
            productResponse.setModificationDate(product.getModificationDate() != null ?
                    new SimpleDateFormat(DD_MM_YYYY).format(product.getModificationDate()) : null);
            productResponseList.add(productResponse);
        }
        shoppingCartResponse.setProducts(productResponseList);
        shoppingCartResponse.setCreationDate(shoppingCart.getCreationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(shoppingCart.getCreationDate()) : null);
        shoppingCartResponse.setModificationDate(shoppingCart.getModificationDate() != null ?
                new SimpleDateFormat(DD_MM_YYYY).format(shoppingCart.getModificationDate()) : null);
        shoppingCartResponse.setDeleted(String.valueOf(shoppingCart.isDeleted()));
        return shoppingCartResponse;
    }

    public List<ShoppingCartResponse> convertToResponseList(List<ShoppingCart> shoppingCarts) {
        return shoppingCarts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}