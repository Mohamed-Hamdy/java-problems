/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author AA
 */
public class Ass_1 {

    /**
     * @param args the command line arguments
     */
    static Scanner sc;
    static Formatter out;

    public static void OpenFile_with_path(String path) throws FileNotFoundException {
        out = new Formatter(path);
    }

    public void closefile() {
        out.close();
    }

    public static void Write_in_File(String code) {

        out.format("%s", code);
        out.format("%n");
        out.flush(); // 34an yktb 3al file

    }

    public static String Read_From_File(String FileName) throws FileNotFoundException // read input data 
    {
        sc = new Scanner(new File(FileName)); // to open file
        String data = "";

        while (sc.hasNext()) {
            data += sc.nextLine();

        }

        sc.close();
        return data;
    }

    public static final String str = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encryption_Function(String plaint) throws FileNotFoundException {
        String key = Read_From_File("key.txt");
        //System.out.println("\nkey is :" + key + " \n");
        String Output = "";
        for (int i = 0; i < plaint.length(); i++) {
            char char_s = plaint.charAt(i);
            char c;
            if (str.contains(String.valueOf(char_s))) {
                int charpos = str.indexOf(plaint.charAt(i));
                c = key.charAt(charpos);
                //System.out.println(charpos);
                Output += c;
            } else {
                Output += String.valueOf(plaint.charAt(i)); // To convert Strang Char to String 
            }
        }
        return Output;
    }

    public static String decryption_function(String Cipher) throws FileNotFoundException {
        String key = Read_From_File("key.txt");
        //System.out.println("\nThe key From File is : " + key + " \n");
        //Cipher = Cipher.toLowerCase();
        String Output = "";
        for (int i = 0; i < Cipher.length(); i++) {
            char char_s = Cipher.charAt(i);
            char c;
            if (key.contains(String.valueOf(char_s))) {
                int charpos = key.indexOf(char_s);
                c = str.charAt(charpos);
                Output += c;
            } else {
                Output += String.valueOf(Cipher.charAt(i)); // To convert Strang Char to String 
            }
        }
        return Output;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader object = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter a key");
//        String key = object.readLine();
//        key=key.toLowerCase();
        Scanner obj = new Scanner(System.in);
        while (true) {
            System.out.println("\n1- To Test Program.\n2- TO Break.\n");
            String x = obj.nextLine();
            
            if ("1".equals(x)) {
                System.out.println("Enter a plain Text");
                String msg = object.readLine();
                System.out.println("Encrypted Text is : " + encryption_Function(msg));
                System.out.println("\nEnter Decrypted Text : ");
                String DE_msg = object.readLine();
                System.out.println("The Decrypted Text : " + decryption_function(DE_msg));
            } else {
                break;
            }

        }
    }
}
/// key : phqgiumeaylnofdxjkrcvstzwb
/// input : mohamed hamdy mohamed
/// output :odepoig epogw odepoig

/// gcsvmxplbeikwntuyaorjfdqzh
/// mohamed hamdy mohamed
/// wtlgwmv lgwvz wtlgwmv
