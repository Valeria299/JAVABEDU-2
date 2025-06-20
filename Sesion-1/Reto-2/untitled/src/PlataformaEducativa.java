import java.util.*;
import java.util.function.Predicate;

public class PlataformaEducativa {

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
        System.out.println();
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("Duración total de videos: " + total + " minutos\n");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio ejercicio) {
                ejercicio.marcarRevisado();
            }
        }
        System.out.println();
    }

    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> criterio) {
        System.out.println("Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (criterio.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a climatología general", "Valeria", 15));
        materiales.add(new Video("Analísis de datos", "Carlos", 20));
        materiales.add(new Articulo("Ecuaciones Diferenciales", "Isela", 1200));
        materiales.add(new Articulo("Tipos de climas", "Luis José", 800));
        materiales.add(new Ejercicio("Radiación solar", "Alonso"));
        materiales.add(new Ejercicio("Ejercicios de ecuaciones diferenciales", "Valeria"));

        mostrarMateriales(materiales);

        List<Video> videos = materiales.stream()
                .filter(m -> m instanceof Video)
                .map(m -> (Video) m)
                .toList();
        contarDuracionVideos(videos);

        marcarEjerciciosRevisados(materiales);

        filtrarPorAutor(materiales, m -> m.getAutor().equals("Valeria"));
    }
}
