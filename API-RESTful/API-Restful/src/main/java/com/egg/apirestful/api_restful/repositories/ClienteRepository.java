package com.egg.apirestful.api_restful.repositories;

import com.egg.apirestful.api_restful.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
