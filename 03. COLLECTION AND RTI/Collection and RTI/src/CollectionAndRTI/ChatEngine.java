/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionAndRTI;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ChatEngine {
    HashMap<Long,User> userMap =new HashMap<>();
    long id;
    Set<String> banNames = new TreeSet<>();
    
    void addUser(User user) throws UserAddException{
        if(hasUser(user.getId()) == false && hasName(user.getName())==false){
            userMap.put(id++, user);
        }
        else{
            throw new UserAddException();
        }
        
        
    }
    
    void removeUser(long userid) throws UserRemoveException{
        if(hasUser(userid) == true){
            userMap.remove(id);
        }
        else{
            throw new UserRemoveException();
        }
    }
    
    int getNumerOfUsers(){
        return userMap.size();
    }
    
    void createBan(String userName){
        banNames.add(userName);
        if(hasUser(userName)==true){
            userMap.remove(userName);
        }
        
    }
    
    void removeBan(String userName){
        banNames.remove(userName);
        
    }
    
    boolean hasUser(long userid){
        boolean isThereUser = userMap.containsKey(userid);
        return isThereUser;
    }
    
    boolean hasUser(String userName){
        boolean isThereUser = userMap.containsValue(userName);
        return isThereUser;
    }
    
    boolean hasName(String userName){
        boolean isThereUser = banNames.contains(userName);
        return isThereUser;
    }
    
    boolean broadcastMessage(long userId, String message){
        for (Map.Entry<Long,User> entry : userMap.entrySet()) {
            Long key = entry.getKey();
            User value = entry.getValue();
            System.out.println("ID: "+value.getId()+" NAZWA: "+value.getName()+" wiadomosc: "+message);
        }
        return true;
    }   
}
