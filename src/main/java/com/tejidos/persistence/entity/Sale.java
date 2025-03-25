package com.tejidos.persistence.entity;

import com.tejidos.utils.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq_gen")
    @SequenceGenerator(name = "sale_seq_gen", sequenceName = "sales_id_seq", allocationSize = 1)
    @Column(name = "id_sale")
    private Long idSale;
    private Double total;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_payment", nullable = false)
    private Payment payment;

    public Sale() {
    }

    public Sale(Long idSale) {
        this.idSale = idSale;
    }

    public Sale(Client client, Status status, Double total) {
        this.client = client;
        this.status = status;
        this.total = total;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
