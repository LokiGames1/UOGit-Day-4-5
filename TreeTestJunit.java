import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class TreeTestJunit {
    private Tree tree;

    @Before
    public void setUp() {
        this.tree = new Tree();
    }

    @Test
    public void testAdd() {
        tree.add("entry1");
        assertTrue(tree.entries.contains("entry1"));
    }

    @Test
    void testDelete() {
        tree.add("entry1");
        tree.add("entry2");
        tree.remove("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }

    @Test
    void testGenerateBlob() {
        tree.add("entry1");
        tree.generateBlob();
        assertTrue(new File("objects").exists());
        String sha1 = tree.calculateSHA1("entry1\n");
        String blobFileName = "objects" + File.separator + sha1;
        assertTrue(new File(blobFileName).exists());
    }

    @Test
    void testRemove() {
        tree.add("entry1");
        tree.add("entry2");
        tree.delete("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }
}
