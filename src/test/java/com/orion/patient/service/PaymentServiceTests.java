package com.orion.patient.service;

import com.orion.patient.dto.PaymentDto;
import com.orion.patient.entity.AppointmentEntity;
import com.orion.patient.entity.PaymentEntity;
import com.orion.patient.entity.PaymentStatusEntity;
import com.orion.patient.entity.PaymentTypeEntity;
import com.orion.patient.mapper.PaymentMapper;
import com.orion.patient.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTests {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentEntity paymentEntity;
    private PaymentDto paymentDto;

    @BeforeEach
    void setUp() {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(10L);

        PaymentStatusEntity paymentStatusEntity = new PaymentStatusEntity();
        paymentStatusEntity.setStatus("Completed");

        PaymentTypeEntity paymentTypeEntity = new PaymentTypeEntity();
        paymentTypeEntity.setType("Credit Card");

        paymentEntity = new PaymentEntity();
        paymentEntity.setId(1L);
        paymentEntity.setAppointment(appointmentEntity);
        paymentEntity.setPaymentStatus(paymentStatusEntity);
        paymentEntity.setPaymentType(paymentTypeEntity);

        paymentDto = new PaymentDto(1L, 10L, "Completed", "Credit Card");
    }

    @Test
    void testFindAll() {
        when(paymentRepository.findAll()).thenReturn(List.of(paymentEntity));
        when(paymentMapper.toDto(paymentEntity)).thenReturn(paymentDto);

        List<PaymentDto> result = paymentService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).id()).isEqualTo(1L);
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(paymentEntity));
        when(paymentMapper.toDto(paymentEntity)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().id()).isEqualTo(1L);
        verify(paymentRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(paymentMapper.toEntity(paymentDto)).thenReturn(paymentEntity);
        when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        when(paymentMapper.toDto(paymentEntity)).thenReturn(paymentDto);

        PaymentDto result = paymentService.save(paymentDto);

        assertThat(result.id()).isEqualTo(1L);
        verify(paymentRepository, times(1)).save(paymentEntity);
    }

    @Test
    void testUpdate() {
        when(paymentMapper.toEntity(paymentDto)).thenReturn(paymentEntity);
        when(paymentRepository.save(paymentEntity)).thenReturn(paymentEntity);
        when(paymentMapper.toDto(paymentEntity)).thenReturn(paymentDto);

        PaymentDto result = paymentService.update(1L, paymentDto);

        assertThat(result.id()).isEqualTo(1L);
        verify(paymentRepository, times(1)).save(paymentEntity);
    }

    @Test
    void testDelete() {
        doNothing().when(paymentRepository).deleteById(1L);

        paymentService.delete(1L);

        verify(paymentRepository, times(1)).deleteById(1L);
    }
}
