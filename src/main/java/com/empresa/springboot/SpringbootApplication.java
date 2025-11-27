package com.empresa.springboot; // Asegúrate que este sea el paquete raíz

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esto habilita la configuración automática y el escaneo de componentes
public class SpringbootApplication {

    public static void main(String[] args) {
        // Este método es el punto de inicio de la aplicación Java estándar
        SpringApplication.run(SpringbootApplication.class, args);
    }
}