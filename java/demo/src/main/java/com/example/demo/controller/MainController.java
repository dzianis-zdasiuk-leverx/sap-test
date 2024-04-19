package com.example.demo.controller;

import com.example.demo.config.NotAuthorizedException;
import com.sap.cloud.security.xsuaa.token.Token;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")

public class MainController {

    @GetMapping(path = "")
    public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
        if (token.getAuthorities() == null || token.getAuthorities().stream().noneMatch(x -> StringUtils.containsIgnoreCase(x.getAuthority(), "Display"))) {
            System.err.println(token);
            if (token.getAuthorities() != null) {
                return new ResponseEntity<>(String.join(",",
                        token.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new)
                ), HttpStatus.OK);
            }
            throw new NotAuthorizedException("This operation requires \"Display\" scope");
        }

        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
}
