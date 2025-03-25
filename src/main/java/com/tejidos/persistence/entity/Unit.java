package com.tejidos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_seq_gen")
    @SequenceGenerator(name = "unit_seq_gen", sequenceName = "units_id_seq", allocationSize = 1)
    @Column(name = "id_unit")
    private Long idUnit;
    @Column(name = "unit_name")
    private String unitName;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    public Unit() {
    }

    public Unit(Long idUnit) {
        this.idUnit = idUnit;
    }

    public Unit(String unitName) {
        this.unitName = unitName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(Long idUnit) {
        this.idUnit = idUnit;
    }
}
