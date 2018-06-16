package com.dziennik.dao;

import com.dziennik.entity.Classes;
import com.dziennik.entity.Students;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentsDAO extends AbstractDAO {

    public StudentsDAO() {
        setClazz(Students.class);
    }

    public Students getStudentByLogin(String login) {
        return (Students) entityManager.createQuery("SElECT s FROM Students s WHERE s.login=:studentLogin")
                .setParameter("studentLogin", login)
                .getSingleResult();
    }

    public Students getStudentByEmail(String email) {
        return (Students) entityManager.createQuery("SElECT s FROM Students s WHERE s.email=:studentEmail")
                .setParameter("studentEmail", email)
                .getSingleResult();
    }

    public List<Classes> getClassByStudent(String login) {
        Students students = getStudentByLogin(login);
        return students.getStudentClassesList();
    }


}
