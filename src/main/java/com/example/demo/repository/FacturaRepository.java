package com.example.demo.repository;

import com.example.demo.repository.crud.FacturaCrud;
import com.example.demo.repository.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FacturaRepository {

    @Autowired
    private FacturaCrud facturaCrud;

    public Factura save(Factura factura){
        return facturaCrud.save(factura);
    }

    public Optional<Factura> findFactura(int id){
        return facturaCrud.findById(id);
    }

    public List<Factura> findAll(){
        return facturaCrud.findAll();
    }

    public  Boolean delete(int id){
        facturaCrud.deleteById(id);
        return !facturaCrud.existsById(id);
    }

}
