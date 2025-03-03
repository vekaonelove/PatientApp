package com.orion.patient.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic patientDataTopic() {
        return new NewTopic("patient-data", 1, (short) 1);
    }

    @Bean
    public NewTopic patientDocumentsTopic() {
        return new NewTopic("patient-documents", 1, (short) 1);
    }

    @Bean
    public NewTopic patientDiseasesTopic() {
        return new NewTopic("patient-diseases", 1, (short) 1);
    }

    @Bean
    public NewTopic patientPaymentsTopic() {
        return new NewTopic("patient-payments", 1, (short) 1);
    }

    @Bean
    public NewTopic patientEmergencyTopic() {
        return new NewTopic("patient-emergency", 1, (short) 1);
    }

    @Bean
    public NewTopic patientAppointmentsTopic() {
        return new NewTopic("patient-appointments", 1, (short) 1);
    }

    @Bean
    public NewTopic doctorClinicDataTopic() {
        return new NewTopic("doctor-clinic-data", 1, (short) 1);
    }

    @Bean
    public NewTopic authResponseTopic() {
        return new NewTopic("auth-response", 1, (short) 1);
    }
}