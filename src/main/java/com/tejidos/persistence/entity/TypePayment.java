package com.tejidos.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "types_payment")
public class TypePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_payment_seq_gen")
    @SequenceGenerator(name = "type_payment_seq_gen", sequenceName = "types_payment_id_seq", allocationSize = 1)
    @Column(name = "id_type_payment")
    private Long idTypePayment;
    @Column(name = "type_payment")
    private String typePayment;

    public TypePayment() {
    }

    public TypePayment(Long idTypePayment) {
        this.idTypePayment = idTypePayment;
    }

    public Long getIdTypePayment() {
        return idTypePayment;
    }

    public void setIdTypePayment(Long idTypePayment) {
        this.idTypePayment = idTypePayment;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

}
