package com.egg.apirestful.api_restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    @NotBlank(message = "Cannot be blank")
    private String id;
    @NotBlank(message = "Cannot be blank")
    private String nombre;
    @NotBlank(message = "Cannot be blank")
    private Double precio;
    private Boolean active;

}
