package com.egg.apirestful.api_restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDTO {
    @NotBlank(message= "idCliente cannot be blank")
    private String id;
    @NotBlank(message = "phone cannot be blank")
    private String telefono;
    @NotBlank(message = "the name cannot be blank")
    private String name;
    @Email(message = "Invalid email")
    private String email;
}
