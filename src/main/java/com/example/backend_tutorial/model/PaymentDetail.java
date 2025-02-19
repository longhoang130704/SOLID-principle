package com.example.backend_tutorial.model;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_details")
public class PaymentDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentOrder payment_id;

    @Type(value = JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private JsonNode detailData;
}
