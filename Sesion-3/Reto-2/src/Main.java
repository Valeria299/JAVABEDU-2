import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Sucursal> sucursales = List.of(
                new Sucursal("Centro", List.of(
                        new Encuesta("Carlos", null, 4),
                        new Encuesta("Luis", "El tiempo de espera fue largo", 3),
                        new Encuesta("José", "null", 5),
                        new Encuesta("Max", "La atención fue mala, me contestaron con mala cara", 3)
                )),
                new Sucursal("Norte", List.of(
                        new Encuesta("Miguel", null, 4),
                        new Encuesta("Isela", null, 5),
                        new Encuesta("Valeria", "La atención fue buena", 2)
                ))
        );

        sucursales.stream()
                .flatMap(sucursal ->
                                sucursal.getEncuestas().stream()

                                        .filter(encuesta -> encuesta.getCalificacion() <= 3)

                                        .flatMap(encuesta -> encuesta.getComentario()
                                                        .filter(c -> !c.equalsIgnoreCase("null"))
                                                .map(comentario ->
                                                        "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + comentario + "\""
                                                )
                                                .stream()
                                        )
                )
                .forEach(System.out::println);
    }
}