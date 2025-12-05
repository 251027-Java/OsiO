package com.revature.ExpenseReport.Model;

import com.revature.ExpenseReport.Repository.AppUserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
public class BasicAuthInterceptor implements HandlerInterceptor {
    //fields
    private final AppUserRepo repo;
    private final PasswordEncoder passwordEncoder;


    //constructors
    public BasicAuthInterceptor(AppUserRepo repo, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    //methods
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String authHeader = request.getHeader("Authorization");

        //it the header there, is it the right kind
        if (authHeader != null && authHeader.startsWith("Basic ")){
            //decode to a base 64 string
            String b64c = authHeader.substring(6);
            byte[] decoded = Base64.getDecoder().decode(b64c);
            String creds = new String(decoded, StandardCharsets.UTF_8);
            // split the "username:password"
            String[] parts = creds.split(":",2);
            if (parts.length == 2){
                String username = parts[0];
                String password = parts[1];

                //check if user is in the database
                Optional<AppUser> user = repo.findByUsername(username);

                /*if (user.isPresent() && user.get().getPassword().equals(password)){
                    return true;
                }*/

                if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
                    return true;
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized: invalid credentials");
        return false;
    }
}
