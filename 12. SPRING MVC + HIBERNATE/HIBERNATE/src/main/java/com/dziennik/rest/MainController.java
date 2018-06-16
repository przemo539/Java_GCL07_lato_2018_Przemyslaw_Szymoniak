package com.dziennik.rest;

import com.dziennik.dao.StudentsDAO;
import com.dziennik.entity.Classes;
import com.dziennik.entity.Students;
import com.dziennik.dao.ClassesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.NoResultException;
import java.util.List;

@Controller
@EnableWebMvc
public class MainController {

    private final StudentsDAO studentsDAO;

    private final ClassesDAO classesDAO;

    @Autowired
    public MainController(StudentsDAO userDAO, ClassesDAO usergroupDAO) {
        this.studentsDAO = userDAO;
        this.classesDAO = usergroupDAO;
    }
    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }
    @GetMapping("/show")
    public String showStudent(Model model) {
        List<Students> list = studentsDAO.findAll();
        List<Classes> ClassesList = classesDAO.findAll();
        model.addAttribute("listStud", list);
        model.addAttribute("listClass", ClassesList);
        return "show";
    }
    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String addStudent(@RequestParam(value = "login") String login, @RequestParam(value = "email") String email, @RequestParam(value = "passwd") String passwd) {
        studentsDAO.create(new Students(login, email, passwd));
        return "s_added";
    }

    @RequestMapping(value = "/student/{login}", method = RequestMethod.GET)
    public Students getStudent(@PathVariable(value = "login") String login) {
        try {
            return studentsDAO.getStudentByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @RequestMapping(value = "/student/{login}/classes", method = RequestMethod.GET)
    public String getStudentClasses(@PathVariable(value = "login") String login, Model model) {
        model.addAttribute("classes", studentsDAO.getClassByStudent(login));
        System.out.println(studentsDAO.getClassByStudent(login));
        return "showClass";
    }

    @RequestMapping(value = "/student/{login}", method = RequestMethod.DELETE)
    public void removeStudent(@PathVariable(value = "login") String login) {
        try {
            Students student = studentsDAO.getStudentByLogin(login);
            studentsDAO.delete(student);
        } catch (NoResultException e) {

        }

    }

    @RequestMapping(value = "/student/all", method = RequestMethod.DELETE)
    public void removeAllStudents() {
        List<Students> list = studentsDAO.findAll();
        for (Students student : list) {
            studentsDAO.delete(student);
        }
    }

    @RequestMapping(value = "/class/add", method = RequestMethod.POST)
    public String addClass(@RequestParam(value = "name") String name) {
        classesDAO.create(new Classes(name));
        return "cl_added";
    }

    @RequestMapping(value = "/class/{name}", method = RequestMethod.DELETE)
    public void removeClass(@RequestParam(value = "name") String name) {
        Classes classes = classesDAO.getClassesByName(name);
        classesDAO.delete(classes);
    }

    @RequestMapping(value = "/class/all", method = RequestMethod.DELETE)
    public void removeAllClasses() {
        List<Classes> ClassesList = classesDAO.findAll();
        for (Classes cl : ClassesList) {
            classesDAO.delete(cl);
        }
    }

    @RequestMapping(value = "/class/{class}/add/{student}", method = RequestMethod.POST)
    public void addStudentToClasses(@PathVariable(value = "class") String usergroup, @PathVariable(value = "student") String user) {
        Classes classes = classesDAO.getClassesByName(usergroup);
        Students students = studentsDAO.getStudentByLogin(user);
        classes.getStudentsList().add(students);
        classesDAO.update(classes);
    }

    @RequestMapping(value = "/class/{class}/remove/{student}", method = RequestMethod.POST)
    public void removeStudentFromClass(@PathVariable(value = "class") String usergroup, @PathVariable(value = "student") String user) {
        Classes classes = classesDAO.getClassesByName(usergroup);
        Students students = studentsDAO.getStudentByLogin(user);
        classes.getStudentsList().remove(students);
        classesDAO.update(classes);
    }




}
