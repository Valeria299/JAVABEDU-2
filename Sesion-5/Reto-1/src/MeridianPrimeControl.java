import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

public class MeridianPrimeControl {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Flujo de sensores de tráfico
        Flux<Integer> trafico = Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // Congestión 0-100%
                .filter(congestion -> congestion > 70) // Filtra congestión crítica
                .doOnNext(congestion -> System.out.println("Alerta: Congestión del " + congestion + "% en Avenida Arte"))
                .onBackpressureBuffer(5); // Simula backpressure

        // Flujo de contaminación del aire
        Flux<Integer> contaminacion = Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(80)) // PM2.5 entre 0-80 ug/m3
                .filter(pm -> pm > 50)
                .doOnNext(pm -> System.out.println("Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)"));

        // Flujo de accidentes viales
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    return prioridades[random.nextInt(prioridades.length)];
                })
                .filter(prioridad -> prioridad.equals("Alta"))
                .doOnNext(prioridad -> System.out.println(" Emergencia vial: Accidente con prioridad " + prioridad));

        // Flujo de trenes maglev
        Flux<Integer> trenes = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) // Retrasos de 0-10 minutos
                .filter(retraso -> retraso > 5)
                .doOnNext(retraso -> System.out.println("Tren maglev con retraso crítico: " + retraso + " minutos"))
                .onBackpressureBuffer(3); // Backpressure en trenes

        // Flujo de semáforos inteligentes (estado persistente)
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(estados.length)];
                })
                .transform(MeridianPrimeControl::controlarSemaforos); // Control de 3 rojos seguidos

        // Combinacion
        Flux.merge(trafico, contaminacion, accidentes, trenes, semaforos)
                .bufferTimeout(5, Duration.ofSeconds(2)) // Agrupar eventos por ventana de tiempo
                .filter(lista -> lista.size() >= 3) // Alerta global si hay >=3 eventos críticos juntos
                .doOnNext(lista -> System.out.println(" Alerta global: Múltiples eventos críticos detectados en Meridian Prime\n"))
                .subscribe();

        Thread.sleep(15000); // Mantener la aplicación activa
    }

    // Controlador de semáforos: detecta 3 rojos seguidos
    private static Flux<String> controlarSemaforos(Flux<String> flujo) {
        final int[] contadorRojos = {0};
        return flujo
                .filter(estado -> {
                    if (estado.equals("Rojo")) {
                        contadorRojos[0]++;
                        if (contadorRojos[0] >= 3) {
                            contadorRojos[0] = 0; // Reiniciar contador
                            return true; // Emitir alerta
                        }
                    } else {
                        contadorRojos[0] = 0; // Reiniciar si cambia de estado
                    }
                    return false; // No emitir alerta aún
                })
                .doOnNext(estado -> System.out.println("Semáforo en Rojo detectado 3 veces seguidas en cruce Norte"));
    }
}
