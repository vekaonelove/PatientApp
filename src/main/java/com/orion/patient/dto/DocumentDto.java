package com.orion.patient.dto;

public record DocumentDto(Long id, DocumentTypeDto typeName, byte[] content) { }
