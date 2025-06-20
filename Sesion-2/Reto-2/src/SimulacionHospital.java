import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {

    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        Runnable tarea1 = () -> salaCirugia.usar("Dra. Pérez");
        Runnable tarea2 = () -> salaCirugia.usar("Dra. Alvarez");
        Runnable tarea3 = () -> salaCirugia.usar("Enf. Damián");
        Runnable tarea4 = () -> salaCirugia.usar("Dr. Salud");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(tarea1);
        executor.execute(tarea2);
        executor.execute(tarea3);
        executor.execute(tarea4);

        executor.shutdown();
    }
}
