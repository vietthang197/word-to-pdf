package com.thanglv;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/init")
    public String init() throws IOException {
        Path path = Paths.get("/pdf");
        if (Files.notExists(path))
            Files.createDirectories(path);
        return "Init successful!";
    }

    @GetMapping("/topdf/{fileName}")
    public String convertToPdf(@PathVariable String fileName) throws IOException, InterruptedException {
        Path path = Paths.get("/pdf/" + fileName);
        if (Files.notExists(path))
            System.out.println("File not found!");
        String output  = FilenameUtils.getBaseName(fileName);
        Process proc = Runtime.getRuntime().exec("libreoffice --headless --infilter=\"writer_pdf_import\" --convert-to pdf " + path.toAbsolutePath().toString() + " --outdir /pdf/" + output + ".pdf");
        proc.waitFor();
        return "Successful!";
    }
}
