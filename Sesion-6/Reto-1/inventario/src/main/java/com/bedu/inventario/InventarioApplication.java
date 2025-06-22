
package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedu.inventario.model.Categoria;
import com.bedu.inventario.model.Marca;
import com.bedu.inventario.model.Producto;
import com.bedu.inventario.repository.CategoriaRepository;
import com.bedu.inventario.repository.MarcaRepository;
import com.bedu.inventario.repository.ProductoRepository;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
        System.out.println("Iniciando demo...");
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository productoRepo,
                                  CategoriaRepository categoriaRepo,
                                  MarcaRepository marcaRepo) {
        return (args) -> {
            Categoria tecnologia = new Categoria("Tecnología");
            categoriaRepo.save(tecnologia);

            Marca huawei = new Marca("Huawei");
            Marca hp = new Marca("HP");
            Marca lenovo = new Marca("Lenovo");

            marcaRepo.save(huawei);
            marcaRepo.save(hp);
            marcaRepo.save(lenovo);

            productoRepo.save(new Producto("Laptop Huawei MateBook X Pro", "Intel Core i7, 16GB RAM", 15000.00, tecnologia, huawei));
            productoRepo.save(new Producto("HP Smart Tank 7305", "Impresora multifuncional inalámbrica", 5700.00, tecnologia, hp));
            productoRepo.save(new Producto("Teclado Lenovo Go Wireless", "Teclado compacto inalámbrico con batería recargable", 1600.00, tecnologia, lenovo));

            System.out.println("Productos registrados:");
            productoRepo.findAll().forEach(p ->
                    System.out.println(p.getNombre() + " - " + p.getCategoria().getNombre())
            );

            System.out.println("Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println(" " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
