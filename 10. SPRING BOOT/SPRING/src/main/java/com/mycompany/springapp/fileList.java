/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springapp;

import java.nio.file.attribute.FileTime;

/**
 *
 * @author Witajcie
 */
public class fileList {
    static int licznik=1;
    int index;
    String name;
    String resolution;
    long size;
    String created;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getResolution() {
        return resolution;
    }

    public long getSize() {
        return size;
    }

    public String getCreated() {
        return created;
    }    

    public fileList() {
    }
    public fileList(String name, String resolution, long size, String created) {
        this.index = licznik++;
        this.name = name;
        this.resolution = resolution;
        this.size = size;
        this.created = created;
    }
    
}
