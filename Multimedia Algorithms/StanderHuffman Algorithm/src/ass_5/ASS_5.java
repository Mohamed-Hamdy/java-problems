/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_5;

import static ass_5.Decompress.decompress;
import ass_5.StandardHuffman.Node;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author AA
 */
public class ASS_5 {

    static Vector<Vector<String>> groups = new Vector<Vector<String>>();
    static Vector<String> AC_coeff = new Vector<String>();
    static Vector<String> descriptors = new Vector<>();
    static Vector<Vector<Integer>> Cat_table = new Vector<Vector<Integer>>();
    static Hashtable<String, String> codes = new Hashtable<String, String>();

    public static void Make_Groups_Function(Vector<String> msg) {
        for (int i = 0; i < msg.size(); i++) {
            Vector<String> data = new Vector<String>();
            if (msg.get(i).equals("EOP")) {
                data.add(msg.get(i));
            } else if (i == 0 && !msg.get(i).equals("0")) {
                data.add(msg.get(i));
            } else if (i != 0 && msg.get(i).equals("0")) {
                for (int j = i; j < msg.size(); j++) {
                    String s = msg.get(j);

                    data.add(s);
                    if (!s.equals("0")) {
                        i = j;

                        break;
                    }
                }
            } else if (i != 0 && !msg.get(i).equals("0")) {

                data.add(msg.get(i));
            }
            groups.add(data);
        }
    }

    public static void Get_AC_coeff() { // loop on groups and get non zero values to make binary represent to it 
 
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).size(); j++) {
                if (!groups.get(i).get(j).equals("0")) {
                    AC_coeff.add(groups.get(i).get(j));
                }
            }
        }
    }

    public static void Bulid_Catrgory_Table_Function() {
        for (int i = 1; i <= 10; i++) {
            Vector<Integer> data = new Vector<>();
            if (i == 1) {
                data.add(1);
                data.add(-1);
            } else {
                int j;
                int Lower_limit = (int) Math.pow(2, i - 1);  // start Lower limit 2 ^ index - 1 
                int upper_Limit = (int) (Math.pow(2, i) - 1);
                for (j = upper_Limit; j >= Lower_limit; j--) {
                    data.add(j);
                    int inverce_number = j * -1;
                    data.add(inverce_number);
                }
            }
            Cat_table.add(data);
        }
    }

    public static void Descriptors_Function() {
        for (int i = 0; i < groups.size() - 1; i++) {
            int Counter_Of_Zeros = 0;
            String Value = "";
            String str = "";
            for (int j = 0; j < groups.get(i).size(); j++) {
                if (groups.get(i).get(j).equals("0")) {
                    Counter_Of_Zeros++;
                }
                Value = groups.get(i).get(j);
            }
            Value = String.valueOf(Get_Index(Value));
            str += Counter_Of_Zeros + "/" + Value;
            descriptors.add(str);
        }// For EOP 
        descriptors.add(groups.get(groups.size() - 1).get(0));
    }

    public static int Get_Index(String a) {
        int x = Integer.parseInt(a);
        int index = 0;
        for (int j = 0; j < Cat_table.size(); j++) {
            for (int k = 0; k < Cat_table.get(j).size(); k++) {
                if (x == Cat_table.get(j).get(k)) {
                    index = j + 1;
                }
            }
        }
        return index;
    }

    public static double Get_Prob_For_Descriptors_Numbers(String str) {
        double result = 0.0;
        for (int i = 0; i < descriptors.size(); i++) {
            if (str.equals(descriptors.get(i))) {
                result++;
            }
        }
        return result / descriptors.size();
    }

    public static String Get_Representation_Bits(String s) {
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

    public static void Compress() throws IOException {
        Bulid_Catrgory_Table_Function();
        File file = new File("input.txt");
        Scanner sc = new Scanner(file); // read compress file
        Vector<String> Input_Vec = new Vector<>();
//        System.out.println("Enter Your Input :");
//        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.next();
            Input_Vec.add(str);
            if (str.equals("EOP")) {
                break;
            }
        }
        Make_Groups_Function(Input_Vec);
        Get_AC_coeff();
        Descriptors_Function();
        sc.close();

        // send all desriptors and prob to Huffman Tree (Sort by prob in Tree)
        Vector<StandardHuffman.Node> nodes = new Vector<StandardHuffman.Node>();
        for (int i = 0; i < descriptors.size(); i++) {
            if (!StandardHuffman.Node.contains(descriptors.get(i), nodes)) {
                StandardHuffman.Node n = new StandardHuffman.Node(Get_Prob_For_Descriptors_Numbers(descriptors.get(i)), descriptors.get(i));
                n.right = null;
                n.left = null;
                nodes.add(n);

            }
        }

        Collections.sort(nodes, new StandardHuffman.Node.MyComparator2());
// send
// put numbers in descriptors vector in tree 
        //  put descriptors numbers in tree denpend on prob .
        StandardHuffman.compress(nodes);
        StandardHuffman.Huffman_SetCode(nodes.get(0));
        StandardHuffman.print_tree(nodes.get(0));
        String Compress_Result = "";

        for (int i = 0; i < descriptors.size(); i++) {
            if (!descriptors.get(i).equals("EOP")) {
                Compress_Result += codes.get(descriptors.get(i)) + "," + Get_Representation_Bits(AC_coeff.get(i)) + " ";
            } else {
                Compress_Result += codes.get(descriptors.get(i));
            }
        }
        FileWriter fw = new FileWriter("Compress_Data.txt");
        fw.write(Compress_Result);

        fw.close();
// *******************************************************************************************************************

    }

    public static void Decompress() throws IOException {
        decompress("Compress_Data.txt");

    }
}
/* 
-2
0
0
2
0
0
3
2
0
1
0
0
-2
0
-1
0
0
1
0
0
-1
EOP
*/
// ****************************
/*
-2
0
0
-5
0
-3
0
0
0
3
EOP
*/