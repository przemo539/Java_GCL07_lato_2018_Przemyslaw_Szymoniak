/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Witajcie
 */
@Controller
public class galleryController {
    @RequestMapping(value="/gallery", method=RequestMethod.GET)
    public String loadGallery(Model model) {
        //model.addAttribute("customer", "asd");
        return "gallery";
    }
}
