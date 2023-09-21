import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BlobTester {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Git gitty = new Git();
        gitty.add("test.txt");
        gitty.delete("test.txt");
    }
}