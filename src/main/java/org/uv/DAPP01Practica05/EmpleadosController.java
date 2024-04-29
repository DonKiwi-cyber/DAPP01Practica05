/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package org.uv.DAPP01Practica05;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author ian
 */
@RestController
@RequestMapping("/empleado")
public class EmpleadosController {
    
    @Autowired
    private EmpleadoRepository repo;
    
    @GetMapping("/all")
    public List<Empleado> list() {
        return repo.findAll();
    }
    
    @GetMapping("/empleado/{id}")
    public Empleado get(@PathVariable Long id) {
        Optional<Empleado> opcional = repo.findById(id);
        if(opcional.isPresent())
            return opcional.get();
        else
            return null;
    }
    
    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado input) {
        Optional<Empleado> opcional = repo.findById(id);
        if(opcional.isPresent()){
            input.setClave(id);
            repo.deleteById(id);
            repo.save(input);
            return ResponseEntity.ok(input);
        }
        else{
            return null;
        }
    }
    
    @PostMapping("/empleado")
    public ResponseEntity<Empleado> post(@RequestBody Empleado input) {
        repo.save(input);
        if(repo.findById(input.getClave()).isPresent()){
            return ResponseEntity.ok(input);
        }
        else{
            return null;
        }
    }
    
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Optional<Empleado> opcional = repo.findById(id);
        if(opcional.isPresent()){
            repo.deleteById(id);
            return ResponseEntity.ok(id);
        }
        else{
            return null;
        }
    }
    
}
