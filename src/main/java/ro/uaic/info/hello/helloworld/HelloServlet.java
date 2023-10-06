package ro.uaic.info.hello.helloworld;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.graph4j.generate.RandomTreeGenerator;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HelloServlet.class.getName());
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private String message;
    public static final String NUMBER_PARAMETER = "number";

    @Override
    public void init() {
        try {
            var logFile = new FileHandler("/Users/snistor/TJ-2023-2024/hello-log.log");
            var formatter = new SimpleFormatter();

            logFile.setFormatter(formatter);
            LOGGER.addHandler(logFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Method requested: " + request.getMethod()
                        + "\nClient IP: " + request.getRemoteAddr()
                        + "\nClient Locale: " + request.getLocale()
                        + "\nParameter: " + request.getParameter(NUMBER_PARAMETER)
                );

        String number = request.getParameter(NUMBER_PARAMETER);
        var tree = new RandomTreeGenerator(Integer.parseInt(number)).create();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Your matrix is:</br>" + generateHTMLTable(tree.adjacencyMatrix()) + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
    }

    public static List<Integer> orderedListFromNumber(String numberStr) {
        List<Integer> orderedDigits = new ArrayList<>();
        if (numberStr != null && DIGITS.matcher(numberStr).matches()) {
            char[] digits = numberStr.toCharArray();
            for (char digitChar : digits) {
                int digit = Character.getNumericValue(digitChar);
                orderedDigits.add(digit);
            }
            Collections.sort(orderedDigits);
        }
        return orderedDigits;
    }

    public static String generateHTMLTable(int[][] data) {
        StringBuilder htmlTable = new StringBuilder();

        htmlTable.append("<table border=\"1\">");
        for (int[] row : data) {
            htmlTable.append("<tr>");
            for (int cell : row) {
                htmlTable.append("<td>").append(cell).append("</td>");
            }
            htmlTable.append("</tr>");
        }
        htmlTable.append("</table>");

        return htmlTable.toString();
    }

}