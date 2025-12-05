package com.empresa.controller;

import com.empresa.model.Empleado;
import com.empresa.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados") // Prefijo para todas las rutas REST
public class EmpleadoRestController {

    private final EmpleadoService service;

    public EmpleadoRestController(EmpleadoService service) {
        this.service = service;
    }

    
    @GetMapping
    public List<Empleado> listarTodos() {
        return service.listarEmpleados();
    }

    
    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> obtenerPorDni(@PathVariable String dni) {
        Optional<Empleado> empleadoOpt = service.buscarEmpleadoPorDni(dni);
        return empleadoOpt.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    
    
    @PutMapping("/{dni}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        empleado.setDni(dni);
        
        if (service.buscarEmpleadoPorDni(dni).isPresent()) {
            Empleado actualizado = service.guardarOActualizar(empleado);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping("/{dni}/sueldo")
    public ResponseEntity<Double> calcularSueldo(@PathVariable String dni) {
        Optional<Empleado> empleadoOpt = service.buscarEmpleadoPorDni(dni);

        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            double sueldo = service.calcularSueldo(empleado);
            return ResponseEntity.ok(sueldo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}