/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

/**
 *
 * @author AA
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class Encryption {

    Scanner x = new Scanner(System.in);
    String plainText_input;
    String initialState_str;
    int[] initialstate_Array = new int[9];
    int[][] Two_D_Array = new int[300][9];

    String Line_number_two_in_File;
    static String Binary_input = "";
    int[] XOR_Index_Values_in_File = new int[9];
    int constant_Line_3;
    int b = 0;
    int XOR_count = 0;

    Scanner sc;
    Formatter out;

    public void OpenFile_with_path(String path) throws FileNotFoundException {
        out = new Formatter(path);
    }

    public void closefile() {
        out.close();
    }

    public void Write_in_File(String code) {

        out.format("%s", code);
        out.format("%n");
        out.flush(); // 34an yktb 3al file

    }

    void Read_Data_File() throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        initialState_str = scan.nextLine();
                        int Counter = 0;
                        for (int k = 0; k < initialState_str.length(); k++) {
                            if (Character.isDigit(initialState_str.charAt(k)) == true) {
                                initialstate_Array[Counter] = Integer.parseInt(initialState_str.charAt(k) + "");
                                Counter++;
                            }
                        }   
                        break;
                    case 1:
                        Line_number_two_in_File = scan.nextLine();
                        break;
                    default:
                        String str = "";
                        str = scan.nextLine();
                        constant_Line_3 = Integer.parseInt(str);
                        break;
                }
            }

        }
    }

    void TextToBinary_Function() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message to Encryption");
        plainText_input = input.nextLine();
        String Temp = "";
        char[] TextChar = plainText_input.toCharArray();     //convert plaintext to binary       
        for (int i = 0; i < TextChar.length; i++){
            if(TextChar[i] == ' '){
                Binary_input += (0+Integer.toBinaryString(TextChar[i]));
            }
            else{
                Binary_input += Integer.toBinaryString(TextChar[i]);
            }
            Temp += Integer.toBinaryString(TextChar[i])+" ";
        }
        System.out.print(Temp);
    }
    

    void Number_of_ones() {
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            if (Line_number_two_in_File.charAt(i) == '1') {
                XOR_Index_Values_in_File[counter] = i;
                counter++;
                XOR_count++;
            }
        }
    }

    void Encription_Function() throws FileNotFoundException {
        System.arraycopy(initialstate_Array, 0, Two_D_Array[0], 0, 9);
        int x = XOR_count;
//        b += XOR_Index_Values_in_File.length - 1;
        for (int i = 0; i < Binary_input.length() + constant_Line_3; i++) {

            if (XOR_count == 0) {
                XOR_count += x;
            }
            /// swapping Shifting 
            for (int j = 1; j < 9; j++) {
                Two_D_Array[i + 1][j] = Two_D_Array[i][j - 1];//swaping 
            }

            int Test = 0;//zeros and ones
            //loop to put first element at row 
            for (int k = XOR_count; k > 0; k--) {
                Test += Two_D_Array[i][XOR_Index_Values_in_File[XOR_count - 1]];
                XOR_count--;
            }//end loop

            //check for XoR 
            if (Test % 2 == 1) {
                Two_D_Array[i + 1][0] = 1;
            } else {
                Two_D_Array[i + 1][0] = 0;
            }

        }
        
        /// print table
        int[] Key = new int[Binary_input.length() + constant_Line_3];
        for (int i = 0; i < Binary_input.length() + constant_Line_3; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(Two_D_Array[i][j] + "");
                if (j == 8) {
                    Key[i] = Two_D_Array[i][j];
                    System.out.println("");
                }
            }
        }
        System.out.print("All Key :");
        for (int j = 0; j < Key.length; j++) {
            System.out.print(Key[j] + "");
        }
        // key XOR plaintext
        String temp1 = "", temp2 = "";

        for (int i = 0; i < Key.length; i++) {
            if (i < (constant_Line_3)) {
                temp1 += Key[i];
            } else {
                temp2 += Key[i];
            }
        }
        System.out.println("\nIgnore Part in Key is :" + temp1);
        System.out.println("Not Ignore Part in Key is :" + temp2);

        String Str = "";
        for (int i = 0; i < Key.length - constant_Line_3; i++) {
            Str += Key[constant_Line_3 + i];
        }
        

        /// XoR Part
        String Encrypyion_Text = "";
        for (int i = 0; i < Binary_input.length(); i++) {
            if (Str.charAt(i) == Binary_input.charAt(i)) {
                Encrypyion_Text += "0";
            } else {
                Encrypyion_Text += "1";
            }
        }
        System.out.println("\nEncryption Text is : " + Encrypyion_Text);
        
        /// Files Part 
        OpenFile_with_path("Encryption.txt");
        Write_in_File(Encrypyion_Text);
        closefile();
        OpenFile_with_path("Key.txt");
        Write_in_File(temp2);
        closefile();
    }    
}



