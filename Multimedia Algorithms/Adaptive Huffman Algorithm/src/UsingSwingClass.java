///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ass_2;
//
//import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
//import java.util.Scanner;
//import java.awt.Choice;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//
///**
// *
// * @author AA
// */
//public class ASS_2 {
//
//    /**
//     * @param args the command line arguments
//     * @throws java.io.FileNotFoundException
//     */
//    public static void main(String[] args) throws FileNotFoundException {
//        TreeNode root = new TreeNode();
//
//        Scanner x = new Scanner(System.in);
//        System.out.println("1 for compressing\n2 for decompressing\n3 Exit");
//        Compress object = new Compress();
//        DeCompress obj = new DeCompress();
//
//        while (true) {
//            int Choice = x.nextInt();
//            if (Choice == 1) {
//
//                String line = x.next();
//                String result = object.compress(line);
//                String content = result;
//                String path = "F:\\Fci\\Year Three\\IT433 - Multimedia\\Multimedia\\Assignment\\ASS_2\\Compession.txt";
//
//                try {
//
//                    Files.write(Paths.get(path), content.getBytes());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//
//            } else if (Choice == 2) {
//                String code = x.next();
//                obj.deCompress(code);
//                break;
//            } else {
//                break;
//            }
//
//        }
//}


import ass_2.Compress;
import ass_2.DeCompress;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.awt.Choice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class UsingSwingClass {

    /**
     * Implementinng the input dialog box and show message +box Something to
     * check on the showMessage dialog box JOption.PLAIN_MESSAGE is the one
     * without any icon
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        Compress C_obj = new Compress();
        DeCompress D_obj = new DeCompress();

        String line1, line2, temp1, temp2, Choice, temp_choice, path1, path2;
        Scanner x = new Scanner(System.in);

        temp1 = JOptionPane.showInputDialog("Compression");

        line1 = C_obj.compress_function(temp1);

        JOptionPane.showMessageDialog(null, "The Result is : " + line1, "Compression Results", JOptionPane.PLAIN_MESSAGE);
        String content = line1;
        String path = "F:\\Fci\\Year Three\\IT433 - Multimedia\\Multimedia\\Assignment\\ASS_2\\Compession.txt";

        try {

            Files.write(Paths.get(path), content.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        temp2 = JOptionPane.showInputDialog("DeCompression");

        line2 = D_obj.deCompress_function(temp2);

        path = "F:\\Fci\\Year Three\\IT433 - Multimedia\\Multimedia\\Assignment\\ASS_2\\Decompession.txt";

        try {
            Files.write(Paths.get(path), line2.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "The Result is : " + line2, "Decompression Results", JOptionPane.PLAIN_MESSAGE);

    }
}

// 100000101000010001000011101000101110 (ABCCCAAAA)

// 1010100011100100011001011011001001110110011011111000110010011 (TreeNode)