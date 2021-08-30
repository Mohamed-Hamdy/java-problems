/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vector_quantizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author AA
 */
public class Files {

    public Files() {

    }

    private Scanner scan;

    public void openFile(String fileName) throws FileNotFoundException {

        scan = new Scanner(new File(fileName));
    }

    public String readfile(){
        String contents = "";
        while (scan.hasNextLine()) {
            contents += scan.nextLine();
        }
        return contents;
    }

    public ArrayList<Integer> readArrayList() {
        ArrayList<Integer> output = new ArrayList<>(0);
        while (scan.hasNext()) {
            String content = scan.next();
            output.add(Integer.parseInt(content));
        }
        return output;
    }

    public void closeFile() {
        scan.close();
    }
    
    
// *********************************************************************************************************
    private Formatter format;

    public void open_File(String fileName) throws FileNotFoundException {
        format = new Formatter(fileName);
    }


    public void writeArrayList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            format.format("%s%s", list.get(i).toString(), " ");
        }
    }

    public void close_File() {
        format.close();
    }
}
