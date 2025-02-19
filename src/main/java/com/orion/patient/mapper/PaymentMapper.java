package com.orion.patient.mapper;

import com.orion.patient.dto.PaymentDto;
import com.orion.patient.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaymentTypeMapper.class, PaymentStatusMapper.class})
public interface PaymentMapper {

    @Mapping(target = "statusName", source = "paymentStatus.status")
    @Mapping(target = "paymentType", source = "paymentType.type")
    @Mapping(target = "appointmentId", source = "appointment.id")
    PaymentDto toDto(PaymentEntity paymentEntity);

    @Mapping(target = "paymentStatus.status", source = "statusName")
    @Mapping(target = "paymentType.type", source = "paymentType")
    @Mapping(target = "appointment.id", source = "appointmentId")
    PaymentEntity toEntity(PaymentDto paymentDTO);
}
