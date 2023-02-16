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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public String save(@RestForm(FileUpload.ALL) List<FileUpload> files) throws IOException {
        List<String> paths = new ArrayList<>(files.size());
        for (FileUpload file : files) {
            String name = file.fileName();
            name.replace("\\","")
                    .replace("/","")
                    .replace("..","");
            java.nio.file.Path path = Paths.get(name).toAbsolutePath();
            if(Files.exists(path)) {

            }
            Files.copy(file.uploadedFile(), path);
            paths.add(path.toString());
            paths.add("<br>");
        }
        return paths.stream().reduce("",String::concat);
    }
}
