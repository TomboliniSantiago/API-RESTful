package com.egg.apirestful.api_restful.entities;

import jakarta.persistence.*;
import jdk.jfr.Timespan;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Timespan
    private DateTimeLiteralExpression.DateTime fecha;

    @ManyToOne
    private Cliente cliente;
}
