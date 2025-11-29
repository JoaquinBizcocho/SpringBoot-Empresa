package com.empresa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    private String dni;
    
    private String nombre;
    private char sexo;
    private int categoria;
    private int anios; 
    
    
    public Empleado() {}
    

    public String getDni() { 
    	return dni; 
    	}
    
    public void setDni(String dni) { 
    	this.dni = dni; 
    	}
    
    public String getNombre() { 
    	return nombre; 
    	}
    
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	}
    
    public char getSexo() { 
    	return sexo; 
    	}
    
    public void setSexo(char sexo) { 
    	this.sexo = sexo; 
    	}
    
    public int getCategoria() { 
    	return categoria; 
    	}
    
    public void setCategoria(int categoria) { 
    	this.categoria = categoria; 
    	}
    
    public int getAnios() { 
    	return anios; 
    	}
    
    public void setAnios(int anios) { 
    	this.anios = anios; 
    	}
}