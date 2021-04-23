package com.example.demo.repository;

import com.example.demo.repository.crud.ProductoCrud;
import com.example.demo.repository.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    @Autowired
    private ProductoCrud productoCrud;

    public Producto save(Producto producto){
        return productoCrud.save(producto);
    }

    public Optional<Producto> findProducto(int id){
        return productoCrud.findById(id);
    }

    public List<Producto> findAll(){
        return productoCrud.findAll();
    }
    
    public Boolean delete(int id){
        productoCrud.deleteById(id);
        return !productoCrud.existsById(id);
    }

    public void deleteMany(List<Producto>productos){
        productoCrud.deleteAll(productos);
    }

}
