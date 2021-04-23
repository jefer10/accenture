package com.example.demo.repository.crud;

import com.example.demo.repository.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaCrud extends JpaRepository<Factura,Integer> {
}
