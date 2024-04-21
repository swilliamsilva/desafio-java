package Entidades.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Redireciona para a página de erro apropriada com base no código de status
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404"; // Renderiza a página de erro 404
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"; // Renderiza a página de erro 500
            }
        }
        return "error"; // Renderiza uma página de erro genérica
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

