import java.util.concurrent.Callable;

public class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Soporte vital: Presión y oxígeno dentro de parámetros normales.";
    }
}