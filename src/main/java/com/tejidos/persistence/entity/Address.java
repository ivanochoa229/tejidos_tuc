package com.tejidos.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @SequenceGenerator(name = "address_seq_gen", sequenceName = "addresses_id_seq", allocationSize = 1)
    @Column(name = "id_address")
    private Long idAddress;
    private Long number;
    private String street;
    private String province;
    private String state;
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Address() {
    }

    public Address(Client client, Long number, String province, String state, String street) {
        this.client = client;
        this.number = number;
        this.province = province;
        this.state = state;
        this.street = street;
        this.deleted = false;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
