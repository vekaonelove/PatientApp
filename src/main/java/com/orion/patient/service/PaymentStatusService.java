package com.orion.patient.service;

import com.orion.patient.dto.PaymentStatusDTO;
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

    public List<PaymentStatusDTO> findAll() {
        List<PaymentStatusEntity> paymentStatuses = paymentStatusRepository.findAll();
        return paymentStatuses.stream()
                .map(paymentStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PaymentStatusDTO> findById(String status) {
        Optional<PaymentStatusEntity> paymentStatusEntity = paymentStatusRepository.findById(status);
        return paymentStatusEntity.map(paymentStatusMapper::toDTO);
    }

    public PaymentStatusDTO save(PaymentStatusDTO paymentStatusDTO) {
        PaymentStatusEntity paymentStatusEntity = paymentStatusMapper.toEntity(paymentStatusDTO);
        PaymentStatusEntity savedPaymentStatus = paymentStatusRepository.save(paymentStatusEntity);
        return paymentStatusMapper.toDTO(savedPaymentStatus);
    }

    public PaymentStatusDTO update(String status, @Valid PaymentStatusDTO paymentStatusDTO) {
        PaymentStatusEntity paymentStatusEntity = paymentStatusMapper.toEntity(paymentStatusDTO);
        paymentStatusEntity.setStatus(status);
        PaymentStatusEntity updatedEntity = paymentStatusRepository.save(paymentStatusEntity);
        return paymentStatusMapper.toDTO(updatedEntity);
    }

    public void delete(String status) {
        paymentStatusRepository.deleteById(status);
    }
}
