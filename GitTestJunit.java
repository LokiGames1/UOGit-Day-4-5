import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class GitTestJunit {
    private Git index;

    @Before
    public void setUp() {
        index = new Git();
    }

    @Test
    public void testAdd() throws NoSuchAlgorithmException, IOException {
        String fileName = "testGit.txt";
        index.add(fileName);

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
        // Asserting if there exists the line on the index file where the blob was
        // added. Test does not pass.
    }

    @Test
    public void testDelete() throws NoSuchAlgorithmException, IOException {
        String fileName = "testGit.txt";
        index.add(fileName);
        index.delete(fileName);

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
        // tests whether or not the delete method works by attempting to delete a file
        // and checking to see if it's still there. Test does not pass
    }

    @Test
    public void testExistsAlready() throws IOException {
        String fileName = "testGit.txt";
        String hash = "someHash";
        File tempGitFile = new File("Git");
        FileWriter fileWriter = new FileWriter(tempGitFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(fileName + " : " + hash + '\n');
        bufferedWriter.close();
        fileWriter.close();

        boolean exists = index.existsAlready(fileName, hash);

        tempGitFile.delete();

        assertTrue(exists);
        // Tested the ExistsAlready method in the index class by checking whether a file
        // with the given fileName and hash already exists in a "Git" file. Test passes
    }

    @Test
    public void testInit() {
        try {
            index.init();
        } catch (IOException e) {

            e.printStackTrace();
        }
        assertTrue(new File("Git").exists());
        assertTrue(new File("./objects").exists());
        // tests init to make sure that initializing an index works and the file is
        // there. Test passes
    }
}
