package com.challenge.midas.service.impl;

import com.challenge.midas.dto.request.PaymentRequest;
import com.challenge.midas.dto.response.PaymentResponse;
import com.challenge.midas.enums.EExceptionMessage;
import com.challenge.midas.exception.PaymentException;
import com.challenge.midas.exception.UserException;
import com.challenge.midas.mapper.PaymentMapper;
import com.challenge.midas.model.Payment;
import com.challenge.midas.model.User;
import com.challenge.midas.repository.PaymentRepository;
import com.challenge.midas.repository.UserRepository;
import com.challenge.midas.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final UserRepository userRepository;

    @Override
    public PaymentResponse create(PaymentRequest request) throws PaymentException {
        Payment payment = mapper.convertToEntity(new Payment(), request);
        return mapper.convertToResponse(repository.save(payment));
    }

    @Override
    public PaymentResponse modify(String idPayment, PaymentRequest request) throws PaymentException {
        Optional<Payment> optionalPayment = repository.findById(idPayment);
        if (optionalPayment.isPresent()) {
            Payment product = mapper.convertToEntity(optionalPayment.get(), request);
            return mapper.convertToResponse(repository.save(product));
        } else {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String idPayment) throws PaymentException {
        Optional<Payment> optionalPayment = repository.findById(idPayment);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.isDeleted()) {
                payment.setDeleted(true);
                payment.setModificationDate(new Date());
                repository.save(payment);
            } else {
                throw new PaymentException(EExceptionMessage.THE_PAYMENT_COULD_NOT_BE_ENABLED.toString());
            }
        } else {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String idPayment) throws PaymentException {
        Optional<Payment> optionalPayment = repository.findById(idPayment);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.isDeleted()) {
                payment.setDeleted(false);
                payment.setModificationDate(new Date());
                repository.save(optionalPayment.get());
            } else {
                throw new PaymentException(EExceptionMessage.THE_PAYMENT_COULD_NOT_BE_DISABLE.toString());
            }
        } else {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void delete(String idPayment) throws PaymentException {
        Optional<Payment> optionalPayment = repository.findById(idPayment);
        if (optionalPayment.isPresent()) {
            repository.delete(optionalPayment.get());
        } else {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
    }

    @Override
    public PaymentResponse getById(String idPayment) throws PaymentException {
        Optional<Payment> optionalPayment = repository.findById(idPayment);
        if (optionalPayment.isPresent()) {
            return mapper.convertToResponse(optionalPayment.get());
        } else {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
    }

    @Override
    public List<PaymentResponse> getAll(String value) throws PaymentException {
        if (value == null) {
            value = "";
        }
        List<Payment> paymentList = repository.getByValue("%" + value + "%");
        if (paymentList.isEmpty()) {
            throw new PaymentException(EExceptionMessage.THE_PAYMENT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(paymentList);
    }

    @Override
    public List<PaymentResponse> gePaymentByUser(String idUser) throws PaymentException, UserException {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isEmpty()) {
            throw new UserException(EExceptionMessage.USER_NOT_FOUND.toString());
        }
        List<Payment> paymentList = repository.getPaymentByUser(idUser);
        if (paymentList.isEmpty()) {
            throw new PaymentException(EExceptionMessage.PAYMENT_NOT_FOUND.toString());
        }
        return mapper.convertToResponseList(paymentList);
    }

    @Override
    public List<PaymentResponse> getForEnable() throws PaymentException {
        List<Payment> paymentList = repository.getByEnable();
        if (paymentList.isEmpty()) {
            throw new PaymentException(EExceptionMessage.THE_PAYMENT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(paymentList);
    }

    @Override
    public List<PaymentResponse> getForDisable() throws PaymentException {
        List<Payment> paymentList = repository.getByDisable();
        if (paymentList.isEmpty()) {
            throw new PaymentException(EExceptionMessage.THE_PAYMENT_LIST_IS_EMPTY.toString());
        }
        return mapper.convertToResponseList(paymentList);
    }
}