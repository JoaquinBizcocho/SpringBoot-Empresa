package com.empresa.service; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.empresa.model.Empleado; 
import com.empresa.service.strategy.CalculoSalarioStrategy;

@Service 
public class CalculadoraService {
    
    @Autowired
    private CalculoSalarioStrategy estrategia; 

    
    public double ejecutarCalculo(Empleado emp) {
        return estrategia.calcularSalario(emp);
    }
}