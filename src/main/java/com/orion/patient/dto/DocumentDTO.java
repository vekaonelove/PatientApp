package com.orion.patient.dto;

public record DocumentDTO(Long id, DocumentTypeDTO typeName, byte[] content) { }
