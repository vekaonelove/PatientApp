package com.orion.patient.service;

import com.orion.patient.dto.PaymentStatusDto;
import com.orion.patient.entity.PaymentStatusEntity;
import com.orion.patient.mapper.PaymentStatusMapper;
import com.orion.patient.repository.PaymentStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentStatusServiceTests {

    @Mock
    private PaymentStatusRepository paymentStatusRepository;

    @Mock
    private PaymentStatusMapper paymentStatusMapper;

    @InjectMocks
    private PaymentStatusService paymentStatusService;

    private PaymentStatusDto paymentStatusDto;
    private PaymentStatusEntity paymentStatusEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentStatusDto = new PaymentStatusDto("COMPLETED");
        paymentStatusEntity = new PaymentStatusEntity("COMPLETED");
    }

    @Test
    void testFindAll() {
        when(paymentStatusRepository.findAll()).thenReturn(List.of(paymentStatusEntity));
        when(paymentStatusMapper.toDto(paymentStatusEntity)).thenReturn(paymentStatusDto);
        List<PaymentStatusDto> result = paymentStatusService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(paymentStatusDto.status(), result.get(0).status());
        verify(paymentStatusRepository, times(1)).findAll();
        verify(paymentStatusMapper, times(1)).toDto(paymentStatusEntity);
    }

    @Test
    void testFindById() {
        when(paymentStatusRepository.findById("COMPLETED")).thenReturn(Optional.of(paymentStatusEntity));
        when(paymentStatusMapper.toDto(paymentStatusEntity)).thenReturn(paymentStatusDto);
        Optional<PaymentStatusDto> result = paymentStatusService.findById("COMPLETED");
        assertTrue(result.isPresent());
        assertEquals(paymentStatusDto.status(), result.get().status());
        verify(paymentStatusRepository, times(1)).findById("COMPLETED");
        verify(paymentStatusMapper, times(1)).toDto(paymentStatusEntity);
    }

    @Test
    void testFindById_NotFound() {
        when(paymentStatusRepository.findById("FAILED")).thenReturn(Optional.empty());
        Optional<PaymentStatusDto> result = paymentStatusService.findById("FAILED");
        assertFalse(result.isPresent());
        verify(paymentStatusRepository, times(1)).findById("FAILED");
    }

    @Test
    void testSave() {
        when(paymentStatusMapper.toEntity(paymentStatusDto)).thenReturn(paymentStatusEntity);
        when(paymentStatusRepository.save(paymentStatusEntity)).thenReturn(paymentStatusEntity);
        when(paymentStatusMapper.toDto(paymentStatusEntity)).thenReturn(paymentStatusDto);
        PaymentStatusDto result = paymentStatusService.save(paymentStatusDto);
        assertNotNull(result);
        assertEquals(paymentStatusDto.status(), result.status());
        verify(paymentStatusMapper, times(1)).toEntity(paymentStatusDto);
        verify(paymentStatusRepository, times(1)).save(paymentStatusEntity);
        verify(paymentStatusMapper, times(1)).toDto(paymentStatusEntity);
    }

    @Test
    void testUpdate() {
        when(paymentStatusMapper.toEntity(paymentStatusDto)).thenReturn(paymentStatusEntity);
        when(paymentStatusRepository.save(paymentStatusEntity)).thenReturn(paymentStatusEntity);
        when(paymentStatusMapper.toDto(paymentStatusEntity)).thenReturn(paymentStatusDto);
        PaymentStatusDto result = paymentStatusService.update("COMPLETED", paymentStatusDto);
        assertNotNull(result);
        assertEquals(paymentStatusDto.status(), result.status());
        verify(paymentStatusRepository, times(1)).save(paymentStatusEntity);
        verify(paymentStatusMapper, times(1)).toDto(paymentStatusEntity);
    }

    @Test
    void testDelete() {
        paymentStatusService.delete("COMPLETED");
        verify(paymentStatusRepository, times(1)).deleteById("COMPLETED");
    }
}
