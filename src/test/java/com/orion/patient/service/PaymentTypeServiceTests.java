package com.orion.patient.service;

import com.orion.patient.dto.PaymentTypeDto;
import com.orion.patient.entity.PaymentTypeEntity;
import com.orion.patient.mapper.PaymentTypeMapper;
import com.orion.patient.repository.PaymentTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentTypeServiceTests {

    @Mock
    private PaymentTypeRepository paymentTypeRepository;

    @Mock
    private PaymentTypeMapper paymentTypeMapper;

    @InjectMocks
    private PaymentTypeService paymentTypeService;

    private PaymentTypeDto paymentTypeDto;
    private PaymentTypeEntity paymentTypeEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentTypeDto = new PaymentTypeDto("CREDIT_CARD");
        paymentTypeEntity = new PaymentTypeEntity("CREDIT_CARD");
    }

    @Test
    void testFindAll() {
        when(paymentTypeRepository.findAll()).thenReturn(List.of(paymentTypeEntity));
        when(paymentTypeMapper.toDto(paymentTypeEntity)).thenReturn(paymentTypeDto);
        List<PaymentTypeDto> result = paymentTypeService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(paymentTypeDto.type(), result.get(0).type());
        verify(paymentTypeRepository, times(1)).findAll();
        verify(paymentTypeMapper, times(1)).toDto(paymentTypeEntity);
    }

    @Test
    void testFindById() {
        when(paymentTypeRepository.findById("CREDIT_CARD")).thenReturn(Optional.of(paymentTypeEntity));
        when(paymentTypeMapper.toDto(paymentTypeEntity)).thenReturn(paymentTypeDto);
        Optional<PaymentTypeDto> result = paymentTypeService.findById("CREDIT_CARD");
        assertTrue(result.isPresent());
        assertEquals(paymentTypeDto.type(), result.get().type());
        verify(paymentTypeRepository, times(1)).findById("CREDIT_CARD");
        verify(paymentTypeMapper, times(1)).toDto(paymentTypeEntity);
    }

    @Test
    void testFindById_NotFound() {
        when(paymentTypeRepository.findById("DEBIT_CARD")).thenReturn(Optional.empty());
        Optional<PaymentTypeDto> result = paymentTypeService.findById("DEBIT_CARD");
        assertFalse(result.isPresent());
        verify(paymentTypeRepository, times(1)).findById("DEBIT_CARD");
    }

    @Test
    void testSave() {
        when(paymentTypeMapper.toEntity(paymentTypeDto)).thenReturn(paymentTypeEntity);
        when(paymentTypeRepository.save(paymentTypeEntity)).thenReturn(paymentTypeEntity);
        when(paymentTypeMapper.toDto(paymentTypeEntity)).thenReturn(paymentTypeDto);
        PaymentTypeDto result = paymentTypeService.save(paymentTypeDto);
        assertNotNull(result);
        assertEquals(paymentTypeDto.type(), result.type());
        verify(paymentTypeMapper, times(1)).toEntity(paymentTypeDto);
        verify(paymentTypeRepository, times(1)).save(paymentTypeEntity);
        verify(paymentTypeMapper, times(1)).toDto(paymentTypeEntity);
    }

    @Test
    void testUpdate() {
        when(paymentTypeMapper.toEntity(paymentTypeDto)).thenReturn(paymentTypeEntity);
        when(paymentTypeRepository.save(paymentTypeEntity)).thenReturn(paymentTypeEntity);
        when(paymentTypeMapper.toDto(paymentTypeEntity)).thenReturn(paymentTypeDto);
        PaymentTypeDto result = paymentTypeService.update("CREDIT_CARD", paymentTypeDto);
        assertNotNull(result);
        assertEquals(paymentTypeDto.type(), result.type());
        verify(paymentTypeRepository, times(1)).save(paymentTypeEntity);
        verify(paymentTypeMapper, times(1)).toDto(paymentTypeEntity);
    }

    @Test
    void testDelete() {
        paymentTypeService.delete("CREDIT_CARD");
        verify(paymentTypeRepository, times(1)).deleteById("CREDIT_CARD");
    }
}
