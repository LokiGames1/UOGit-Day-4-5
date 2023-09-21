import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class GitTest {
    private Git git;

    @Before
    public void setUp() {
        git = new Git();
    }

    @Test
    public void testInit() {
        // Ensure that the init method creates the necessary folders
        try {
            git.init();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue(new File("Git").exists());
        assertTrue(new File("./objects").exists());
    }

    @Test
    public void testAdd() throws NoSuchAlgorithmException, IOException {
        String fileName = "testGit.txt";
        git.add(fileName);

        BufferedReader br = new BufferedReader(new FileReader("Git"));
        boolean found = false;
        String line;
        while ((line = br.readLine()) != null) {
            String[] name = line.split("\\s+");
            if (name[0].equals(fileName)) {
                found = true;
                break;
            }
        }
        br.close();
        assertTrue(found);
    }

    @Test
    public void testDelete() throws NoSuchAlgorithmException, IOException {
        String fileName = "testGit.txt";
        git.add(fileName);
        git.delete(fileName);

        BufferedReader br = new BufferedReader(new FileReader("Git"));
        boolean found = false;
        String line;
        while ((line = br.readLine()) != null) {
            String[] name = line.split("\\s+");
            if (name[0].equals(fileName)) {
                found = true;
                break;
            }
        }
        br.close();
        assertFalse(found);
    }
}
