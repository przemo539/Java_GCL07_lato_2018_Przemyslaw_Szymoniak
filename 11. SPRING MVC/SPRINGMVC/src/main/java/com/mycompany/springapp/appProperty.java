/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Witajcie
 */

@Component
@PropertySource("classpath:appProperty.properties")
//@ConfigurationProperties(prefix = "app")
public class appProperty {
    @Value("${picturePath}")
    private String picturePath;
    @Value("${adminLogin}")
    private String adminLogin;
    @Value("${adminPassword}")
    private String adminPassword;
    public String getAdminLogin() {
        return adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
