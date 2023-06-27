package com.challenge.midas.service;

import com.challenge.midas.dto.request.PromotionRequest;
import com.challenge.midas.dto.response.PromotionResponse;
import com.challenge.midas.exception.PromotionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IPromotionService {

    @Transactional
    PromotionResponse create(PromotionRequest request) throws PromotionException;

    @Transactional
    PromotionResponse modify(String idPromotion, PromotionRequest request) throws PromotionException;

    @Transactional
    void enable(String idPromotion) throws PromotionException;

    @Transactional
    void disable(String idPromotion) throws PromotionException;

    @Transactional
    void delete(String idPromotion) throws PromotionException;

    @Transactional(readOnly = true)
    PromotionResponse getById(String idPromotion) throws PromotionException;

    @Transactional(readOnly = true)
    List<PromotionResponse> getAll(String value) throws PromotionException;

    @Transactional(readOnly = true)
    List<PromotionResponse> getForEnable() throws PromotionException;

    @Transactional(readOnly = true)
    List<PromotionResponse> getForDisable() throws PromotionException;
}