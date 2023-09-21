import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BlobTestJunit {
    private Blob blob;
    private String testFileName = "test.txt";

    @BeforeEach
    public void setUp() throws IOException {

        Path objectsFolder = Paths.get("Objects");
        Files.deleteIfExists(objectsFolder);
        Path indexFile = Paths.get("index.txt");
        Files.deleteIfExists(indexFile);

        Files.write(Paths.get("file1.txt"), "File 1 content".getBytes());
        Files.write(Paths.get("file2.txt"), "File 2 content".getBytes());

        blob = new Blob(testFileName);
    }

    @Test
    void testDoSha() {

    }

    @Test
    void testGetFileContents() {

    }

    @Test
    void testGetShaName() {
        String expectedSha = "b34f0b9c16e7c397f9f2c5f96327c06d60a6fc66"; // Replace with the expected SHA-1 hash
        assertEquals(expectedSha, blob.getShaName());
    }

    @Test
    void testGetToTextFile() {

    }

    @Test
    void testMakeBite() {

    }

    @Test
    void testMakeBlob() {

    }

    @Test
    void testReadText() {

    }
}
