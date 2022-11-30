package com.alexandru.springbootecommerce.security;

import com.alexandru.springbootecommerce.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class CorsFilter implements Filter {
    private final AppConfig appConfig;

    ArrayList<String> AllowedOrigins = new ArrayList<>();
    ArrayList<String> AllowedMethods = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        AllowedOrigins.add(appConfig.getFrontendUrl());
        AllowedOrigins.add(appConfig.getFrontendHttpUrl());
        AllowedMethods.addAll(Arrays.asList("POST", "PUT", "GET", "DELETE", "OPTIONS"));

        String reqOrigin;
        reqOrigin = req.getHeader("Origin");

        if (Objects.nonNull(reqOrigin)) {
            setResponseAllowedOriginHeader(res, reqOrigin);
        }

        if (req.getMethod().equals("OPTIONS")) {


            String reqMethod;
            reqMethod = req.getHeader("Access-Control-Request-Method");

            if (Objects.nonNull(reqMethod)) {
                setResponseAllowedMethodHeader(res, reqMethod);
            }

            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Credentials", "true");
            res.setHeader("Access-Control-Allow-Headers",
                    "cache-control, if-modified-since, pragma, Content-Type, Authorization, "
                            + "Access-Control-Allow-Headers, X-Requested-With, Expires");


            if (isResponseHeaderValid(res)) {
                res.setStatus(200);
            }
        } else {
            filterChain.doFilter(req, res);
        }
    }

    private boolean isResponseHeaderValid(HttpServletResponse res) {
        return !res.getHeader("Access-Control-Allow-Methods").equals("") && !res.getHeader("Access-Control-Allow-Origin").equals("");
    }

    private void setResponseAllowedMethodHeader(HttpServletResponse res, String reqMethod) {
        for (String method : AllowedMethods) {
            if (Objects.nonNull(method) && method.equals(reqMethod)) {
                res.setHeader("Access-Control-Allow-Methods", reqMethod);
            }
        }
    }

    private void setResponseAllowedOriginHeader(HttpServletResponse res, String reqOrigin) {
        for (String origin : AllowedOrigins) {
            if (Objects.nonNull(origin) && origin.equals(reqOrigin)) {
                res.setHeader("Access-Control-Allow-Origin", reqOrigin);
                break;
            }
        }
    }
}
