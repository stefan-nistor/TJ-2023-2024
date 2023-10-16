package ro.uaic.info.lab2.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/*"}, filterName = "DecoratingFilter")
public class DecoratingFilter extends HttpFilter {

    private static final String PRELUDE = "<div>This is the beginning</div>";
    private static final String CODA = "<div>This is the end</div>";
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse originalResponse = response;
        PrintWriter writer = originalResponse.getWriter();

        writer.print(PRELUDE);

        chain.doFilter(request, new HttpServletResponseWrapper(originalResponse) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return super.getOutputStream();
            }

            @Override
            public PrintWriter getWriter() {
                return writer;
            }
        });

        writer.print(CODA);
        writer.close();
    }
}
