import java.util.List;
import java.util.Optional;

public class Pizzeria {

    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("Valeria", "Domicilio", "992-229"),
                new Pedido("Isela", "Local", null),
                new Pedido("Carlos", "Domicilio", null),
                new Pedido("Arleth", "Domicilio", "992-526"),
                new Pedido("Jeni", "Domicilio", "992-645")
        );

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream)
                .map(telefono -> "Confirmación enviada al número: " + telefono)
                .forEach(System.out::println);
    }
}