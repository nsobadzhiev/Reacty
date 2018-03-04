package com.devmonologue.reacty;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.UUID;

@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ImageUploadResource {

    private MemeStore reactionsStore = new MemeStore();

    @POST
    public Response uploadImage(
            @FormDataParam("file") final InputStream fileInputStream,
            @FormDataParam("name") final String imageName,
            @FormDataParam("tags") final String imageTags) {
        System.out.print("Uploading file...");
        String pwd = System.getProperty("user.dir");
        String name = UUID.randomUUID().toString();
        String filePath = pwd + "/src/main/resources/assets/images/" + name;
        File imageFile = new File(filePath);
        saveFile(fileInputStream, imageFile);
        saveDBRecord(imageName, imageTags, name);
        String output = "File can be downloaded from the following location : " + filePath;

        return Response.status(200).entity(output).build();
    }

    private void saveDBRecord(String imageName, String imageTags, String name) {
        reactionsStore.saveReaction(imageName, imageTags, name);
    }

    private void saveFile(InputStream uploadedInputStream,
                          File serverLocation) {
        try {
            serverLocation.createNewFile();
            OutputStream outputStream = new FileOutputStream(serverLocation);
            int read = 0;
            byte[] bytes = new byte[1024];

            outputStream = new FileOutputStream(serverLocation);
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