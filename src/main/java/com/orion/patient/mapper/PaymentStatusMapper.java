package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentStatusDto;
import com.orion.patient.entity.PaymentStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentStatusMapper {
    PaymentStatusDto toDto(PaymentStatusEntity paymentStatusEntity);
    PaymentStatusEntity toEntity(PaymentStatusDto paymentStatusDTO);
}
