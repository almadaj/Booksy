package br.com.booksy.Booksy.service;

import br.com.booksy.Booksy.domain.dto.BookUpload;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${google.drive.shared-folder-id}")
    private String sharedFolderId;

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

    public BookUpload uploadFile(String name, MultipartFile multipartFile) {
        try {
            Drive driveService = getDriveService();

            File fileMetadata = new File();
            fileMetadata.setName(name);
            fileMetadata.setParents(Collections.singletonList(sharedFolderId));

            InputStreamContent mediaContent = new InputStreamContent(
                    multipartFile.getContentType(),
                    multipartFile.getInputStream()
            );

            File uploadedFile = driveService.files()
                    .create(fileMetadata, mediaContent)
                    .setFields("id, webViewLink")
                    .execute();

            Permission permission = new Permission()
                    .setType("anyone")
                    .setRole("reader");

            driveService.permissions().create(uploadedFile.getId(), permission)
                    .setFields("id")
                    .execute();

            return new BookUpload(uploadedFile.getId(), uploadedFile.getWebViewLink());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(String fileId) {
        try {
            Drive driveService = getDriveService();

            driveService.files().delete(fileId).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
