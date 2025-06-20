import java.util.concurrent.TimeUnit;
public class main {
    public static void main(String[] args) {
        MovilidadApp.combinarOperaciones().thenAccept(System.out::println);
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}