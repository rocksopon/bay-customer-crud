package com.bay.demo.config;

import com.bay.demo.model.UserDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (isEmpty(header) || !header.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                var headerSplit = header.split(" ");
                String[] chunks = headerSplit[1].split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();

                String httpHeader = new String(decoder.decode(chunks[0]));
                String httpPayload = new String(decoder.decode(chunks[1]));
                var userDetail = objectMapper.readValue(httpPayload, UserDetail.class);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,null, List.of(new SimpleGrantedAuthority("ROLE_"+userDetail.getRole())));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            }
        } catch (AccessDeniedException ex) {
            throw new AccessDeniedException("Access Denied");
        }



    }
}
