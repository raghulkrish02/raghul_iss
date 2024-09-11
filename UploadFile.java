package org.project;
import javax.crypto.SecretKey;
import java.io.FileWriter;
import java.io.IOException;

public class UploadFile {

    private static final String ACCESS_TOKEN = "sl.B8vchF7wr-1WbL4ntewpdPlRmk04UWMkaEzLozebeRVuy1mewYh93vAI1QEpGrQCIcUgB1JUIJdXc6vaIkfy8Zy5JnqJEOExtd96lgd-b2DBQk5gudsE8PLi66xYXIHoCkH3SurAwQ1fBbIXKP-N"; // Replace with your access token
    private static final String FILE_PATH = "/All files/sid.txt"; // Path on Dropbox
    private static final String KEY_FILE_PATH = "C:/Users/Raghul Krishnan B/Idea/s_key.txt"; // Path to store the key

    public static void main(String[] args) {
        try {
            // Create Dropbox client
            DropboxClient dropboxClient = new DropboxClient(ACCESS_TOKEN);

            // Generate a new encryption key
            SecretKey key = FileEncryptor.generateKey();
            String keyString = FileEncryptor.keyToString(key);

            // Encrypt data and upload the file
            String content = "hellooo everyone!";
            byte[] encryptedData = FileEncryptor.encrypt(content.getBytes(), key);
            dropboxClient.uploadFile(FILE_PATH, encryptedData);

            // Save the encryption key to a file
            try (FileWriter fileWriter = new FileWriter(KEY_FILE_PATH)) {
                fileWriter.write(keyString);
            }

            System.out.println("File uploaded and encrypted successfully.");
            System.out.println("Encryption key saved to file: " + KEY_FILE_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
