package com.dziennik.dao;

import com.dziennik.entity.Classes;
import com.dziennik.entity.Students;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassesDAO extends AbstractDAO {

    public ClassesDAO() {
        setClazz(Classes.class);
    }

    public Classes getClassesByName(String name) {
        return (Classes) entityManager.createQuery("SElECT cl FROM Classes cl WHERE cl.name=:classesName")
                .setParameter("classesName", name)
                .getSingleResult();
    }

    public List<Students> getStudentsInClasses(String name) {
        Classes classes = getClassesByName(name);
        return classes.getStudentsList();
    }
}
