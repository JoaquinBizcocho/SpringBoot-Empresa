package com.empresa.strategy;

import com.empresa.model.Empleado;

public interface CalculoSalarioStrategy {
    double calcularSalario(Empleado empleado);
}