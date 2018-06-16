/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockety;

import java.io.IOException;

/**
 *
 * @author Witajcie
 */
public class ClientProgram {
    public static void main(String[] args) throws IOException {
        CustomClient client = new CustomClient();
        client.connect("localhost", 444);
       // client.login("test","test");
    }
}
