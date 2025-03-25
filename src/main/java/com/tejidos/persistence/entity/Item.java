package com.tejidos.persistence.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
    @SequenceGenerator(name = "item_seq_gen", sequenceName = "items_id_seq", allocationSize = 1)
    @Column(name = "id_item")
    private Long idItem;
    @Column(name = "description_item")
    private String descriptionItem;
    @Column(name = "price_item")
    private Double priceItem;
    @ManyToOne
    @JoinColumn(name = "id_unit")
    private Unit unit;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @Column(name = "quantity")
    private Double quantity;
    private Boolean deleted;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems;
    

    public Item() {
    }

    public Item(Long idItem) {
        this.idItem = idItem;
        this.deleted = false;
    }

    public Item(Category category, String descriptionItem, Double priceItem, Double quantity, Unit unit) {
        this.category = category;
        this.descriptionItem = descriptionItem;
        this.priceItem = priceItem;
        this.quantity = quantity;
        this.unit = unit;
        this.deleted = false;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Double priceItem) {
        this.priceItem = priceItem;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
