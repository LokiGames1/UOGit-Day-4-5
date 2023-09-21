// public class TreeTeseter {
//     // public static void main(String[] args) {
//     //     // Tree tree = new Tree();
//     //     // tree.add("file1.txt");
//     //     // tree.add("file2.txt");
//     //     // tree.add("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//     //     // tree.printTree();

//     //     // System.out.println("\nRemoving 'file1.txt' and 'bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b':");
//     //     // tree.remove("file1.txt");
//     //     // tree.remove("bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//     //     // tree.printTree();

//     //     // System.out.println("\nGenerating Blob:");
//     //     // tree.generateBlob();
//     // }

// }import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class TreeTester {
    private Tree tree;

    @Before
    public void setUp() {
        tree = new Tree();
    }

    @Test
    public void testAdd() {
        // Test adding an entry to the tree
        tree.add("entry1");
        assertTrue(tree.entries.contains("entry1"));
    }

    @Test
    public void testRemove() {
        // Test removing an entry from the tree
        tree.add("entry1");
        tree.add("entry2");
        tree.remove("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }

    @Test
    public void testGenerateBlob() {
        // Test generating a blob and checking if it exists
        tree.add("entry1");
        tree.generateBlob();
        assertTrue(new File("objects").exists()); // Check if objects folder exists
        String sha1 = tree.calculateSHA1("entry1\n");
        String blobFileName = "objects" + File.separator + sha1;
        assertTrue(new File(blobFileName).exists()); // Check if blob file exists
    }

    // You can write more test methods to cover other functionalities as needed

    @Test
    public void testDelete() {
        // Test deleting an entry from the tree
        tree.add("entry1");
        tree.add("entry2");
        tree.delete("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
    }
}
