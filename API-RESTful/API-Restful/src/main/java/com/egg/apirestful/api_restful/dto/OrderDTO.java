package com.egg.apirestful.api_restful.dto;

import com.egg.apirestful.api_restful.entities.Cliente;
import com.egg.apirestful.api_restful.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String id;
    private Cliente cliente;
    private List<Product> products;
    private List<String> productsID;
    private LocalDate date;
    private Boolean active;
}
