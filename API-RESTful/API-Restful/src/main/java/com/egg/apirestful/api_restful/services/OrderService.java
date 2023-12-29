package com.egg.apirestful.api_restful.services;

import com.egg.apirestful.api_restful.dto.OrderDTO;
import com.egg.apirestful.api_restful.entities.Cliente;
import com.egg.apirestful.api_restful.entities.Order;
import com.egg.apirestful.api_restful.entities.Product;
import com.egg.apirestful.api_restful.exceptions.MyException;
import com.egg.apirestful.api_restful.mappers.OrderMapper;
import com.egg.apirestful.api_restful.repositories.ClienteRepository;
import com.egg.apirestful.api_restful.repositories.OrderRepository;
import com.egg.apirestful.api_restful.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductRepository productRepository;

    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDTO addOrder(OrderDTO orderDTO) throws MyException {

        validate(orderDTO);
        Order order = orderMapper.convertToEntity(orderDTO);
        Cliente cliente = clienteRepository.findById(order.getCliente()
                .getId()).orElse(null);

        order.setCliente(cliente);
        List<Product> products = new ArrayList<>();
        for (String productId : orderDTO.getProductsID()) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                products.add(product);
            }
        }
        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.convertToDto(savedOrder);
    }



    private void validate(OrderDTO orderDto) throws MyException {
        if (orderDto.getDate() == null) {
            throw new MyException("Date can't be null or empty");
        }
        if (orderDto.getCliente() == null) {
            throw new MyException("Orders must have a client");
        }
    }


}
