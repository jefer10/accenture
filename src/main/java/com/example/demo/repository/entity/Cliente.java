package com.example.demo.repository.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cedula;

    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getDirreccion() {
        return direccion;
    }

    public void setDirreccion(String dirreccion) {
        this.direccion = dirreccion;
    }

    /*
    public List<Factura> getFacturas() {
        return facturas;
    }
    */
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
