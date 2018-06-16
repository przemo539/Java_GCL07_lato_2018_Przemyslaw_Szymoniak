package com.dziennik.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Students implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="student_id")
    private int student_id;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="passwd")
    private String passwd;

    @ManyToMany(cascade = {
            CascadeType.ALL,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_classes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "classes_id")
    )
    private List<Classes> studentClassesList;

    public Students() {
        this.studentClassesList = new ArrayList<>();
    }

    public Students(String login, String email, String passwd) {
        this.login = login;
        this.email = email;
        this.passwd = passwd;
        this.studentClassesList = new ArrayList<>();
    }

    public int getStudent_id() {
        return student_id;
    }

    public void seStudent_id(int user_id) {
        this.student_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public List<Classes> getStudentClassesList() {
        return studentClassesList;
    }

    public void setStudentClassesList(List<Classes> studentClassesList) {
        this.studentClassesList = studentClassesList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", studentClassesList=" + studentClassesList +
                '}';
    }
}
