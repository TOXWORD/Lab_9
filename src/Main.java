import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            RegexPatcher rp = new RegexPatcher();
            rp.removeBrackets();
            rp.removeNumbers();
            rp.removeZero();
        } catch (FileNotFoundException e) {
            System.out.println("FNF");
        } catch (IOException e) {
            System.out.println("IOE");
        }
    }
}
