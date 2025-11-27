package com.empresa.service.strategy;

import com.empresa.model.Empleado;

// Interfaz que define la estrategia de cálculo del salario
public interface CalculoSalarioStrategy {
    double calcularSalario(Empleado empleado);
}