package com.ipi.jva320.Controllers;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ipi.jva320.model.SalarieAideADomicile;

import java.util.List;

@Controller
public class SalarieController {

    @Autowired
    public SalarieAideADomicileService SalarieAideADomicileService;


    @GetMapping("/salaries/{id}")
    public String Salaries ( ModelMap model1,@PathVariable(value = "id") Long id ){
        SalarieAideADomicile salarie = SalarieAideADomicileService.getSalarie(id);

        model1.put("salarie",salarie);

        return "detail_Salarie";
    }


    @RequestMapping("/salaries/aide/new")
    public String Create_Salaries ( ModelMap modelMap){
        modelMap.addAttribute("newSalarie",new SalarieAideADomicile());

        return "create_Salarie";
    }

    @RequestMapping ("/salaries/aide/10")
    public String redirectSave (@ModelAttribute SalarieAideADomicile newSalarie) throws SalarieException {
        SalarieAideADomicileService.creerSalarieAideADomicile(newSalarie);
        return "succesSave";
    }



    @RequestMapping("/salaries/aide/{id}/delete")
    public String deleteSalaries(@PathVariable(value = "id") Long id, ModelMap modelMap) throws SalarieException {
        SalarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }


    @RequestMapping("/salaries")
    public String printList(ModelMap modelMap, @RequestParam(required = false) String nom) {
        List<SalarieAideADomicile> salaries;

        if (nom != null && !nom.isEmpty()) {
            salaries = SalarieAideADomicileService.getSalaries(nom);
        } else {
            salaries = SalarieAideADomicileService.getSalaries();
        }
        modelMap.addAttribute("salaries", salaries);
        return "list";
    }


    @PostMapping("/salaries/aide/{id}/edit")
    public String editSalaries(@PathVariable(value = "id") Long id, @ModelAttribute SalarieAideADomicile updatedSalarie) throws SalarieException {
        if (!id.equals(updatedSalarie.getId())) {
            return "redirect:/error";
        }

        SalarieAideADomicile existingSalarie = SalarieAideADomicileService.getSalarie(id);

        // Mettez à jour les champs nécessaires
        existingSalarie.setNom(updatedSalarie.getNom());
        existingSalarie.setMoisEnCours(updatedSalarie.getMoisEnCours());
        existingSalarie.setMoisDebutContrat(updatedSalarie.getMoisDebutContrat());
        existingSalarie.setJoursTravaillesAnneeNMoins1(updatedSalarie.getJoursTravaillesAnneeNMoins1());
        existingSalarie.setCongesPayesAcquisAnneeNMoins1(updatedSalarie.getCongesPayesAcquisAnneeNMoins1());
        existingSalarie.setJoursTravaillesAnneeN(updatedSalarie.getJoursTravaillesAnneeN());
        existingSalarie.setCongesPayesAcquisAnneeN(updatedSalarie.getCongesPayesAcquisAnneeN());
        existingSalarie.setCongesPayesPrisAnneeNMoins1(updatedSalarie.getCongesPayesPrisAnneeNMoins1());

        // Utilisez le service pour mettre à jour le salarié
        SalarieAideADomicileService.updateSalarieAideADomicile(existingSalarie);

        return "succesModif";
    }


}

