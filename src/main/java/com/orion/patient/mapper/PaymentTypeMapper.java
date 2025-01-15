package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentTypeDTO;
import com.orion.patient.entity.PaymentTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentTypeDTO toDTO(PaymentTypeEntity paymentTypeEntity);
    PaymentTypeEntity toEntity(PaymentTypeDTO paymentTypeDTO);
}
