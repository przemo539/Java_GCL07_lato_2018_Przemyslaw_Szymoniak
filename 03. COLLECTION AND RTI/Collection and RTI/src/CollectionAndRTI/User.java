/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionAndRTI;

public class User {
    long id;
    String name;
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public User(long id, String name){
        this.id=id;
        this.name=name;
    }
}
