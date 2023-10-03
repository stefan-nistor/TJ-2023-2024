package ro.uaic.info.hello.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private String message;
    public static final String NUMBER_PARAMETER = "number";
    @Override
    public void init() {
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
        String number = request.getParameter(NUMBER_PARAMETER);
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Your number is: " + orderedListFromNumber(number) + "</h1>");
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

}