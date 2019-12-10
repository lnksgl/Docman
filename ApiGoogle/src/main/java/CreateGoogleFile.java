import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.omg.CORBA.portable.OutputStream;

public class CreateGoogleFile {

    // PRIVATE!
    private static File _createGoogleFile(String googleFolderIdParent, String contentType, //
                                          String customFileName, AbstractInputStreamContent uploadStreamContent) throws IOException {

        File fileMetadata = new File();
        fileMetadata.setName(customFileName);

        List<String> parents = Arrays.asList(googleFolderIdParent);
        fileMetadata.setParents(parents);
        //
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file = driveService.files().create(fileMetadata, uploadStreamContent)
                .setFields("id, webContentLink, webViewLink, parents").execute();

        return file;
    }

    // Create Google File from java.io.File
    public static File createGoogleFile(String googleFolderIdParent, String contentType, //
                                        String customFileName, java.io.File uploadFile) throws IOException {

        //
        AbstractInputStreamContent uploadStreamContent = new FileContent(contentType, uploadFile);
        //
        return _createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
    }

    public static void main(String[] args) throws IOException {

        String fileId = "1VraQ7gCjigUuPWeLYRJ9CCaQ_T96h9Zb";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GoogleDriveUtils.getDriveService().files().get(fileId)
                .executeMediaAndDownloadTo(outputStream);
    }

}
