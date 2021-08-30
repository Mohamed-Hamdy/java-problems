/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_2;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author AA
 */
public class Network {

    private int mNum;

    public Network() throws InterruptedException {
        main();
    }

    public Network(int num) {
        this.mNum = num;
    }

//    public void setConnection(int num) {
//        this.mNum = num;
//    }

    public int getConnection() {
        return this.mNum;
    }

    private void main() throws InterruptedException {
        String connect_dnum, num_of_device = null , name , Type = null;
        String Temp = "";
        //Scanner x = new Scanner(System.in);

        connect_dnum = JOptionPane.showInputDialog("What is number of WI-FI Connections? ");
        Router router = new Router( Integer.parseInt(connect_dnum));

        num_of_device = JOptionPane.showInputDialog("What is number of devices Clients want to connect? ");

        String[] str = new String[Integer.parseInt(num_of_device)];
        String[] type = new String[Integer.parseInt(num_of_device)];
        
        for (int i = 0; i < Integer.parseInt(num_of_device); i++) {
            name = JOptionPane.showInputDialog("Device Name :");
            str[i] = name;

            Type = JOptionPane.showInputDialog("Device Type :");
            type[i] = Type;

        }
        for (int j = 0; j < Integer.parseInt(num_of_device); j++) {
            Temp += ("\n" + str[j]+" : "+type[j]); 
        }
        JOptionPane.showMessageDialog(null, "your Input is : " + Temp , "The Input Array is ", JOptionPane.PLAIN_MESSAGE);
        
        router.addDevices(str, type, Integer.parseInt(num_of_device) ,Integer.parseInt(connect_dnum));
    }

}
