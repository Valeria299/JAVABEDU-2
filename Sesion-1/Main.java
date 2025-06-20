import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
        System.out.println();
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada personalizada) {
                personalizada.procesar(costoAdicional);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<OrdenMasa> ordenesMasa = Arrays.asList(
                new OrdenMasa("V299", 500),
                new OrdenMasa("V300", 900)
        );

        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
                new OrdenPersonalizada("C485", 100, "Isela Alvarez"),
                new OrdenPersonalizada("C486", 150, "Valeria Pérez")
        );

        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
                new OrdenPrototipo("D850", 10, "Diseño"),
                new OrdenPrototipo("D851", 5, "Pruebas")
        );

        mostrarOrdenes(ordenesMasa);
        mostrarOrdenes(ordenesPersonalizadas);
        mostrarOrdenes(ordenesPrototipo);

        procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        System.out.println("Resumen total de órdenes:");
        System.out.println("Producción en masa: " + ordenesMasa.size());
        System.out.println("Personalizadas: " + ordenesPersonalizadas.size());
        System.out.println("Prototipos: " + ordenesPrototipo.size());
    }
}
