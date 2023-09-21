import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class BlobTester {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        Index indy = new Index();
        indy.delete("test.txt");

    }
}