package org.project;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.SecretKey;

public class DownloadFile {

    private static final String ACCESS_TOKEN = "sl.B8vchF7wr-1WbL4ntewpdPlRmk04UWMkaEzLozebeRVuy1mewYh93vAI1QEpGrQCIcUgB1JUIJdXc6vaIkfy8Zy5JnqJEOExtd96lgd-b2DBQk5gudsE8PLi66xYXIHoCkH3SurAwQ1fBbIXKP-N"; // Replace with your access token
    private static final String FILE_PATH = "/All files/sid.txt"; // Path on Dropbox
    private static final String KEY_FILE_PATH = "C:/Users/Raghul Krishnan B/Idea/s_key.txt"; // Path to read the key from

    public static void main(String[] args) {
        try {
            // Create Dropbox client
            DropboxClient dropboxClient = new DropboxClient(ACCESS_TOKEN);

            // Read the encryption key from file
            String keyString = new String(Files.readAllBytes(Paths.get(KEY_FILE_PATH)));
            SecretKey key = FileEncryptor.stringToKey(keyString);

            // Download and decrypt the file
            byte[] downloadedData = dropboxClient.downloadFile(FILE_PATH);
            byte[] decryptedData = FileEncryptor.decrypt(downloadedData, key);

            System.out.println("Downloaded file content: " + new String(decryptedData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
