package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedu.inventario.model.Producto;
import com.bedu.inventario.repository.ProductoRepository;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {
            // Guardar productos
            repository.save(new Producto("Monitor Huawei MateView", "Diseño minimalista", 10500.00));
            repository.save(new Producto("Mouse HP Dual Mode", "Mouse inalámbrico", 699.00));
            repository.save(new Producto("Laptop Lenovo IdeaPad 5", "Ryzen 5, 8GB RAM", 13900.00));
            repository.save(new Producto("Lenovo Tab P11 Pro", "Snapdragon 730G", 8499.00));

            System.out.println(" Productos con precio mayor a 500:");
            repository.findByPrecioGreaterThan(500).forEach(System.out::println);

            System.out.println("\n Productos que contienen 'lap':");
            repository.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

            System.out.println("\n Productos con precio entre 1000 y 10000:");
            repository.findByPrecioBetween(1000, 10000).forEach(System.out::println);

            System.out.println("\n Productos cuyo nombre empieza con 'm':");
            repository.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}
