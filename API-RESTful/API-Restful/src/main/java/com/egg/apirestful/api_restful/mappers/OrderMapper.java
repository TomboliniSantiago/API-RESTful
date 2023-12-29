package com.egg.apirestful.api_restful.mappers;

import com.egg.apirestful.api_restful.dto.OrderDTO;
import com.egg.apirestful.api_restful.entities.Order;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDTO convertToDto(Order order){
        return this.modelMapper.map(order, OrderDTO.class);
    }

    public Order convertToEntity(OrderDTO orderDTO){
        return this.modelMapper.map(orderDTO, Order.class);
    }

    public List<OrderDTO> convertToDtoList(List<Order> products) {
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
