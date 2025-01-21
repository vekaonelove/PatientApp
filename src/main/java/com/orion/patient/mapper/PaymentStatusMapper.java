package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentStatusDTO;
import com.orion.patient.entity.PaymentStatusEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentStatusMapper {
    PaymentStatusDTO toDTO(PaymentStatusEntity paymentStatusEntity);
    PaymentStatusEntity toEntity(PaymentStatusDTO paymentStatusDTO);
}
