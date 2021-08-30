package javaapplication38;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class DES {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<String> subkeys = new ArrayList<String>();
    public static ArrayList<String> input = new ArrayList<String>();
    public static ArrayList<String> Encrtption_output = new ArrayList<String>();

    static String key;
    public static int KEY_LENGTH = 64;
    private static String left;
    private static String New_left;

    private static String right;
    private static String Old_right;

    private static final byte[] PC1_Key_to_56 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4
    };

    private static final byte[] KEY_SHIFTS = {0, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    private static final byte[] PC2_Key_to_48 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };

    private static final byte[][] s1 = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };

    private static final byte[][] s2 = {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
    };

    private static final byte[][] s3 = {
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
    };

    private static final byte[][] s4 = {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
    };

    private static final byte[][] s5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
    };

    private static final byte[][] s6 = {
        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
    };

    private static final byte[][] s7 = {
        {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
    };

    private static final byte[][] s8 = {
        {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
    };

    private static final byte[][][] s = {s1, s2, s3, s4, s5, s6, s7, s8};

    private static final byte[] expandRight
            = {
                32, 1, 2, 3, 4, 5,
                4, 5, 6, 7, 8, 9,
                8, 9, 10, 11, 12, 13,
                12, 13, 14, 15, 16, 17,
                16, 17, 18, 19, 20, 21,
                20, 21, 22, 23, 24, 25,
                24, 25, 26, 27, 28, 29,
                28, 29, 30, 31, 32, 1
            };

    static byte[] permution_matrix
            = {
                16, 7, 20, 21,
                29, 12, 28, 17,
                1, 15, 23, 26,
                5, 18, 31, 10,
                2, 8, 24, 14,
                32, 27, 3, 9,
                19, 13, 30, 6,
                22, 11, 4, 25
            };

    static byte[] IP
            = {
                58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8,
                57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7
            };

    static byte[] IP_1
            = {
                40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25
            };

    public static String TextToBinary(String plainText_input) {

        String Binary_input = "";
        String Temp = "";
        for (int i = 0; i < plainText_input.length(); i++) {
            if (plainText_input.charAt(i) == ' ' || plainText_input.charAt(i) == '#') {
                Binary_input += (0 + (0 + Integer.toBinaryString(plainText_input.charAt(i))));
            } else {
                Binary_input += (0 + Integer.toBinaryString(plainText_input.charAt(i)));
            }
        }
        return Binary_input;

    }

    static void generatekey(String key) {
//        System.out.println("The key Length :" + key.length());
        String key1 = permute(key, PC1_Key_to_56);
        System.out.println("key 64 bit :" + key);
//        System.out.println("key 56 bit :" + key1.length());

        String prevleft = key1.substring(0, key1.length() / 2); // D part
        String prevright = key1.substring(key1.length() / 2);   // C part
        for (int i = 0; i < 16; i++) {

            String newleft = shift(prevleft, KEY_SHIFTS[i]);
            String newright = shift(prevright, KEY_SHIFTS[i]);
            prevleft = newleft;
            prevright = newright;
            String subKey = prevleft + prevright;
            subKey = permute(subKey, PC2_Key_to_48);
            subkeys.add(subKey);
        }
        //System.out.println("Subkeys :" + subkeys);
    }

    private static String shift(String input, int round) {
        StringBuilder subString = new StringBuilder(input);
        for (int i = 0; i < round; i++) {
            char letter = subString.charAt(0);
            subString.append(letter);
            subString.deleteCharAt(0);
        }
        return subString.toString();
    }

    public static ArrayList<String> cipher(String Orignal_plaintext) {

        System.out.println("\nplaintext :" + Orignal_plaintext);
        Orignal_plaintext = TextToBinary(Orignal_plaintext);

        for (int i = 0; i < Orignal_plaintext.length(); i += 64) {
            String tmp = "";
            for (int j = i; j < i + 64; j++) {
                if (j == Orignal_plaintext.length()) {
                    break;
                }
                tmp += Orignal_plaintext.charAt(j);
            }
            input.add(tmp);
        }

        System.out.println("\ninput arraylist :\n");
        for (int k = 0; k < input.size(); k++) {
            System.out.println(input.get(k) + "    " + input.get(k).length() + "\n");

        }

        String s = " ";

        for (int t = 0; t < input.size(); t++) {
            String plaintext;
            plaintext = input.get(t);
            String Str_temp = "";

            for (int i = 0; i < plaintext.length(); i += 8) {
                String temp = plaintext;
                Str_temp += temp.substring(i, i + 8);
                Str_temp += " ";
            }
            System.out.println("\nEncryption msg with Space :" + Str_temp);

            //initial permutation
            plaintext = permute(plaintext, IP);
            System.out.println("initial permutation IP : " + plaintext);

            //split the text into 2 parts
            left = plaintext.substring(0, plaintext.length() / 2);
            right = plaintext.substring(plaintext.length() / 2);

            //16 round of des Agorithm
            for (int i = 0; i < 16; i++) {
                Old_right = right;
                mixer(subkeys.get(i));
            }

            // last swap after round 16  
            String result = right + left;

            //final permutation
            String output = permute(result, IP_1);
            System.out.println("initial permutation IP^1 : " + result);
            String Str_temp_1 = "";
            Encrtption_output.add(output);
            System.out.println("Final Encryption Binary output :" + output);

            // make output 8 bits and but spaces between every 8 bit
            for (int i = 0; i < output.length(); i += 8) {
                Str_temp_1 += output.substring(i, i + 8);
                Str_temp_1 += " ";
            }
            //convert the output to characters with space 
            String letters = Str_temp_1;

            for (int i = 0; i < letters.length(); i += 9) {
                String temp = letters.substring(i, i + 8);
                int num = Integer.parseInt(temp, 2);
                char letter = (char) num;
                s = s + letter;
            }
            System.out.println("\nFinal Encryption Binary output with Space : " + Str_temp_1);
        }
        System.out.println("\nFinal Encryption Text output is : " + s);
        System.out.println("Final Arraylist output :" + Encrtption_output);

        return Encrtption_output;
    }

    public static String Decryption(ArrayList<String> Dec_Arraylist) {
        System.out.println("                           \n\n/////////////////////////////////////Decryption/////////////////////////////////////\n\n");

        System.out.println("cipherText : " + Dec_Arraylist);
        String s = " ";

        for (int t = 0; t < Dec_Arraylist.size(); t++) {

            String cipherText;
            cipherText = Dec_Arraylist.get(t);

            cipherText = permute(cipherText, IP);

            left = cipherText.substring(0, cipherText.length() / 2);
            right = cipherText.substring(cipherText.length() / 2);

            for (int i = 15; i >= 0; i--) {
                Old_right = right;
                mixer(subkeys.get(i));
            }
            String result = right + left;
            String output = permute(result, IP_1);

            String Str_temp = "";
            for (int i = 0; i < output.length(); i += 8) {
                Str_temp += output.substring(i, i + 8);
                Str_temp += " ";
            }
            System.out.println("Final Decryption Binary output : " + output);

            System.out.println("\nDecryption msg with Space with space :" + Str_temp);
            String letters = Str_temp;

            for (int i = 0; i < letters.length(); i += 9) {
                String temp = letters.substring(i, i + 8);
                int num = Integer.parseInt(temp, 2);
                char letter = (char) num;
                s = s + letter;
            }
        }
        System.out.println("\nFinal Decryption Text is :" + s + "\n");
        return s;
    }

    private static String permute(String plain, byte[] table) {
        StringBuilder output = new StringBuilder();
        for (int index : table) {
            output.append(plain.charAt(index - 1)); //index of value in table
        }

        return output.toString();
    }

    private static void mixer(String subkey) {
        // System.out.println("\nLeft = " + left);
        // System.out.println("right= " + right + "\n");
        right = expandDBox(right);
        right = Xor_Function(right, subkey);
        right = SBox_Function(right);
        right = permute(right, permution_matrix);
        right = Xor_Function(right, left);
        left = Old_right;
    }

    private static String expandDBox(String right1) {
        String expanded = new String();
        right1 = permute(right, expandRight);
        for (int i : expandRight) {
            expanded += right1.charAt(i - 1);
        }
        //System.out.println("PE :" + expanded);
        return expanded;
    }

    private static String Xor_Function(String x1, String x2) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < x1.length(); i++) {
            output.append(x1.charAt(i) ^ x2.charAt(i));
        }
        return output.toString();
    }

    private static String SBox_Function(String newright) {
        StringBuilder builder = new StringBuilder(newright);
        for (int i = 0; i < 48; i += 6) {
            String sub = "";
            sub = newright.substring(i, i + 6);
            char first = sub.charAt(0);
            char last = sub.charAt(5);
            int row = Integer.parseInt("" + first + last, 2);
            int coloumn = Integer.parseInt(sub.substring(1, 5), 2);
            int sbox_values = s[i / 6][row][coloumn];
            String result = Integer.toBinaryString(sbox_values);
            builder.append(result);
        }
        return builder.toString();
    }

    public static void main(String args[]) {
        ArrayList<String> result_1 = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String x;
        while (true) {
            System.out.println("Enter Your Key Please : ");
            x = sc.nextLine();
            if (x.length() != 8) {
                System.out.println("invalid input true agrain Key must be 8 chars only . ");
            } else {
                break;
            }
        }

        generatekey(TextToBinary(x));
        System.out.println("Enter PlainText : ");
        String text;
        text = sc.nextLine();
        int input_len = text.length();
//        System.out.println("\ninput_len : " + input_len);

        double temp1;
        int temp4;

        if (input_len % 8 != 0) {
            temp1 = input_len / 8.0;
            int temp2 = (int) Math.ceil(temp1);
            int temp3 = 8 * temp2;
            temp4 = temp3 - input_len;

            for (int i = 0; i < temp4; i++) {
                text += "#";
            }
            System.out.println("New Text :" + text);
            System.out.println(" New Text Length :" + text.length());
            result_1 = cipher(text);
            String res = Decryption(result_1);
            System.out.println("Final Decyption User Text is : " + res.substring(0, res.length() - temp4));

        } else {
            result_1 = cipher(text);
            String res = Decryption(result_1);
        }
    }
}
