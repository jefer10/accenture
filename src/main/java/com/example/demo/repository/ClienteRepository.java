package com.example.demo.repository;

import com.example.demo.repository.crud.ClienteCrud;
import com.example.demo.repository.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    @Autowired
    private ClienteCrud clienteCrud;

    public Cliente save(Cliente cliente){
        return clienteCrud.save(cliente);
    }
    public Optional<Cliente> findCliente(int id){
        return clienteCrud.findById(id);
    }

    public List<Cliente> findAll(){
        return clienteCrud.findAll();
    }

    public Boolean delete(int id){
        clienteCrud.deleteById(id);
        return !clienteCrud.existsById(id);
    }
}
