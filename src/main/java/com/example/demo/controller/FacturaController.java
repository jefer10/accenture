package com.example.demo.controller;

import com.example.demo.repository.entity.Factura;

import com.example.demo.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/save")
    public ResponseEntity<Factura> save(@RequestBody Factura factura){
        return new ResponseEntity<>(facturaService.save(factura), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Factura> update(@RequestBody Factura factura){
        return new ResponseEntity<>(facturaService.update(factura),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Factura> delete(@PathVariable("id")int id){
        return new ResponseEntity<>(facturaService.delete(id),HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Factura>> facturaAll(){
        return new ResponseEntity<>(facturaService.facturasAll(),HttpStatus.OK);
    }
}
