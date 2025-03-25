package com.tejidos.persistence.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_gen")
    @SequenceGenerator(name = "payment_seq_gen", sequenceName = "payments_id_seq", allocationSize = 1)
    @Column(name = "id_payment")
    private Long idPayment;
    @Column(name = "total_payment")
    private Double totalPayment;
    @Column(name = "description_payment")
    private String descriptionPayment;
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "id_type_payment")
    private TypePayment typePayment;

    @OneToOne(mappedBy = "payment")
    private Sale sale;

    public Payment() {
        this.deleted = false;
    }

    public Payment(Double totalPayment, String descriptionPayment, TypePayment typePayment) {
        this.totalPayment = totalPayment;
        this.descriptionPayment = descriptionPayment;
        this.typePayment = typePayment;
        this.deleted = false;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public TypePayment getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(TypePayment typePayment) {
        this.typePayment = typePayment;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public TypePayment getTypePaymentList() {
        return typePayment;
    }

    public void setTypePaymentList(TypePayment typePaymentList) {
        this.typePayment = typePaymentList;
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
