/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg4;

/**
 *
 * @author AA
 */
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Floating {

    ArrayList<Element> Elements_Compression_ArrayList = new ArrayList<>();
    ArrayList<Element> Elements_Decompression_ArrayList = new ArrayList<>();
    JFrame frame;
    JTextArea output_textArea;

    public Floating() {
    }



public class Element {

    private String letter;
    private double probability;
    private double low_range;
    private double high_range;

    public Element(String letter, double probability, double low_range, double high_range) {
        this.letter = letter;
        this.probability = probability;
        this.low_range = low_range;
        this.high_range = high_range;
    }

    public Element() {
    }

    public void Set_Letter(String letter) {
        this.letter = letter;
    }

    public String Get_Letter() {
        return letter;
    }

    public void Set_Prob(double probability) {
        this.probability = probability;
    }

    public double Get_Prob() {
        return probability;
    }

    public void Set_Low(double low_range) {
        this.low_range = low_range;
    }

    public double Get_Low() {
        return low_range;
    }

    public void Set_High(double high_range) {
        this.high_range = high_range;
    }

    public double Get_High() {
        return high_range;
    }

}

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

    public static String Sort_String(String inputString) {
        // convert input string to char array 
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public void compress() throws FileNotFoundException {
        String data = Read_From_File("Original Data.txt");
        System.out.println("orignal data is :"+data);
        String str = Sort_String(data);
        System.out.println("orignal data is :"+str);
        String temp = "";
        Calculate_probability(str);

        for (int i = 0; i < Elements_Compression_ArrayList.size(); i++) {
          System.out.println(Elements_Compression_ArrayList.get(i).Get_Letter() + " ----------- prob = " + Elements_Compression_ArrayList.get(i).Get_Prob() + " ----------- low =  " + Elements_Compression_ArrayList.get(i).Get_Low() + " ----------- high =  " + Elements_Compression_ArrayList.get(i).Get_High());
            //temp+=(elements_comp.get(i).Get_Letter() + " ----------- prob = " + elements_comp.get(i).Get_Prob() + " ----------- low =  " + elements_comp.get(i).Get_Low() + " ----------- high =  " + elements_comp.get(i).Get_High());

        }

        double lower = 0;
        double upper = 1;

        double old_lower = 0;
        double old_upper = 1;

        for (int i = 0; i < data.length(); i++){
            Element cur = Search_Item_in_Arraylist(data.charAt(i), Elements_Compression_ArrayList);

            lower = old_lower + (old_upper - old_lower) * cur.Get_Low();
            upper = old_lower + (old_upper - old_lower) * cur.Get_High();
            old_lower = lower;
            old_upper = upper;
            System.out.println("Char " + data.charAt(i) + " Range  = " + lower + " , " + upper);
        }

        double Avarage_output = (lower + upper) / 2;
        String code = Double.toString(Avarage_output);

        int length = data.length();
        String len = Integer.toString(length);

        OpenFile_with_path("Compressed Data.txt");  // save number & length & prob 3ala l file

        Write_in_File(code);
        Write_in_File(len);
        Save_probabilities_in_Com_File();

        closefile();

        System.out.println("code = " + code);
        addUpdates("code = " + code);
        //addUpdates(temp);

    }

    public void Read_ComElement_And_Find_Range_For_IT() // read letters and thier probablity from file and calculate commulative ranges for each one
    {
        while (sc.hasNext()){
            String data = sc.nextLine();
            String letter = "" + data.charAt(0);
            Elements_Decompression_ArrayList.add(new Element(letter, Double.parseDouble(data.substring(1)), 0 , 0));
        }
        Calculate_range(Elements_Decompression_ArrayList);
    }

    public void decompress() throws FileNotFoundException {

        sc = new Scanner(new File("Compressed Data.txt"));
        double code = Double.parseDouble(sc.nextLine()); // read float num
        int len = Integer.parseInt(sc.nextLine()); // read length

        Read_ComElement_And_Find_Range_For_IT(); // read prob 

        sc.close();

        System.out.println("---------------------------------------------------------------------------------------------\n");
        //addUpdates("---------------------------------------------------------------------------------------------\n");

        for (int i = 0; i < Elements_Decompression_ArrayList.size(); i++) {
            System.out.println(Elements_Decompression_ArrayList.get(i).Get_Letter() + " ----------- prob = " 
                    + Elements_Decompression_ArrayList.get(i).Get_Prob() + " ----------- low =  " + Elements_Decompression_ArrayList.get(i).Get_Low()
                    + " ----------- high =  " + Elements_Decompression_ArrayList.get(i).Get_High());
            //addUpdates(elements_decomp.get(i).Get_Letter() + " ----------- prob = " + elements_decomp.get(i).Get_Prob() + " ----------- low =  " + elements_decomp.get(i).Get_Low() + " ----------- high =  " + elements_decomp.get(i).Get_High());

        }

        String stream = ""; // el output data 

        double lower = 0, upper = 1; 
        double old_lower = 0, old_upper = 1;

        for (int i = 0; i < len; i++) {
            double cur_code = (code - old_lower) / (old_upper - old_lower);  // b7sb el code lel iteration li na feha 

            System.out.println("\ncur code = " + cur_code);
            System.out.println("lower = " + lower);
            System.out.println("upper = " + upper);
//                addUpdates("\ncur code = " + cur_code);
//                addUpdates("lower = " + lower);
//                addUpdates("upper = " + upper);

            for (int j = 0; j < Elements_Decompression_ArrayList.size(); j++) {
                if ((cur_code > Elements_Decompression_ArrayList.get(j).Get_Low()) && (cur_code < Elements_Decompression_ArrayList.get(j).Get_High())) // b4ofo howa fe anhy range
                {
                    stream += Elements_Decompression_ArrayList.get(j).Get_Letter(); // bgeeb el 7arf el mokabel lel range dah 
                    lower = old_lower + (old_upper - old_lower) * Elements_Decompression_ArrayList.get(j).Get_Low(); // b3ml lel 7arf da new range ... expand
                    upper = old_lower + (old_upper - old_lower) * Elements_Decompression_ArrayList.get(j).Get_High();

                    old_lower = lower;
                    old_upper = upper;

                    break;
                }

            }

        }
        OpenFile_with_path("Decompressed Data.txt");
        Write_in_File(stream);
        closefile();
        System.out.println("stream = " + stream);
        addUpdates("stream = " + stream);
    }

    public void Calculate_range(ArrayList<Element> arr) {
        double low = 0;

        for (int i = 0; i < arr.size(); i++) {
            double high = arr.get(i).Get_Prob() + low;
            arr.get(i).Set_Low(low);
            arr.get(i).Set_High(high);
            low = high;
        }

    }

    public void Calculate_probability(String data) {
        for (int i = 0; i < data.length(); i++) {
            String letter = "";
            letter += data.charAt(i);

            boolean found = false;

            for (int j = 0; j < Elements_Compression_ArrayList.size(); j++) {
                if (Elements_Compression_ArrayList.get(j).Get_Letter().equals(letter)) {
                    double new_prob = Elements_Compression_ArrayList.get(j).Get_Prob() + 1;
                    Elements_Compression_ArrayList.get(j).Set_Prob(new_prob);
                    found = true;
                    break;
                }
            }

            if (found == false) {
                Elements_Compression_ArrayList.add(new Element(letter, 1, 0, 0));
            }

        }

        for (int i = 0; i < Elements_Compression_ArrayList.size(); i++) {
            double new_prob = Elements_Compression_ArrayList.get(i).Get_Prob() / data.length();
            Elements_Compression_ArrayList.get(i).Set_Prob(new_prob);

        }

        Calculate_range(Elements_Compression_ArrayList);
    }

    public Element Search_Item_in_Arraylist(char x, ArrayList<Element> arr) {
        String item = "";
        item += x;
        Element result = new Element();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).Get_Letter().equals(item)) {
                result = arr.get(i);
                break;

            }
        }
        // return Element info from array list
        return result;
    }

    public void Save_probabilities_in_Com_File() {
        for (int i = 0; i < Elements_Compression_ArrayList.size(); i++) {
            String item = Elements_Compression_ArrayList.get(i).Get_Letter() + Elements_Compression_ArrayList.get(i).Get_Prob();
            Write_in_File(item);
        }
    }
    
    
 

    /**
     * Initialize the contents of the frame.
     */
    void addUpdates(String str){

        frame = new JFrame();
        frame.setBounds(150, 150, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        output_textArea = new JTextArea();
        output_textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        output_textArea.setBounds(20, 15, 450, 425); // right , top , width , high 
        frame.getContentPane().add(output_textArea);
       
        output_textArea.append(str);
        frame.setVisible(true);
    }
    
}
