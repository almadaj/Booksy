package br.com.booksy.Booksy.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Drive getDriveService() throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("Booksy")
                .build();
    }

    public String uploadFile(String name, MultipartFile multipartFile) {
        try {
            Drive driveService = getDriveService();

            File fileMetadata = new File();
            fileMetadata.setName(name);

            InputStreamContent mediaContent = new InputStreamContent(
                    multipartFile.getContentType(),
                    multipartFile.getInputStream()
            );

            File uploadedFile = driveService.files()
                    .create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            return uploadedFile.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFile(String fileId) {
        try {
            Drive driveService = getDriveService();

            driveService.files().delete(fileId).execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generatePublicViewLink(String fileId) {
        try {
            Drive driveService = getDriveService();

            Permission permission = new Permission()
                    .setType("anyone")
                    .setRole("reader");

            driveService.permissions().create(fileId, permission)
                    .setFields("id")
                    .execute();

            return "https://drive.google.com/file/d/" + fileId + "/view";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
