package com.orion.patient.service;

import com.orion.patient.dto.PaymentTypeDto;
import com.orion.patient.entity.PaymentTypeEntity;
import com.orion.patient.mapper.PaymentTypeMapper;
import com.orion.patient.repository.PaymentTypeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    public List<PaymentTypeDto> findAll() {
        List<PaymentTypeEntity> paymentTypes = paymentTypeRepository.findAll();
        return paymentTypes.stream()
                .map(paymentTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PaymentTypeDto> findById(String type) {
        Optional<PaymentTypeEntity> paymentTypeEntity = paymentTypeRepository.findById(type);
        return paymentTypeEntity.map(paymentTypeMapper::toDto);
    }

    public PaymentTypeDto save(PaymentTypeDto paymentTypeDTO) {
        PaymentTypeEntity paymentTypeEntity = paymentTypeMapper.toEntity(paymentTypeDTO);
        PaymentTypeEntity savedPaymentType = paymentTypeRepository.save(paymentTypeEntity);
        return paymentTypeMapper.toDto(savedPaymentType);
    }

    public PaymentTypeDto update(String type, @Valid PaymentTypeDto paymentTypeDTO) {
        PaymentTypeEntity paymentTypeEntity = paymentTypeMapper.toEntity(paymentTypeDTO);
        paymentTypeEntity.setType(type);
        PaymentTypeEntity updatedEntity = paymentTypeRepository.save(paymentTypeEntity);
        return paymentTypeMapper.toDto(updatedEntity);
    }

    public void delete(String type) {
        paymentTypeRepository.deleteById(type);
    }
}
