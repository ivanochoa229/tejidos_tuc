package com.tejidos.persistence.entity;

import com.tejidos.persistence.entity.embedded.SaleItemId;
import jakarta.persistence.*;

@Entity
@Table(name = "sales_items")
public class SaleItem {
    @EmbeddedId
    private SaleItemId saleItemId;

    @ManyToOne
    @MapsId("id_sale")
    @JoinColumn(name = "id_sale", referencedColumnName = "id_sale")
    private Sale sale;

    @ManyToOne
    @MapsId("id_item")
    @JoinColumn(name = "id_item", referencedColumnName = "id_item")
    private Item item;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double subtotal;

    public SaleItem() {
    }

    public SaleItem(Sale sale, Item item, double quantity, double price, double subtotal) {
        this.sale = sale;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.saleItemId = new SaleItemId(sale.getIdSale(), item.getIdItem());
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public SaleItemId getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(SaleItemId saleItemId) {
        this.saleItemId = saleItemId;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
