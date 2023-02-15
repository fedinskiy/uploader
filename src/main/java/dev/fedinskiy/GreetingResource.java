package dev.fedinskiy;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Paths;

@Path("/file")
public class GreetingResource {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public java.nio.file.Path hello() {
        return Paths.get("save.html");
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String save(@RestForm FileUpload input) throws IOException {
        System.out.println(input.fileName() );
//        System.out.println(file.getFormData().keySet());
//        java.nio.file.Path path = Paths.get("file.tmp").toAbsolutePath();
//        System.out.println(path);
//        Files.copy(file.toPath(),path);
        return "ok";
    }
}
