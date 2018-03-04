package com.devmonologue.reacty;

import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Path("/images")
@Produces("image/gif")
public class ImageResource {

    public ImageResource() {}

    @GET
    @Timed
    public Response getImage(@QueryParam("name") @NotEmpty String name) {
        try {
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
            String pwd = System.getProperty("user.dir");
            String filePath = pwd + "/images/" + name;
            File imageFile = new File(filePath);
            String mimeType = Files.probeContentType(Paths.get(filePath));
            BufferedImage image = ImageIO.read(imageFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "gif", baos);
            byte[] imageData = baos.toByteArray();

            return Response.ok(new ByteArrayInputStream(imageData)).build();
        }
        catch (IOException exception) {
            return Response.status(404).build();
        }

    }
}
