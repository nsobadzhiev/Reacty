package com.devmonologue.reacty;

import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ReactyApplication extends Application<ReactyConfiguration> {

    public static void main(String[] args) throws Exception {
        new ReactyApplication().run(args);
    }

    @Override
    public String getName() {
        return "Reacty";
    }

    @Override
    public void initialize(Bootstrap<ReactyConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/build/resources/main/", "/start/", "index.html"));
    }

    @Override
    public void run(ReactyConfiguration configuration,
                    Environment environment) {
        final ReactionResource getReactions = new ReactionResource();
        final ImageResource imageLoader = new ImageResource();
        final ImageUploadResource imageUpload = new ImageUploadResource();
        environment.jersey().register(getReactions);
        environment.jersey().register(imageLoader);
        environment.jersey().register(imageUpload);
        environment.jersey().register(MultiPartFeature.class);

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }
}
