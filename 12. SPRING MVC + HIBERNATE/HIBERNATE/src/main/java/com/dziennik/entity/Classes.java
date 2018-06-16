package com.dziennik.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="classes_id")
    private int classes_id;

    @Column(name="name", unique = true)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.ALL,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_classes",
            joinColumns = @JoinColumn(name = "classes_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Students> StudentsList;

    public Classes() {
        this.StudentsList = new ArrayList<>();
    }

    public Classes(String name) {
        this.name = name;
        this.StudentsList = new ArrayList<>();
    }

    public int getClasses_id() {
        return classes_id;
    }

    public void setClasses_id(int classes_id) {
        this.classes_id = classes_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Students> getStudentsList() {
        return StudentsList;
    }

    public void setStudentsList(List<Students> StudentsList) {
        this.StudentsList = StudentsList;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classes_id=" + classes_id +
                ", name='" + name + '\'' +
                '}';
    }
}
