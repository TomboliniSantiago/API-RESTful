package com.egg.apirestful.api_restful.dto;

import javax.validation.constraints.NotBlank;

public class ProductDTO {
    @NotBlank(message = "Cannot be blank")
    private String id;
    @NotBlank(message = "Cannot be blank")
    private String nombre;
    @NotBlank(message = "Cannot be blank")
    private Double precio;

}
