package com.tejidos.persistence.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SaleItemId implements Serializable {
    private Long id_sale;
    private Long id_item;

    public SaleItemId() {
    }

    public SaleItemId(Long idItem, Long id_sale) {
        this.id_item = idItem;
        this.id_sale = id_sale;
    }

    public Long getId_item() {
        return id_item;
    }

    public void setId_item(Long id_item) {
        this.id_item = id_item;
    }

    public Long getId_sale() {
        return id_sale;
    }

    public void setId_sale(Long id_sale) {
        this.id_sale = id_sale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleItemId that = (SaleItemId) o;
        return Objects.equals(id_sale, that.id_sale) && Objects.equals(id_item, that.id_item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_sale, id_item);
    }
}
