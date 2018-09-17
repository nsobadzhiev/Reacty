package com.devmonologue.reacty;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.*;
import java.net.URI;
import java.util.UUID;

@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ImageUploadResource {

    private MemeStore reactionsStore = new MemeStore();

    @POST
    @PermitAll
    public Response uploadImage(
            @FormDataParam("file") final InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("name") final String imageName,
            @FormDataParam("tags") final String imageTags,
            @FormDataParam("link") final String link) {
        System.out.print("Uploading file...");
        String pwd = System.getProperty("user.dir");
        String name = null;
        if (fileDetail.getSize() != 0) {
            name = UUID.randomUUID().toString();
            String filePath = pwd + "/build/resources/main/images/" + name;
            File imageFile = new File(filePath);
            saveFile(fileInputStream, imageFile);
        }
        saveDBRecord(imageName, imageTags, name, link);

        URI uri = UriBuilder.fromUri("/start").build();
        return Response.seeOther(uri).build();
    }

    private void saveDBRecord(String imageName, String imageTags, String name, String link) {
        reactionsStore.saveReaction(imageName, imageTags, name, link);
    }

    private void saveFile(InputStream uploadedInputStream,
                          File serverLocation) {
        try {
            serverLocation.createNewFile();
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream outputStream = new FileOutputStream(serverLocation);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
