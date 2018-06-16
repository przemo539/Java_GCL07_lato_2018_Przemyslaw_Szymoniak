/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Witajcie
 */
public class CustomServer {
    public class Zalogowani{
        String name;
        Socket socket;
        boolean zalogowany;
        boolean admin;

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public void setZalogowany(boolean zalogowany) {
            this.zalogowany = zalogowany;
        }

        public Socket getSocket() {
            return socket;
        }

        public String getName() {
            return name;
        }

        public Thread getThread() {
            return thread;
        }
        Thread thread;

        public Zalogowani(String n, Socket soc, Thread thr) {
            name = n;
            socket = soc;
            thread = thr;
            zalogowany = false;
            admin = false;
        }
        public void setName(String n){
            name = name;
        }
        
    };
    
    public class users{
        String name;
        String pass;
        boolean admin;

        public users(String name, String pass, boolean admin) {
            this.name = name;
            this.pass = pass;
            this.admin = admin;
        }
        public boolean check(String n, String p){
            return (n== name && p == pass);
        }
        public boolean checkAdmin(){
            return admin;
        }
    };
    public static ArrayList<Zalogowani> ConnectionArray = new ArrayList<Zalogowani>();
    public static ArrayList<users> UsersArray = new ArrayList<users>();
    
    CustomServer(){
        UsersArray.add(new users("test","test",false));
        UsersArray.add(new users("admin","admin",true));
        UsersArray.add(new users("jan","123",false));
        UsersArray.add(new users("ewa","123",false));
        UsersArray.add(new users("kaziu","123",false));
    }
  
    public void run( int port)throws IOException{
        ServerSocket Serwer = new ServerSocket(port);
        CustomServer to = this;
        while(true){
            if(ConnectionArray.size()<5){
                Socket tempSocket = Serwer.accept();
                Thread tempThread = new Thread(){
                    @Override
                    public void run() {
                       Socket temp = tempSocket;
                        try {
                                                        
                            to.doClientLogic(temp);
                        } catch (IOException ex) {
                            Logger.getLogger(CustomServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }; 
                tempThread.start();
                Zalogowani temp = new Zalogowani("guest"+ConnectionArray.size(),tempSocket, tempThread);
                ConnectionArray.add(temp);
            }
        }
    }
    
    public void stop() throws IOException {
        for (Zalogowani temp : ConnectionArray) {
            temp.getThread().interrupt(); 
            temp.getSocket().close(); 
           
	}
        ConnectionArray.clear();
    }
    
    private boolean login(String  username,  String  password, Socket socket){
        for (users temp : UsersArray) {
            boolean tempCheck = temp.check(username, password);
            if(tempCheck){
                for (Zalogowani temp2 : ConnectionArray) {
                    if(temp2.getSocket() == socket){
                     temp2.setName(username);
                     temp2.setAdmin(temp.checkAdmin());
                        
                     break;
                    }
                }
                return true;              
            }
        
        }
        return false;
    }
    
    private boolean kick(String adminName, String  username) throws IOException{
        int toKick = -1;
        boolean admin = false;
         for (int i = 0; i < ConnectionArray.size(); i++) {
            if(ConnectionArray.get(i).getName() == username){
                toKick = i;
            }else if(ConnectionArray.get(i).getName() == adminName){
                admin = ConnectionArray.get(i).admin;
         }
            
         }
         if(admin && toKick >0){
             ConnectionArray.get(toKick).getThread().interrupt();
             ConnectionArray.get(toKick).getSocket().close(); 
             ConnectionArray.remove(toKick);
             return true;
         }
        return false;
    }
    
     private void logout(String  username) throws IOException{
       for (int i = 0; i < ConnectionArray.size(); i++) {
            if(ConnectionArray.get(i).getName() == username){
                ConnectionArray.get(i).setName("guest"+ConnectionArray.size()+i);
                break;
            }
         }
    }
     
    private static final int PING_FRAME = 1;
    private static final int HELLO_WORLD_FRAME = 2;
    private static final int LOGIN_FRAME = 3;
    private static final int LOGOUT_FRAME = 4;
    private static final int ECHO_FRAME = 5;
    private static final int ADMIN_KICK_FRAME = 6;
    private void doClientLogic( Socket socket ) throws IOException
    {
        //TODO: poprawne zamykanie strumieni
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        while( !Thread.interrupted() )
        {
            switch(input.readInt() )
            {
                case PING_FRAME:
                    output.writeInt(PING_FRAME); // odsyłamy typ ramki (pusta ramka)
                    output.flush();
                    break;
                case HELLO_WORLD_FRAME:
                    output.writeInt( HELLO_WORLD_FRAME);// typ ramki
                    output.writeUTF( "HELLO WORLD!" );// dane ramki
                    output.flush();
                    break;
                case LOGIN_FRAME:
                    output.writeInt( LOGIN_FRAME);// typ ramki
                    String username = input.readUTF();
                    String password = input.readUTF();
                    
                    output.writeBoolean(login(username, password, socket));
                    output.flush();
                    break;
                case LOGOUT_FRAME:
                    output.writeInt(LOGOUT_FRAME);// typ ramki
                    String usernam = input.readUTF();
                    logout(usernam);
                    output.flush();
                    break;
                case ECHO_FRAME:
                    output.writeInt(ECHO_FRAME);// typ ramki
                    String text = input.readUTF();
                    output.writeUTF(text);
                    output.flush();
                    break;
                case ADMIN_KICK_FRAME:
                    output.writeInt(ECHO_FRAME);// typ ramki
                    String adminName = input.readUTF();
                    String userToKick = input.readUTF();
                    output.writeBoolean(kick(adminName, userToKick));
                    output.flush();
                    break;
                //next cases...
            }
        }
        input.close();
        output.close();
    }

}
/*

*/
