package com.example.demo.service;

import com.example.demo.repository.FacturaRepository;
import com.example.demo.repository.crud.ProductoCrud;
import com.example.demo.repository.entity.Factura;
import com.example.demo.repository.entity.Producto;
import org.jboss.jandex.PrimitiveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    private final double DOMICIOLIOS= 10000;
    private final double VALOR_MINIMO=70000;
    private final double VALOR_MAXIMO=100000;

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ProductoCrud productoCrud;

    public Factura save(Factura factura){
        List<Producto>productos= factura.getProductos();
        Double compra=valor(productos,factura);
        factura.setCompra(compra);
        factura.setIva(compra*0.19);
        factura.setDomicilio(valorDomicio(compra));
        factura.setTotal(factura.getCompra()+factura.getIva()+ factura.getDomicilio());
        factura.setFecha(LocalDateTime.now());
        return facturaRepository.save(factura);
    }

    public Factura update(Factura factura){
        Factura facturaVieja= facturaRepository.findFactura(factura.getId()).get();
        LocalDateTime horaDeCreacion=facturaVieja.getFecha();
        if(horaDeCreacion.plusHours(5).isBefore(LocalDateTime.now())){
            double valor=valor(factura.getProductos(),factura);
            if (valor>=facturaVieja.getCompra()){
                factura.setCompra(valor);
                return facturaRepository.save(factura);
            }
        }
        return facturaVieja;
    }

    public Factura delete(int id){
        Factura facturaVieja= facturaRepository.findFactura(id).get();
        LocalDateTime horaDeCreacion=facturaVieja.getFecha();
        if(horaDeCreacion.plusHours(12).isBefore(LocalDateTime.now())){
            facturaRepository.delete(id);
            return null;
        }
        facturaVieja.setProductos(new ArrayList());
        facturaVieja.setDomicilio(0.0);
        double valorViejo=facturaVieja.getCompra();
        facturaVieja.setIva(0.0);
        facturaVieja.setCompra(valorViejo*0.1);
        facturaVieja.setTotal(facturaVieja.getCompra());
        return facturaRepository.save(facturaVieja);

    }

    public List<Factura> facturasAll(){
        return facturaRepository.findAll();
    }

    private double valor(List<Producto> productoList,Factura factura){
        double precio=0;
        int i=0;
        for (Producto producto:productoList) {
            System.out.println("#################################"+i);
            /*
            if (producto.getId() == null) {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+i);
                //producto.setFactura(factura);
                productoCrud.save(producto);
                System.out.println(i);
            }*/
            precio=producto.getValor()+precio;
        }
        return precio;
    }
    private double valorDomicio(double compra){
        if (compra>VALOR_MINIMO && compra<VALOR_MAXIMO){
            return DOMICIOLIOS;
        }
        return 0;
    }


}
