import java.util.concurrent.*;

public class Main {
        public static void main(String[] args) throws InterruptedException, ExecutionException {
                ExecutorService executor = Executors.newFixedThreadPool(4);

                System.out.println("Simulación de misión espacial iniciada...");

//        Envia cada subsistema como tareea
                Future<String> navegacion = executor.submit(new SistemaNavegacion());
                Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());
                Future<String> controlTermico = executor.submit(new SistemaControlTermico());
                Future<String> soporteVital = executor.submit(new SistemaSoporteVital());

//        Imprimimos los resultados
                System.out.println(comunicaciones.get());
                System.out.println(soporteVital.get());
                System.out.println(navegacion.get());
                System.out.println(controlTermico.get());

//        Cerramos el executor
                executor.shutdown();
                System.out.println("Todos los pedidos fueron procesados.");


        }
}