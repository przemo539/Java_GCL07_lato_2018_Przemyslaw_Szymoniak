/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Witajcie
 */
public class CustomClient {
    public Socket socket;
    String login=null;
    public  void  connect(  String  host,  int  port  )  throws  IOException{
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
        }
    }
    
    public void disconnect() throws IOException{
        socket.close();
    }
    
    public boolean login(  String  username,  String  password  ) throws  IOException{
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        output.writeInt(3);
        input.readInt();
        output.writeUTF(username);
        output.writeUTF(password);
        output.flush();
        if(input.readBoolean()){            
            login = username;
            input.close();
            output.close();
            return true;
        }      
        input.close();
        output.close();
        return false;        
    }
    
    public void logout() throws IOException{
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeInt(4);
        input.readInt();
        output.writeUTF(login);
        output.flush();
        input.close();
        output.close();
    }
    
    public boolean ping() throws IOException{
        if(login.isEmpty())
             return false;    
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeInt(4);
        input.readInt();
        output.writeUTF(login);
        output.flush();
        input.close();
        output.close();
       return true;
    }
    
    public boolean echo(String text ) throws IOException {
        if(login.isEmpty())
             return false;   
        String test = "TESTOWANY TEXT";
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeInt(5);
        input.readInt();
        output.writeUTF(test);
        output.flush();
        if(input.readUTF() == test){
            input.close();
            output.close();
            return true;
        }
        input.close();
        output.close();
        return false;
    }
    
    public boolean toKick(String name)throws IOException {
        if(login.isEmpty())
             return false;   
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeInt(6);
        input.readInt();
        output.writeUTF(login);
        output.writeUTF(name);
        output.flush();
        if(input.readBoolean()){
            input.close();
            output.close();
            return true;
        }
        input.close();
        output.close();
        return false;
    }
}
