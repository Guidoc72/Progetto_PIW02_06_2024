package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.exception.MyException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Pagina non trovata.");
        return "notfoundview"; // Restituisci la vista della pagina di errore
    }
	
	
	
	
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleMyException(MyException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "/errori"; // Restituisci la vista della pagina di errore
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Si è verificato un errore. Per favore riprova più tardi.");
        return "/errori";
    }
}