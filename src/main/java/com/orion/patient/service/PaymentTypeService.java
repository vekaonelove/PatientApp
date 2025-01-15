package com.orion.patient.service;

import com.orion.patient.dto.PaymentTypeDTO;
import com.orion.patient.entity.PaymentTypeEntity;
import com.orion.patient.mapper.PaymentTypeMapper;
import com.orion.patient.repository.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    @Autowired
    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    public List<PaymentTypeDTO> findAll() {
        List<PaymentTypeEntity> paymentTypes = paymentTypeRepository.findAll();
        return paymentTypes.stream()
                .map(paymentTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PaymentTypeDTO> findById(String type) {
        Optional<PaymentTypeEntity> paymentTypeEntity = paymentTypeRepository.findById(type);
        return paymentTypeEntity.map(paymentTypeMapper::toDTO);
    }

    public PaymentTypeDTO save(PaymentTypeDTO paymentTypeDTO) {
        PaymentTypeEntity paymentTypeEntity = paymentTypeMapper.toEntity(paymentTypeDTO);
        PaymentTypeEntity savedPaymentType = paymentTypeRepository.save(paymentTypeEntity);
        return paymentTypeMapper.toDTO(savedPaymentType);
    }

    public void delete(String type) {
        paymentTypeRepository.deleteById(type);
    }
}
