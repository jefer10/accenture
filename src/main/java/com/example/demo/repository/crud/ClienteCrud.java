package com.example.demo.repository.crud;

import com.example.demo.repository.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCrud extends JpaRepository<Cliente,Integer> {
}
