package com.empresa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // <-- Importación necesaria
import com.empresa.model.Empleado;
import com.empresa.repository.EmpleadoRepository;
import com.empresa.service.CalculadoraService; // <-- Importación necesaria (tu clase Strategy/Contexto)


@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository; 
    
    // INYECCIÓN DE LA LÓGICA DE NEGOCIO (EL SERVICIO QUE EJECUTA LA STRATEGY)
    @Autowired
    private CalculadoraService calculadoraService; 


    // =========================================================================
    // 1. LISTAR EMPLEADOS (Reemplaza a ListarCommand)
    // =========================================================================
    @GetMapping("/listar")
    public String listarEmpleados(Model model) { 
        List<Empleado> lista = empleadoRepository.findAll(); 
        model.addAttribute("lista", lista);
        return "views/listar"; 
    }
    
    // =========================================================================
    // 2. BUSCAR POR DNI (Reemplaza a BuscarPorDniCommand)
    // =========================================================================
    
    // Método para mostrar el formulario de búsqueda (si el usuario solo va a /empleados/buscar)
    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "views/buscarEmpleado"; 
    }
    
    /**
     * Procesa la búsqueda por DNI y calcula el sueldo base.
     */
    @GetMapping("/buscarDni") 
    public String buscarEmpleadoPorDni(
        @RequestParam("dni") String dni, // Obtiene el parámetro 'dni' del formulario/URL
        Model model
    ) {
        // Búsqueda del Empleado (reemplaza a dao.buscarPorDni(dni))
        Empleado empleado = empleadoRepository.findById(dni).orElse(null); 

        if (empleado != null) {
            
            // EJECUCIÓN DEL CÁLCULO DE SALARIO (Reemplaza a new Calculadora().setEstrategia().ejecutarCalculo())
            double sueldoCalculado = calculadoraService.ejecutarCalculo(empleado);
            
            // Se guardan los datos en el modelo para la vista JSP
            model.addAttribute("empleado", empleado);
            model.addAttribute("sueldoBase", sueldoCalculado);
        } else {
            // Si el empleado no existe, envía un mensaje de error
            model.addAttribute("error", "No se encontró ningún empleado con el DNI: " + dni);
        }

        // Se reenvía a la misma vista que tiene el formulario de búsqueda
        return "views/buscarEmpleado";
    }


    // =========================================================================
    // 3. EDICIÓN (Reemplaza a EditarPorDniCommand y ActualizarEmpleadoCommand)
    // =========================================================================
    
    /**
     * Muestra el formulario de edición con datos precargados.
     */
    @GetMapping("/editar/{dni}")
    public String mostrarFormularioEdicion(@PathVariable String dni, Model model) {
        
        Empleado empleado = empleadoRepository.findById(dni).orElse(null);

        if (empleado == null) {
            model.addAttribute("error", "Empleado no encontrado para edición.");
            return "views/error"; 
        }

        model.addAttribute("empleado", empleado);
        return "views/editarEmpelado"; 
    }
    
    /**
     * Procesa el formulario enviado y guarda los cambios en la BD.
     */
    @PostMapping("/actualizar")
    public String actualizarEmpleado(
        @ModelAttribute("empleado") Empleado empleadoActualizado, 
        Model model
    ) {
        try {
            empleadoRepository.save(empleadoActualizado);

            model.addAttribute("mensaje", "Empleado " + empleadoActualizado.getDni() + " actualizado con éxito.");

            // Redirigir al listado
            return "redirect:/empleados/listar"; 

        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar empleado: " + e.getMessage());
            return "views/editarEmpelado";
        }
    }
    
    // NOTA: Se ha añadido el cierre de la clase '}' que faltaba en tu código original.
}