package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentDTO;
import com.orion.patient.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "appointmentId", source = "appointment.id")
    PaymentDTO toDTO(PaymentEntity paymentEntity);

    @Mapping(target = "appointment.id", source = "appointmentId")
    PaymentEntity toEntity(PaymentDTO paymentDTO);
}
