import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class BlobTestJunit {
    private Blob blob;
    private String testFileName = "test.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Clear the Objects directory
        Path objectsFolder = Paths.get("Objects");
        if (Files.exists(objectsFolder)) {
            try (Stream<Path> paths = Files.walk(objectsFolder)) {
                paths.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        }

        Path indexFile = Paths.get("index.txt");
        Files.deleteIfExists(indexFile);

        // Create the test.txt file with some content
        Files.write(Paths.get("test.txt"), "Test file content".getBytes());

        Files.write(Paths.get("file1.txt"), "File 1 content".getBytes());
        Files.write(Paths.get("file2.txt"), "File 2 content".getBytes());

        blob = new Blob("test.txt");
    }

    @Test
    void testDoSha() {
        String expectedSha = "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d"; // Correct SHA-1 hash for "hello"
        String input = "hello";
        String actualSha = blob.doSha(input);
        assertEquals(expectedSha, actualSha);
    }

    @Test
    void testGetFileContents() throws IOException {
        String expectedContents = "Test file content";
        String actualContents = (String) blob.getFileContents();
        assertEquals(expectedContents, actualContents);
    }

    @Test
    void testGetShaName() {
        String expectedSha = "3cddd3926cbb2787afc183c6da2b1d56161416af"; // Replace with the expected SHA-1 hash
        assertEquals(expectedSha, blob.getShaName());
    }

    @Test
    void testGetToTextFile() {
        Path expectedPath = Paths.get(testFileName);
        Path actualPath = blob.getToTextFile();
        assertEquals(expectedPath, actualPath);
    }

    @Test
    void testMakeBite() throws IOException {
        String expectedContents = "File 1 content";
        byte[] expectedBytes = expectedContents.getBytes();
        byte[] actualBytes = blob.makeBite("file1.txt");
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testMakeBlob() throws IOException {
        // Since Blob creation has already been tested in other methods,
        // you can test makeBlob by writing the Blob to a file and then checking its
        // existence.
        blob.makeBlob();
        String expectedSha = blob.getShaName();
        Path expectedPath = Paths.get("Objects", expectedSha);
        assertTrue(Files.exists(expectedPath));
    }

    @Test
    void testReadText() throws IOException {
        String expectedContents = "File 1 content";
        String actualContents = blob.readText("file1.txt");
        assertEquals(expectedContents, actualContents);
    }
}
