package es.uji.ei1027.clubesportiu.controller;

import es.uji.ei1027.clubesportiu.dao.ClassificacioDao;
import es.uji.ei1027.clubesportiu.model.Classificacio;
import es.uji.ei1027.clubesportiu.services.ClassificacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/classificacio")
public class ClassificacioController {

    private ClassificacioDao classificacioDao;
    private ClassificacioService classificacioService;

    @Autowired
    public void setClassificacioDao(ClassificacioDao classificacioDao) {
        this.classificacioDao=classificacioDao;
    }

    @Autowired
    public void setClassificacioService(ClassificacioService classificacioService) {
        this.classificacioService = classificacioService;
    }

    @RequestMapping(value = "/delete/{nNadador}/{nProva}")
    public String processDeleteClassif(@PathVariable String nNadador,
                                       @PathVariable String nProva) {
        ClassificacioDao.deleteClassificacio(nNadador, nProva);
        return "redirect:../../list";
    }
    @RequestMapping("/list")
    public String listClassificacio(Model model) {
        model.addAttribute("classificacions", classificacioDao.getClassificacions());
        return "classificacio/list";
    }
    @RequestMapping(value="/add")
    public String addNadador(Model model) {
        model.addAttribute("classificacio", new Classificacio());
        return "classificacio/add";
    }
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddClassif(@ModelAttribute("classificacio") Classificacio classificacio,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "classificacio/add";
        try {
            classificacioDao.addClassificacio(classificacio);
        } catch (DuplicateKeyException e) {
            throw new ClubesportiuException(
                    "Ja existeix una classificacio del nadador "
                            + classificacio.getNomNadador() + " per a la prova "
                            + classificacio.getNomProva(), "CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nom}/{nomProva}", method = RequestMethod.GET)
    public String editClassificacio(Model model, @PathVariable String nom, @PathVariable String nomProva) {
        model.addAttribute("classificacio", classificacioDao.getClassificacio(nom,nomProva));
        return "classificacio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("classificacio") Classificacio classificacio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "classificacio/update";
        classificacioDao.updateClassificacio(classificacio);
        return "redirect:list";
    }
    @RequestMapping(value="/perpais/{nomprova}", method = RequestMethod.GET)
    public String listClsfPerPais(Model model, @PathVariable String nomprova) {
        model.addAttribute("classificacions",
                classificacioService.getClassificationByCountry(nomprova));
        return "classificacio/perpais";
    }



}

