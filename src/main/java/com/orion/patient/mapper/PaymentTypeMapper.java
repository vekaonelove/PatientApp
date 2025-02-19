package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentTypeDto;
import com.orion.patient.entity.PaymentTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentTypeDto toDto(PaymentTypeEntity paymentTypeEntity);
    PaymentTypeEntity toEntity(PaymentTypeDto paymentTypeDTO);
}
