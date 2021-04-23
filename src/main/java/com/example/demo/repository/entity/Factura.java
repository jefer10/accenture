package com.example.demo.repository.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "factura")
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "cliente_id",insertable = true, updatable = false)
    private Cliente cliente;

    private Double compra;
    private Double iva;
    private Double domicilio;
    private Double total;
    private LocalDateTime fecha;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCompra() {
        return compra;
    }

    public void setCompra(Double compra) {
        this.compra = compra;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Double domicilio) {
        this.domicilio = domicilio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", productos=" + productos +
                ", cliente=" + cliente +
                ", compra=" + compra +
                ", iva=" + iva +
                ", domicilio=" + domicilio +
                ", total=" + total +
                ", fecha=" + fecha +
                '}';
    }
}
