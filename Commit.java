import java.io.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Commit {
    private String treeSHA1;
    private String parentCommitSHA1;
    private String nextCommitSHA1;
    private String author;
    private String date;
    private String summary;

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Commit tester = new Commit("randomstringofletters", "", "Asher Engelberg", "Wow look at this awesome commit!");
        Commit test2 = new Commit("", "", "Poopy mcPooped my pants",
                "This one tests if both the parent and next commits are null");
        Commit test3 = new Commit("parentSHAPlaceholder", "NextSHAPlaceholder", "Ronald McDonald",
                "What if the Commit has both a previous and next commit");
        tester.writeToFile();
        test2.writeToFile();
        test3.writeToFile();

    }

    public Commit(String parentCommitSHA1, String nextCommit, String author, String summary)
            throws NoSuchAlgorithmException, IOException {
        this.nextCommitSHA1 = nextCommit;
        this.treeSHA1 = Commit.createTree();
        this.parentCommitSHA1 = parentCommitSHA1;
        this.author = author;
        this.date = getCurrentDate();
        this.summary = summary;
    }

    public String getTreeSHA1() {
        return treeSHA1;
    }

    public String getParentCommitSHA1() {
        return parentCommitSHA1;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String generateSHA1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String fileContents = treeSHA1 + "\n" + parentCommitSHA1 + "\n" + author + "\n" + date + "\n" + summary + "\n";
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(fileContents.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        return sdf.format(new Date());
    }

    public void writeToFile() throws IOException, NoSuchAlgorithmException {
        String sha1 = generateSHA1();
        String filePath = "objects/" + sha1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(treeSHA1 + "\n");
            if (parentCommitSHA1 != null) {
                writer.write(parentCommitSHA1 + "\n");
            }
            if (nextCommitSHA1 != null) {
                writer.write(nextCommitSHA1 + "\n");
            }
            writer.write(author + "\n");
            writer.write(date + "\n");
            writer.write(summary + "\n");
        }
    }

    // Create a Tree and return its SHA1
    public static String createTree() throws NoSuchAlgorithmException, IOException {
        Tree tree = new Tree();

        return tree.getSHA("tree.txt");
    }

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
}