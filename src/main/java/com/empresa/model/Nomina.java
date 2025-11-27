package com.empresa.model;

// Importaciones de JPA
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity 
@Table(name = "nominas") // Nombre de la tabla
public class Nomina {
    
    // El DNI es la CLAVE PRIMARIA (PK) de esta tabla, según tu captura.
    @Id 
    @Column(name = "dni") // Mapea al campo 'dni' de la tabla
    private String dniN; 
    
    // El sueldo, que es lo que calculamos o recuperamos.
    @Column(name = "sueldo")
    private Double sueldo;

    // Ya no necesitamos 'nombreEmpleado' en la entidad, ya que no se almacena aquí.
    
    // Constructores (vacío obligatorio para JPA)
    public Nomina() {}
    
    public String getDni() {
		return dniN;
	}
	

	public Double getSueldo() {
		return sueldo;
	}


}