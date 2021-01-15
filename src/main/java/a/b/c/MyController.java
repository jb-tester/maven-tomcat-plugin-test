package a.b.c;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * *
 * <p>Created by irina on 15.01.2021.</p>
 * <p>Project: maven-tomcat-plugin-test</p>
 * *
 */
@Controller
public class MyController {
    @RequestMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("_attr1", "hello! it works!");
        return "home";
    }
}
