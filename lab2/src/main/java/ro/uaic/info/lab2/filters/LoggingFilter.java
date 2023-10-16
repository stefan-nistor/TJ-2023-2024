package ro.uaic.info.lab2.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {"/upload"}, filterName = "LoggingFilter")
public class LoggingFilter extends HttpFilter {
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        LOGGER.info("Method requested: " + request.getMethod()
                + "\nClient IP: " + request.getRemoteAddr()
                + "\nClient Locale: " + request.getLocale()
        );
        super.doFilter(request, response, filterChain);
    }
}
