package com.empresa.strategy;

import com.empresa.model.Empleado;

public class Calculadora {
    
    private CalculoSalarioStrategy estrategia;

    public void setEstrategia(CalculoSalarioStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double ejecutarCalculo(Empleado emp) {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha definido ninguna estrategia.");
        }
        return estrategia.calcularSalario(emp);
    }
}