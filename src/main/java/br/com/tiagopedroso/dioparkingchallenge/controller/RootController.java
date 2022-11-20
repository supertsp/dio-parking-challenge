package br.com.tiagopedroso.dioparkingchallenge.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Hidden
    @GetMapping
    String show() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <style>
                    body {
                        background-color: black;
                        color: white;
                        font-family: Arial;
                    }
                    a {
                        text-decoration: none;
                    }
                    a:link, a:visited, a:active {
                        color: rgb(0, 255, 64);
                    }                    
                    a:hover {
                        color: rgb(0, 255, 149);
                    }
                    </style>                    
                </head>
                <body>
                <pre>

                THE PARKING DIO CHALLENGE REST SERVER
                   \s
                                  (__)         \s
                       _-_-  _/\\__(oo)_\\\\_
                    _-_-__  / ,-. -\\/  ,-.`-.
                       _-_- `( o )----( o )-'
                              `-'      `-' \s
                          \s
                    2022-11-15, supertsp
                  	    
                </pre>
                <a href="./swagger-ui.html">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Swagger API Doc</a><br>
                <a href=":5430/browser/">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Localhost pgAdmin</a>
                </body>
                </html>
                """;
    }

}
