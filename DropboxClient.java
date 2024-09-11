package org.project;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DropboxClient {

    private final DbxClientV2 client;

    public DropboxClient(String accessToken) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        this.client = new DbxClientV2(config, accessToken);
    }

    // Upload a file to Dropbox
    public FileMetadata uploadFile(String path, byte[] data) throws Exception {
        try (InputStream in = new ByteArrayInputStream(data)) {
            return client.files().uploadBuilder(path)
                    .withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(in);
        }
    }

    // Download a file from Dropbox
    public byte[] downloadFile(String path) throws Exception {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            client.files().downloadBuilder(path).download(out);
            return out.toByteArray();
        }
    }
}
