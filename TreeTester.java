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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class TreeTester {
    private Tree tree;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        tree = new Tree();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testAdd() {
        // Test adding an entry to the tree
        tree.add("entry1");
        assertTrue(tree.entries.contains("entry1"));
        // Adds an entry to the tree and checks if it's there. Test passes
    }

    @Test
    public void testCalculateSHA1() {
        String input = "Hello, World!";
        String expectedHash = "a0a9f2a6772942557ab5355d76af442f8f65e01";
        String calculatedHash = tree.calculateSHA1(input);
        assertEquals(expectedHash, calculatedHash);
        // Knows an expected hash for an input and checks the calculate Sha method. Test
        // passes
    }

    @Test
    public void testDelete() {
        tree.add("entry1");
        tree.add("entry2");
        tree.remove("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
        // attempts to remove an entry from the tree and checks if it's still there.
        // Test passes
    }

    @Test
    public void testGenerateBlob() {
        tree.add("entry1");
        tree.generateBlob();
        assertTrue(new File("objects").exists());
        String sha1 = tree.calculateSHA1("entry1\n");
        String blobFileName = "objects" + File.separator + sha1;
        assertTrue(new File(blobFileName).exists());
    }

    @Test
    public void testPrintTree() {
        tree.add("Entry 1");
        tree.add("Entry 2");
        tree.add("Entry 3");

        tree.printTree();

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Entry 1\nEntry 2\nEntry 3";

        assertEquals(expectedOutput, printedOutput);
        // Prints out the contents of a tree and checks it with the expected value. Does
        // not pass
    }

    @Test
    public void testRemove() {
        tree.add("entry1");
        tree.add("entry2");
        tree.delete("entry1");
        assertFalse(tree.entries.contains("entry1"));
        assertTrue(tree.entries.contains("entry2"));
        // I'm not sure what the remove method was as compared to the delete method in
        // the Tree class, but I did a similar test to the delete method, and this one
        // did not pass.
    }
}
