/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author AA
 */
public class OS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("1- To Comperation .\n2- To Decomperation.\n3- To Exist .\n Enter your choose : ");
        Scanner scannerobject = new Scanner(System.in);
        int choose = scannerobject.nextInt();
        if (choose == 1) {
            ArrayList<String> arraylist = new ArrayList<>();
            String temp = "";

            System.out.println("Enter Your String :");
            Scanner obj = new Scanner(System.in);
            String input = obj.nextLine();
            String[] stringToCharArray = input.split("");
            arraylist.add("null");
            int index = 0;
            for (int i = 0; i < stringToCharArray.length; i++) {
                if ("".equals(temp)) {
                    temp += stringToCharArray[i];

                }

                boolean check = true;
                for (int j = 0; j < arraylist.size(); j++) {

                    if (arraylist.get(j).equals(temp) && i != stringToCharArray.length - 1) {
                        index = j;
                        temp += stringToCharArray[i + 1];
                        check = false;
                        break;

                    }
                }
                if (check) {
                    if (index == 0) {
                        System.out.println("< 0 , " + stringToCharArray[i] + " > ");
                        arraylist.add(temp);
                        temp = "";
                    } else {
                        System.out.println("< " + (index) + " , " + stringToCharArray[i] + " > ");
                        arraylist.add(temp);
                        temp = "";

                    }
                }

            }
            System.out.println("The Array List is : ");
            for (int k = 0; k < arraylist.size(); k++) {
                System.out.println(arraylist.get(k));
            }
        } 
        // ///////////////////////////////////////////////////////////////////////////////////////////////////////
        else if (choose == 2) {
            ArrayList<String> Darraylist = new ArrayList<>();
            ArrayList<String> Dintger = new ArrayList<>();
            ArrayList<String> Dchar = new ArrayList<>();

            //String temp = "";
            System.out.println("Enter Your String :");

            Scanner Dobj = new Scanner(System.in);
            String Dinput = Dobj.nextLine();
            String[] Dinputstring = Dinput.split("");
            String temp = "";
            String Temp_2 = "";
            int index;
            Darraylist.add("null");
            for (int k = 0; k < Dinputstring.length; k += 5) {
                if (k != Dinputstring.length - 1) {
                    Dintger.add(Dinputstring[k + 1]);
                    Dchar.add(Dinputstring[k + 3]);
                }
            }
            
            
            for (int k = 0; k < Dintger.size(); k++) {
                if (0 == Integer.parseInt(Dintger.get(k))) { // convert from string to intger

                    Darraylist.add(Dchar.get(k));
                    temp += Dchar.get(k);
                } else {
                    index = Integer.parseInt(Dintger.get(k)); // convert from string to intger

                    Temp_2 += Darraylist.get(index);
                    Temp_2 += Dchar.get(k);
                    temp += Temp_2;
                    Darraylist.add(Temp_2);
                    Temp_2 = "";
                }
            }
            System.out.println(temp);
            System.out.println("The ArrayList is : ");
            for (int k = 0; k < Darraylist.size(); k++) {
                System.out.println(Darraylist.get(k));
            }
            
        }
            
        // ///////////////////////////////////////////////////////////////////////////////////////////////////////

        while (choose != 1 || choose != 2) {
            break;
        }
        // abaababaa
        // <0,a><0,b><1,a><2,a><4,a>

    }
}

