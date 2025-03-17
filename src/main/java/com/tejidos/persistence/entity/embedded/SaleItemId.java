package com.tejidos.persistence.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SaleItemId implements Serializable {
    private Long idSale;
    private Long idItem;

    public SaleItemId() {
    }

    public SaleItemId(Long idItem, Long idSale) {
        this.idItem = idItem;
        this.idSale = idSale;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleItemId that = (SaleItemId) o;
        return Objects.equals(idSale, that.idSale) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSale, idItem);
    }
}
