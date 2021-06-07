import java.io.IOException;

public class Functions {
    public void pause() {
        try {  
            Thread.sleep(1400);  
        }catch(InterruptedException e){
            System.out.println(e);    
        }    
    }

    public void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }
}
