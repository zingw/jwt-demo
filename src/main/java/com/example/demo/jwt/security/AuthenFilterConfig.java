package com.example.demo.jwt.security;

import com.example.demo.jwt.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthenFilterConfig extends BasicAuthenticationFilter {

    /**
     * B1: Tao Constructor
     * B2: viet doFilterInternal
     * decode
     * B3: Tao UsernamePasswordAuthenticationToken
     * B4: Tao List<GrantedAuthority>
     */
    public AuthenFilterConfig(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        String tokenTemp = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (tokenTemp == null) {
            chain.doFilter(req, res);
            return;
        }
        String token = tokenTemp.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authen = getAuthentication(token);
        if(authen==null){
            chain.doFilter(req,res);
            return;
        }

        context.setAuthentication(authen);
        chain.doFilter(req,res);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Map<String,Object> userMap = TokenService.decodeToken(token);
        String username = (String) userMap.get("username");
        List<String> authorList = (List<String>)userMap.get("authors");
        List<GrantedAuthority> grantedAuthorityList = generateGrantedAuthorityList(authorList);
        return new UsernamePasswordAuthenticationToken(username,null, grantedAuthorityList);

    }

    public List<GrantedAuthority> generateGrantedAuthorityList(List<String> inputList){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String s : inputList){
            grantedAuthorities.add(new SimpleGrantedAuthority(s));
        }
        return grantedAuthorities;
    }

}

