/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CollectionAndRTI;

/**
 *
 * @author rszelc
 */
public class MainClass {

    /**
     * @param args the command line arguments
     * @throws CollectionAndRTI.UserAddException
     */
    public static void main(String[] args) throws UserAddException {
        int id=0;
        User user1=new User(++id, "ADAM");
        User user2=new User(++id, "ZBYSZEK");
        ChatEngine chat = new ChatEngine();
        
         System.out.println("Czy Istnieje uzytkownik id:0 " + chat.hasUser(0));
        chat.addUser(user1);
        chat.addUser(user2);
        chat.broadcastMessage(1, "TEST"); 
    }
    
}
