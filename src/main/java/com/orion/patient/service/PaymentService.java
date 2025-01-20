package com.orion.patient.service;

import com.orion.patient.dto.PaymentDTO;
import com.orion.patient.entity.PaymentEntity;
import com.orion.patient.mapper.PaymentMapper;
import com.orion.patient.repository.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public List<PaymentDTO> findAll() {
        List<PaymentEntity> payments = paymentRepository.findAll();
        return payments.stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PaymentDTO> findById(Long id) {
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(id);
        return paymentEntity.map(paymentMapper::toDTO);
    }

    public PaymentDTO save(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentMapper.toEntity(paymentDTO);
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);
        return paymentMapper.toDTO(savedPayment);
    }

    public PaymentDTO update(Long id, @Valid PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = paymentMapper.toEntity(paymentDTO);
        paymentEntity.setId(id);
        PaymentEntity updatedPayment = paymentRepository.save(paymentEntity);
        return paymentMapper.toDTO(updatedPayment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
