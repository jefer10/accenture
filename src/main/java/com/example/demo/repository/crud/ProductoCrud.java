package com.example.demo.repository.crud;

import com.example.demo.repository.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoCrud extends JpaRepository<Producto,Integer> {
}
