/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author AA
 */
public class Decryption {

    Scanner sc;
    Formatter out;

    public String Read_From_File(String FileName) throws FileNotFoundException // read input data 
    {
        sc = new Scanner(new File(FileName)); // to open file
        String data = "";

        while (sc.hasNext()) {
            data += sc.nextLine();

        }

        sc.close();
        return data;
    }

    void Decrypyion_Function() throws FileNotFoundException {
        String Encryption_msg = Read_From_File("Encryption.txt");
        String File_key = Read_From_File("Key.txt");

        System.out.println("\n\nEncryption Msg From File : " + Encryption_msg);
        System.out.print("Key From File is : " + File_key);

        String Decryption_msg = "";
        for (int i = 0; i < Encryption_msg.length(); i++) {
            if (File_key.charAt(i) == Encryption_msg.charAt(i)) {
                Decryption_msg += "0";
            } else {
                Decryption_msg += "1";
            }
        }
        System.out.println("\nDecryption msg is: " + Decryption_msg);
        String Str_temp = "";
        for (int i = 0; i < Decryption_msg.length(); i += 7) {
            Str_temp += Decryption_msg.substring(i, i + 7);
            Str_temp += " ";
        }
        System.out.println("Decryption msg with Space :" + Str_temp);
        String s = " ";
        for (int i = 0; i < Str_temp.length(); i += 8) {
            String temp = Str_temp.substring(i, i + 7);
            int num = Integer.parseInt(temp, 2);
            char letter = (char) num;
            s = s + letter;
        }
        System.out.println("Final Text is :" + s);
    }
}
