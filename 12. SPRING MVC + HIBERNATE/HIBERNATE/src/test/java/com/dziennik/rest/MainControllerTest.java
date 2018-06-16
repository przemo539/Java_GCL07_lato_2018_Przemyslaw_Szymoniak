package com.dziennik.rest;

import com.dziennik.entity.Classes;
import com.dziennik.entity.Students;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.text.MessageFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    private Students students;

    private Classes classes;

    @Before
    public void setUp() throws Exception {
        students = new Students("adle", "abc@abc.com", "test");

    }

    @After
    public void tearDown() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/student/all"));
        mvc.perform(MockMvcRequestBuilders.delete("/class/all"));
    }

    @Test
    public void addUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/student/add")
                .param("login", students.getLogin())
                .param("email", students.getEmail())
                .param("passwd", students.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserDuplicate() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/student/add")
                .param("login", students.getLogin())
                .param("email", students.getEmail())
                .param("passwd", students.getPasswd())
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request);
    }

    @Test
    public void addUserMissingData() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/student/add")
                .param("login", students.getLogin())
                .param("passwd", students.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student/adle")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addUserToUserGroup() throws Exception {
        Classes cl = new Classes("ugTest");

        mvc.perform(MockMvcRequestBuilders.post("/student/add")
                .param("login", students.getLogin())
                .param("email", students.getEmail())
                .param("passwd", students.getPasswd())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/class/add")
                .param("name", cl.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post(MessageFormat.format("/class/{0}/add/{1}", cl.getName(), students.getLogin()))
                .param("name", cl.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}