package com.egg.apirestful.api_restful.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Getter
    @ManyToOne
    @JoinColumn(name = "id")
    private Cliente cliente;

    @Getter
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date = LocalDate.now();

    @Getter
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    private boolean isActive;

    public Order() {
    }

    public void setIdOrder(String idOrder) {
        this.id = idOrder;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
