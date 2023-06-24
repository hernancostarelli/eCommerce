package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.Product.ProductRequest;
import com.challenge.midas.dto.response.ProductResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.ProductException;
import com.challenge.midas.mapper.ProductMapper;
import com.challenge.midas.model.Product;
import com.challenge.midas.repository.ProductRepository;
import com.challenge.midas.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;

    public ProductServiceImpl(ProductMapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ProductResponse create(ProductRequest request) throws ProductException {
        Product productForConvert = mapper.convertToEntity(new Product(), request);
        Product productForSave = repository.save(productForConvert);
        return mapper.convertToResponse(productForSave);
    }

    @Override
    public ProductResponse modify(String idProduct, ProductRequest request) throws ProductException {
        Optional<Product> optionalProduct = repository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product productForConvert = mapper.convertToEntity(optionalProduct.get(), request);
            Product productForSave = repository.save(productForConvert);
            return mapper.convertToResponse(productForSave);
        } else {
            throw new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idProduct) throws ProductException {
        Optional<Product> optionalProduct = repository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.isDeleted()) {
                product.setDeleted(true);
                product.setModificationDate(new Date());
                repository.save(optionalProduct.get());
            } else {
                throw new ProductException(EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idProduct) throws ProductException {
        Optional<Product> optionalProduct = repository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.isDeleted()) {
                product.setDeleted(false);
                product.setModificationDate(new Date());
                repository.save(optionalProduct.get());
            } else {
                throw new ProductException(EExceptionMessage.THE_PRODUCT_COULD_NOT_BE_DISABLE.toString());
            }
        } else {
            throw new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idProduct) throws ProductException {
        Product product = repository.findById(idProduct).orElseThrow(() -> new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.toString()));
        repository.delete(product);
    }

    @Override
    public ProductResponse getById(String idProduct) throws ProductException {
        return repository.findById(idProduct)
                .map(mapper::convertToResponse)
                .orElseThrow(() -> new ProductException(EExceptionMessage.PRODUCT_NOT_FOUND.toString()));
    }

    @Override
    public List<ProductResponse> getAll(String value) throws ProductException {
        if (value == null) {
            value = "";
        }
        List<Product> productList = repository.getByValue("%" + value + "%");
        if (productList.isEmpty()) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(productList);
    }

    @Override
    public List<ProductResponse> getForEnable() throws ProductException {
        List<Product> productList = repository.getByEnable();
        if (productList.isEmpty()) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(productList);
    }

    @Override
    public List<ProductResponse> getForDisable() throws ProductException {
        List<Product> productList = repository.getByDisable();
        if (productList.isEmpty()) {
            throw new ProductException(EExceptionMessage.THE_PRODUCT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(productList);
    }
}