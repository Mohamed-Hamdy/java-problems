/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_5;

import static ass_5.ASS_5.Cat_table;
import static ass_5.ASS_5.codes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;




public class Decompress {

    static Hashtable<String, String> binary_table = new Hashtable<String, String>();

    public static void decompress(String path) throws IOException {
        File file = new File(path);
        Scanner sc = new Scanner(file); // read compress file
        String File_compressed_msg = sc.nextLine();
        sc.close();
        Build_Binary_Table_From_Category_Table(); // get binary number for all numbers that found in cat_table and store it in binary_table hashtable  
        String[] arr_Space_Spliter = File_compressed_msg.split(" "); // split file msg by space
        String result = "";
        for (int i = 0; i < arr_Space_Spliter.length; i++) { // loop to split arr_1 (arr_Space_Spliter) by comma 
            String arr_Comma_Spliter[] = arr_Space_Spliter[i].split(","); // split arr_1 (arr_Space_Spliter) by comma
            // loop at arr_comma_spliter
            for (int j = 0; j < arr_Comma_Spliter.length; j++){
                // if i found number .
                if (j == 0 && !arr_Comma_Spliter[j].equals(codes.get("EOP"))){
                    String s = Get_Key_Function(arr_Comma_Spliter[j]);
                    // say s = 0/2
                    int n = Integer.parseInt(s.charAt(0) + ""); // take first number or part in s to put n zeros in output 
                    for (int k = 0; k < n; k++) { // 2/2 put counter of 2 bast ( 0 0 ) 
                        result += "0 ";
                    }
                } else if (j == 0 && arr_Comma_Spliter[j].equals(codes.get("EOP"))) {
                    result += "EOP";
                } 
                // write scound part from s into result.
                else {
                    result += binary_table.get(arr_Comma_Spliter[j]) + " ";
                }

            }
        }
        FileWriter fw = new FileWriter("Decompress_Data.txt");
        fw.write(result);
        fw.close();
    }
    public static void Build_Binary_Table_From_Category_Table() {
        for (int i = 0; i < Cat_table.size(); i++) {
            for (int j = 0; j < Cat_table.get(i).size(); j++) {
                String s = Cat_table.get(i).get(j).toString();
                binary_table.put(Get_Representation_Bits_To_CatTable_Numbers(s), s);
            }
        }
    }

    public static String Get_Representation_Bits_To_CatTable_Numbers(String s) {
        int num = Integer.parseInt(s);
        if (num > 0) {
            return Integer.toBinaryString(num);
        }
        num = num * -1;
        String str = Integer.toBinaryString(num);
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;
    }
 // get descrip=tors numbers from compress file
    public static String Get_Key_Function(String Str) {
        
        // check if s index in comma_spliter_arr found in codes vector then i get this key and insert in in part1 of index 1 in map then 
        // send next code check and put his value in part 2 in index 1 to make first vakue say 0/2  then retuen it 
        String key = null;
        for (Map.Entry<String, String> entry : codes.entrySet()) {
            if (Str.equals(entry.getValue())){
                key = entry.getKey();
                break;
            }
        }
        return key;
    }
}
