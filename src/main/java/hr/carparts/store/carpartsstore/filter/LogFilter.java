package hr.carparts.store.carpartsstore.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@Order(2)
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String httpMethodName = httpServletRequest.getMethod();

        log.trace("The name of this HTTP method is: " + httpMethodName);

        for(String parameterName : httpServletRequest.getParameterMap().keySet()) {
            log.trace("Parameter name = " + parameterName + ", value = "
                + Arrays.toString(httpServletRequest.getParameterMap().get(parameterName)));
        }

        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Collection<String> headerNames = httpServletResponse.getHeaderNames();

        for(String headerParameter : headerNames) {
            log.trace("Header parameter = " + headerParameter
                + " value = " + httpServletResponse.getHeader(headerParameter));
        }

    }
}
