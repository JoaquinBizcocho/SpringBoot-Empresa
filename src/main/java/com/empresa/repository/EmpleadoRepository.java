package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.model.Empleado; // Importa tu clase Empleado

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
     
}