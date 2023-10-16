package ro.uaic.info.lab2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.graph4j.io.DimacsIO;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    private static final String SAVE_DIR = "uploadFiles";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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