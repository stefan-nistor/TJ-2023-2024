package ro.uaic.info.lab2.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.graph4j.io.DimacsIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Lab2Service {

    private static final String SAVE_DIR = "uploadFiles";

    public void processDoPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String filePath = savePath + File.separator + fileName;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }

        DimacsIO dimacsIO = new DimacsIO();
        var readGraph = dimacsIO.read(filePath);
        var order = readGraph.numVertices();
        var size = readGraph.numEdges();

        request.setAttribute("order", order);
        request.setAttribute("size", size);

        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

}
