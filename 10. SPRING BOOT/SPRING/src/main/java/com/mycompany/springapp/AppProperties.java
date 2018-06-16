/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Witajcie
 */

@Component
@PropertySource("classpath:AppProperties.properties")
//@ConfigurationProperties(prefix = "app")
public class AppProperties {
    @Value("${picturePath}")
    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }
}
