package com.pizzeria.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
@Controller
@EnableWebMvc
public class ResourceController {

    @GetMapping("/styles/{name}.css")
    @ResponseBody
    public ResponseEntity<String> styles(@PathVariable("name") String name) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("static/styles/"+name+".css");
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line = bf.readLine()) != null){
            sb.append(line+"\n");
        }

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("Content-Type", "text/css; charset=utf-8");
        return new ResponseEntity<String>( sb.toString(), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/images/goods/{num}.jpg")
    public void goodsImages(HttpServletResponse response, @PathVariable("num") String num) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("static/images/goods/" + num + ".jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/images/{name_and_extension}")
    public void functionalImages(HttpServletResponse response, @PathVariable("name_and_extension") String name_and_extension) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("static/images/" + name_and_extension);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
