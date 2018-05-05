package com.devmonologue.reacty;

import com.codahale.metrics.annotation.Timed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/reactions")
@Produces(MediaType.APPLICATION_JSON)
public class ReactionResource {

    private MemeStore reactionsStore = new MongoMemeStore();

    public ReactionResource() {}

    @GET
    @Timed
    public Response getReactions(@QueryParam("name") @NotEmpty String name, @Context HttpHeaders header, @Context HttpServletResponse response) {
        List<Reaction> foundReactions = reactionsStore.getReactions(name);
        System.out.print("Name: " + name);
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
//        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
//        System.out.print("Response headers: " + response.toString());
        Response response2 = Response.status(200).
                entity(foundReactions).
                header("Access-Control-Allow-Origin", "*").
                build();
        return response2;
//        return foundReactions;
    }
}
