import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class MovilidadApp {
    private static void latencia(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static CompletableFuture<String> calcularRuta(){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculando ruta...");
            // Simula la estimación de la tarifa (latencia de 1-2 segundos).
            latencia(3);
            return "Ruta Cunduacán -> Villahermosa";
        });
    }
    public static CompletableFuture<Double> estimarTarifa(){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Estimando tarifa...");
            latencia(5);

            return 30.00;
        });
    }

    public static CompletableFuture<String> combinarOperaciones(){
        return calcularRuta() .thenCombine(estimarTarifa(), (ruta, tarifa) -> {
                    return "Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa;
                })
                .exceptionally(ex -> {
                    return "Error: " + ex.getMessage();
                });
    }
}