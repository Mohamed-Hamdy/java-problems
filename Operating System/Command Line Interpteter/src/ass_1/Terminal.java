/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import static java.nio.file.Files.list;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author AA
 */
class Terminal {

    private final ArrayList<String> allCommands = new ArrayList<>();
    private final ArrayList<String> description = new ArrayList<>();
    private final ArrayList<String> argNeeded = new ArrayList<>();
    public static String Path = "Path";
    public static String current = System.getProperty("user.dir");

    public static String New_Path;
    // public static Scanner Inpt = new Scanner(System.in);

    Terminal() {
        Commands();
    }

    static String check(String x) {
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ':') {
                return x;
            }
        }
        x = current + "/" + x;
        return x;
    }

    static String pwd() {
        String My_Path;
        if ("Path".equals(Path)) {
            My_Path = System.getProperty("user.dir");
        } else {
            My_Path = New_Path;
        }
        return My_Path;
    }

    public void ls() {
        File File = new File(pwd());
        File[] LOF = File.listFiles();
        for (File L_of_F : LOF) {
            if (L_of_F.isDirectory()) {
                System.out.println("Folder >> " + L_of_F.getName());
            } else if (L_of_F.isFile()) {
                System.out.println("File >> " + L_of_F.getName());
            }
        }
    }

    public void cat(String PAthOFFile) throws Exception {

        FileReader File = new FileReader(check(PAthOFFile));
        int Data;
        while ((Data = File.read()) != -1) {
            System.out.print((char) Data);
        }
        System.out.println();
    }

    public void more(String PAthOFFile) throws FileNotFoundException, IOException {
        String My_File;
        FileReader File = new FileReader(check(PAthOFFile));
        int Data;
        String Temp = "";
        while ((Data = File.read()) != -1) {
            Temp += (char) Data;
        }
        if ("".equals(Temp)) {
            System.out.println("File Empty");
        } else {
            System.out.print(Temp.substring((0), (Temp.length() / 2)));
            String Continue;
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nIF You Want To Continue Data File press + ");
            String Inpt_1 = scanner.next();
            Continue = Inpt_1;
            switch (Continue) {
                case "+":
                    System.out.println(Temp.substring((Temp.length() / 2), (Temp.length())));
                    break;
                default:
                    System.out.println("Wrong");
                    break;
            }
        }
    }

    public void cp(String PAthOFFile, String PAthOFFolder) throws Exception {
        String NameofFile1, NameofFile2;

        Path source = Paths.get(check(PAthOFFile));
        Path target = Paths.get(check(PAthOFFolder));
        try {

            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
        }
        System.out.println("The File Cereted and Data is Copy in New File.");
    }

    public void append_operator(String PAthOFFile1, String PAthOFFile2) throws Exception { // >> 
        ArrayList<String> list = new ArrayList<>();
        // Read File 1 in list
        Path out = Paths.get(PAthOFFile1);
        try (Scanner s = new Scanner(new File(PAthOFFile1))) {
            while (s.hasNext()) {
                list.add(s.next());
            }
        }
        // Append list in File 2 
        Writer output;
        output = new BufferedWriter(new FileWriter(PAthOFFile1, true));
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
            output.append("\n");

        }
        output.close();
    }

    public void copy_operator(String PAthOFFile1, String PAthOFFile2) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        // Read File 1 in list
        Path out = Paths.get(PAthOFFile2);
        try (Scanner s = new Scanner(new File(PAthOFFile1))) {
            while (s.hasNext()) {
                list.add(s.next());
            }
        }
        // Copy list in File 2 
        Writer output;
        output = new BufferedWriter(new FileWriter(PAthOFFile2));
        for (int i = 0; i < list.size(); i++) {
            output.write(list.get(i));
            output.append("\n");
        }
        output.close();
    }

    public void Create_file_operator(String PAthOFFile1) throws Exception {
        try {
            File file = new File(PAthOFFile1);
            boolean check = file.createNewFile();
            if (check) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }
        } catch (IOException e) {
        }
    }

    public void mv(String PAthOFFile, String PAthOFFolder) throws Exception {

        Path source = Paths.get(check(PAthOFFile));
        Path target = Paths.get(check(PAthOFFolder));
        try {
            Files.move(source, target,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        }
        System.out.println("The File is Moved .");
    }

    public void rm(String PAthOFFile) {
        String NameOfFile;
        Path source = Paths.get(check(PAthOFFile));
        try {
            Files.deleteIfExists(source);
        } catch (IOException e) {
        }

        System.out.println("The File iS Deleted .");
    }

    public void mkdir(String PAthOFFile) {
        File My_File = new File(check(PAthOFFile));
        My_File.mkdir();
        System.out.println("The Folder is Created .");
    }

    public void rmdir(String PAthOFFile) {
        File My_File = new File(check(PAthOFFile));
        My_File.delete();
        System.out.println("The Folder is Deleted .");

    }

    void date() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }
        //System.out.println("Done .");
    }

    String cd(String Filename) {
        Path = Filename;
        File File = new File(Path);
        if (File.exists()) {
            New_Path = File.getPath();
        } else {
            New_Path = "Not Found";
        }
        return New_Path;
    }

    String cd_1() {
        Path = "Path";
        return Path;
    }

    public void help() {
        for (int i = 0; i < allCommands.size(); i++) {
            System.out.println(allCommands.get(i) + description.get(i));
        }
    }

    public void args(String s) {

        for (int i = 0; i < allCommands.size(); i++) {

            if (s.equals(allCommands.get(i))) {
                System.out.println(allCommands.get(i) + " : " + argNeeded.get(i));
            }

        }
    }

    private void Commands() {
        allCommands.add("cd");
        description.add(" : Change Directory");
        argNeeded.add("Takes Required Destination Path");

        allCommands.add("cp");
        description.add(" : Copy A file");
        argNeeded.add("Takes Source File Path & Destination File Path");

        allCommands.add("ls");
        description.add(" : List all Files/Folders in current directory");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("rm");
        description.add(" : Remove a File");
        argNeeded.add("Takes the Required File Path");

        allCommands.add("mv");
        description.add(" : Move a File/Folder");
        argNeeded.add("Takes Source File Path & Destination File Path");

        allCommands.add("cat");
        description.add(" : Print Content of 1 or more files to the terminal or to another file. \">\" to override the file and \">>\" to append to file");
        argNeeded.add("cat file1 file2,fileN or file1,file2,fileN > fileX or file1,file2,fileN >> fileX");

        allCommands.add("pwd");
        description.add(" : Print Working Directory");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("more");
        description.add(" : Print Content of a file with a scrollable manner");
        argNeeded.add("Path to file Required");

        allCommands.add("help");
        description.add(" : Print the description of all the commands");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("args");
        description.add(" : Print the required arguments for a specific command");
        argNeeded.add("Takes a specific Command");

        allCommands.add("date");
        description.add(" : Print Current Date/Time");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("exit");
        description.add(" : Terminate the process");
        argNeeded.add("Doesn't have Arguments");

        allCommands.add("mkdir");
        description.add(" : Make new Directory");
        argNeeded.add("Takes the name of the new Directory");

        allCommands.add("rmdir");
        description.add(" : Remove Directory");
        argNeeded.add("Takes the name of the Directory");

        allCommands.add("clear");
        description.add(" : Clear Terminal Screen");
        argNeeded.add("Doesn't have Arguments");
    }
}
