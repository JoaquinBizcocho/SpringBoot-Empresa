package com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.model.Nomina;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> { 

    Nomina findByDniN(String dni); 
    
}