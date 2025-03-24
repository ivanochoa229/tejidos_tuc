package com.tejidos.persistence.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long idPayment;
    @Column(name = "total_payment")
    private Double totalPayment;
    @Column(name = "description_payment")
    private String descriptionPayment;

    @ManyToOne
    @JoinColumn(name = "id_type_payment")
    private TypePayment typePaymentList;

    @OneToOne(mappedBy = "payment")
    private Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public TypePayment getTypePaymentList() {
        return typePaymentList;
    }

    public void setTypePaymentList(TypePayment typePaymentList) {
        this.typePaymentList = typePaymentList;
    }

    public String getDescriptionPayment() {
        return descriptionPayment;
    }

    public void setDescriptionPayment(String descriptionPayment) {
        this.descriptionPayment = descriptionPayment;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
