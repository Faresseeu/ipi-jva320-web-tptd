package com.ipi.jva320.Controllers;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @Autowired
    public com.ipi.jva320.service.SalarieAideADomicileService SalarieAideADomicileService;
    @GetMapping(value = "/")
    public String index (ModelMap model){

        Long nb = SalarieAideADomicileService.countSalaries();
        model.addAttribute("nbSalarie", nb);


        return "home";
    }

    // TODO re- implémenter les méthodes des différent controller ici


}
