package com.orion.patient.service;

import com.orion.patient.dto.PaymentStatusDto;
import com.orion.patient.entity.PaymentStatusEntity;
import com.orion.patient.mapper.PaymentStatusMapper;
import com.orion.patient.repository.PaymentStatusRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentStatusMapper paymentStatusMapper;

    public PaymentStatusService(PaymentStatusRepository paymentStatusRepository, PaymentStatusMapper paymentStatusMapper) {
        this.paymentStatusRepository = paymentStatusRepository;
        this.paymentStatusMapper = paymentStatusMapper;
    }

    public List<PaymentStatusDto> findAll() {
        List<PaymentStatusEntity> paymentStatuses = paymentStatusRepository.findAll();
        return paymentStatuses.stream()
                .map(paymentStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PaymentStatusDto> findById(String status) {
        Optional<PaymentStatusEntity> paymentStatusEntity = paymentStatusRepository.findById(status);
        return paymentStatusEntity.map(paymentStatusMapper::toDto);
    }

    public PaymentStatusDto save(PaymentStatusDto paymentStatusDTO) {
        PaymentStatusEntity paymentStatusEntity = paymentStatusMapper.toEntity(paymentStatusDTO);
        PaymentStatusEntity savedPaymentStatus = paymentStatusRepository.save(paymentStatusEntity);
        return paymentStatusMapper.toDto(savedPaymentStatus);
    }

    public PaymentStatusDto update(String status, @Valid PaymentStatusDto paymentStatusDTO) {
        PaymentStatusEntity paymentStatusEntity = paymentStatusMapper.toEntity(paymentStatusDTO);
        paymentStatusEntity.setStatus(status);
        PaymentStatusEntity updatedEntity = paymentStatusRepository.save(paymentStatusEntity);
        return paymentStatusMapper.toDto(updatedEntity);
    }

    public void delete(String status) {
        paymentStatusRepository.deleteById(status);
    }
}
