/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Witajcie
 */
@Controller
public class AdminController {
@RequestMapping(value="/gallery/panel", method=RequestMethod.GET)
    public String loadPanel(Model model) {
       return "panel";
    
    }
    @RequestMapping("/gallery/login")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
       return "login";
    }
}
