import java.util.concurrent.Callable;

public class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Control térmico: Temperatura estable (22°C).";
    }
}