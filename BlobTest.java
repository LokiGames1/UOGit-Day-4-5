import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BlobTest {

    private Blob blob;
    private String testFileName = "test.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Delete the object folder and index file before each test
        Path objectsFolder = Paths.get("Objects");
        Files.deleteIfExists(objectsFolder);
        Path indexFile = Paths.get("index.txt");
        Files.deleteIfExists(indexFile);

        // Create and fill a few blank files
        Files.write(Paths.get("file1.txt"), "File 1 content".getBytes());
        Files.write(Paths.get("file2.txt"), "File 2 content".getBytes());

        // Create a Blob object for testing
        blob = new Blob(testFileName);
    }

    @Test
    public void testBlobCreation() {
        assertNotNull(blob);
    }

    @Test
    public void testBlobFileContents() throws IOException {
        String expectedContents = "File 1 content"; // Assuming this is the content of test.txt
        assertEquals(expectedContents, blob.getFileContents());
    }

    @Test
    public void testBlobShaName() {
        String expectedSha = "b34f0b9c16e7c397f9f2c5f96327c06d60a6fc66"; // Replace with the expected SHA-1 hash
        assertEquals(expectedSha, blob.getShaName());
    }

    @Test
    public void testAddFile() throws IOException {
        // Call add to add the Blob to the index and objects

        // Verify index is updated
        String indexContents = Files.readString(Paths.get("index.txt"));
        assertTrue(indexContents.contains(blob.getShaName()));

        // Verify objects are created
        Path objectFile = Paths.get("Objects", blob.getShaName());
        assertTrue(Files.exists(objectFile));
    }
}
