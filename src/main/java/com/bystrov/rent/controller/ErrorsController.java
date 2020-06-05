package com.bystrov.rent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = null;
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());            

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMessage = "error.page.404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMessage = "error.page.500";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                errorMessage = "error.page.400";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                errorMessage = "error.page.401";
            }
            else {
                errorMessage = "error.page.default";
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "errors";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
