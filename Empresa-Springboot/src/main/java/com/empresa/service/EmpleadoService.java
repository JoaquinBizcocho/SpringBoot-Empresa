package com.empresa.service;

import com.empresa.model.Empleado;
import com.empresa.repository.EmpleadoRepository;
import com.empresa.strategy.Calculadora;
import com.empresa.strategy.CalculoSalarioCompleto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repo;

    public EmpleadoService(EmpleadoRepository repo){
        this.repo = repo;
    }

    public List<Empleado> listarEmpleados(){ 
        return repo.findAll(); 
    }
    
    public Optional<Empleado> buscarEmpleadoPorDni(String dni){
        return repo.findById(dni);
    }

    public Empleado guardarOActualizar(Empleado e){
        return repo.save(e);
    }

    public double calcularSueldo(Empleado e) {
        Calculadora calculadora = new Calculadora();
        calculadora.setEstrategia(new CalculoSalarioCompleto()); 
        return calculadora.ejecutarCalculo(e);
    }
}