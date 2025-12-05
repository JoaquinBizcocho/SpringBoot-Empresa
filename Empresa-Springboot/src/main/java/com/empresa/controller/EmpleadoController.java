package com.empresa.controller;

import com.empresa.model.Empleado;
import com.empresa.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service){ 
        this.service = service; 
    }

    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    @GetMapping("/listar")
    public String listar(Model model){ 
        model.addAttribute("lista", service.listarEmpleados());
        return "listar"; 
    }
    
    @GetMapping("/editar-empleado") 
    public String mostrarFormularioEditar(
            @RequestParam(value = "dni", required = false) String dni, 
            Model model){
        
        if (dni != null && !dni.isEmpty()) {
            Optional<Empleado> empleadoOpt = service.buscarEmpleadoPorDni(dni);
            
            if (empleadoOpt.isPresent()) {
                model.addAttribute("empleado", empleadoOpt.get());
            } else {
                model.addAttribute("mensaje", "No se encontró ningún empleado con el DNI: " + dni);
                model.addAttribute("empleado", new Empleado()); 
            }
        } else {
            model.addAttribute("empleado", new Empleado());
        }
        
        return "editar"; 
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Empleado e){ 
        service.guardarOActualizar(e);
        return "redirect:/listar"; 
    }

    @GetMapping("/buscar-sueldo")
    public String mostrarFormularioBuscarSueldo() {
        return "buscar-sueldo";
    }

    @GetMapping("/calcular-sueldo-dni")
    public String calcularSueldoDni(@RequestParam String dni, Model model) {
        Optional<Empleado> empleadoOpt = service.buscarEmpleadoPorDni(dni);
        
        if (empleadoOpt.isPresent()) {
            Empleado empleado = empleadoOpt.get();
            double sueldoCalculado = service.calcularSueldo(empleado);
            
            model.addAttribute("empleado", empleado);
            model.addAttribute("sueldoBase", sueldoCalculado);
        } else {
            model.addAttribute("mensaje", "No se encontró ningún empleado con ese DNI.");
        }
        
        return "buscar-sueldo"; 
    }
}