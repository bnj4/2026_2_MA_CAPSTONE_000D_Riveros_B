package com.pcdoctor.backend.controller;

import com.pcdoctor.backend.model.Cliente;
import com.pcdoctor.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Registro o login rápido mediante credencial (RUT)
    @PostMapping
    public Cliente registrarOBuscarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.findByCredencialRut(cliente.getCredencialRut())
                .orElseGet(() -> clienteRepository.save(cliente));
    }
}