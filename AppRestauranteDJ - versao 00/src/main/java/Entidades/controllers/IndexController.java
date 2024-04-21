package Entidades.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/AppRestauranteDJ/src/main/resourses/template/")
    public String index() {
        logger.debug("Acessando a página inicial");
        return "index"; // index.html na pasta src/main/resources/templates
    }
}
