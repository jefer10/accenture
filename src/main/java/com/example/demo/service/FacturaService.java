package com.example.demo.service;

import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.crud.FacturaCrud;
import com.example.demo.repository.crud.ProductoCrud;
import com.example.demo.repository.entity.Cliente;
import com.example.demo.repository.entity.Factura;
import com.example.demo.repository.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacturaService {

    private final double DOMICIOLIOS= 10000;
    private final double VALOR_MINIMO=70000;
    private final double VALOR_MAXIMO=100000;

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ProductoCrud productoCrud;
    @Autowired
    private FacturaCrud facturaCrud;

    public Factura save(Factura factura){
        Cliente cliente=factura.getCliente();
        factura.setCliente(cliente);
        facturaRepository.save(factura);
        List<Producto>productos= factura.getProductos();
        Double compra=valor(productos,factura);
        factura.setProductos(productos);
        factura.setCompra(compra);
        factura.setIva(compra*0.19);
        factura.setDomicilio(valorDomicio(compra));
        factura.setTotal(factura.getCompra()+factura.getIva()+ factura.getDomicilio());
        factura.setFecha(LocalDateTime.now());
        factura.setProductos(productos);
        return facturaRepository.save(factura);
    }

    public Factura update(Factura factura){
        Factura facturaVieja= facturaRepository.findFactura(factura.getId()).get();
        LocalDateTime horaDeCreacion=facturaVieja.getFecha();
        if(LocalDateTime.now().isBefore(horaDeCreacion.plusHours(5))){
            double valor=valorupdate(factura.getProductos());
            if (valor>=facturaVieja.getCompra()){
                factura.setCompra(valor);
                nuevosproductos(factura.getProductos(),factura);
                factura.setIva(factura.getCompra()*0.19);
                factura.setDomicilio(valorDomicio(factura.getCompra()));
                factura.setTotal(factura.getCompra()+factura.getIva()+factura.getDomicilio());
                return facturaRepository.save(factura);
            }
        }
        return facturaVieja;
    }

    public Factura delete(int id){
        Factura facturaVieja= facturaRepository.findFactura(id).get();
        LocalDateTime horaDeCreacion=facturaVieja.getFecha();
        if(LocalDateTime.now().isBefore(horaDeCreacion.plusHours(12))){
            productoCrud.deleteAll(facturaVieja.getProductos());
            if(facturaRepository.delete(id)){
                return null;
            }
            return facturaVieja;
        }else {
            facturaVieja.setProductos(null);
            facturaVieja.setDomicilio(0.0);
            double valorViejo=facturaVieja.getCompra();
            facturaVieja.setIva(0.0);
            facturaVieja.setCompra(valorViejo*0.1);
            facturaVieja.setTotal(facturaVieja.getCompra());
            return facturaRepository.save(facturaVieja);
        }
    }

    public List<Factura> facturasAll(){
        return facturaRepository.findAll();
    }

    private double valor(List<Producto> productoList,Factura factura){
        double precio=0;
        for (Producto producto:productoList) {
            if (producto.getId() == null) {
                producto.setFactura(factura);
                productoCrud.save(producto);
            }
            precio=producto.getValor()+precio;
        }
        return precio;
    }
    private double valorupdate(List<Producto> productoList){
        double precio=0;
        for (Producto producto:productoList) {
            precio=producto.getValor()+precio;
        }
        return precio;
    }

    private List<Producto> nuevosproductos(List<Producto> productos,Factura factura){
        for (Producto producto:productos) {
            producto.setFactura(factura);
            productoCrud.save(producto);
        }
        return productos;
    }

    private double valorDomicio(double compra){
        if (compra>VALOR_MINIMO && compra<VALOR_MAXIMO){
            return DOMICIOLIOS;
        }
        return 0;
    }


}
