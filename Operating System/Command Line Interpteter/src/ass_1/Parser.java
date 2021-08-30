/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author AA
 */
public class Parser extends Terminal {

    public ArrayList<String> args = new ArrayList<>();
    public String cmd;
    Terminal terminal = new Terminal();

    public String getcmd() {
        return cmd;
    }

    public ArrayList<String> getargs() {
        return args;
    }

    public Boolean parse(String command) {

//        String[] words = command.split("\\s+");
//        //ArrayList<String> CLI = new ArrayList<>();
//
//        for (int i = 0; i < words.length; i++) {
//            words[i] = words[i].trim();
//            if (words[i].length() > 0) {
//                args.add(words[i]);
//            }
//        }
//        System.out.println("The Array list is :");
//        for (int w = 0; w < args.size(); w++) {
//            System.out.println(args.get(w));
//        }
        args = new ArrayList<>(Arrays.asList(command.split("\\s+")));


//            System.out.println("The array list is :");
//            for (int w = 0; w < args.size(); w++) {
//                System.out.println(args.get(w));
//            }
        
        //System.out.println("=================================================");

        if (args.isEmpty()) {
            return false;
        }

        //File file;
        switch (args.get(0)) {
            case "cd":
                if (args.size() > 2) {
                    System.out.println("Invalid Arguments cd takes 0 or 1 Arguments, Write help for more details");
                    return false;
                }
                cmd = "cd";
                break;
            case "cd:":
                if (args.size() > 2) {
                    System.out.println("Invalid Arguments cd takes 0 or 1 Arguments, Write help for more details");
                    return false;
                }
                cmd = "cd:";
                break;

            //======================================================================================================
            case "cp":
                if (args.size() != 3) {
                    System.out.println("Invalid Arguments cp takes 2 Arguments, Write help for more details");
                    return false;
                }
                //terminal.cp(CLI.get(1),CLI.get(2));
                cmd = "cp";
                break;
            //======================================================================================================
            case "mv":
                if (args.size() != 3) {
                    System.out.println("Invalid Arguments mv takes 2 Arguments, Write help for more details");
                    return false;
                }
                //terminal.mv(CLI.get(1),CLI.get(2));
                cmd = "mv";
                break;
            //======================================================================================================
            case "rm":
                if (args.size() != 2) {
                    System.out.println("Invalid Arguments rm takes 1 Argument, Write help for more details");
                    return false;
                }
                //terminal.rm(CLI.get(1));
                cmd = "rm";
                break;
            //======================================================================================================
            case "mkdir":
                if (args.size() != 2) {
                    System.out.println("Invalid Arguments mkdir takes 1 Argument, Write help for more details");
                    return false;
                }
                //terminal.mkdir(CLI.get(1));
                cmd = "mkdir";
                break;
            //======================================================================================================
            case "rmdir":
                if (args.size() != 2) {
                    System.out.println("Invalid Arguments rmdir takes 1 Argument, Write help for more details");
                    return false;
                }
                //String path = terminal.pwd() + "\\" + CLI.get(1);
                //terminal.rmdir(path);
                cmd = "rmdir";
                break;
            //======================================================================================================
            case "pwd":
                if (args.size() > 1) {
                    System.out.println("Invalid Arguments pwd takes no Arguments, Write help for more details");
                    return false;
                }
                //terminal.pwd();
                //return true;
                cmd = "pwd";
                break;
            //======================================================================================================
            case "clear":
                if (args.size() > 1) {
                    System.out.println("Invalid Arguments clear takes no Arguments, Write help for more details");
                    return false;
                }
                //terminal.clear();
                cmd = "clear";
                break;
            //======================================================================================================
            case "ls":
                if (args.size() > 1) {
                    System.out.println("Invalid Arguments ls takes no Arguments, Write help for more details");
                    return false;
                }
                // System.out.println("Hello From ls Parser");
                // terminal.ls();
                cmd = "ls";
                break;
            //======================================================================================================
            case "|":
                if (args.size() < 2) {
                    System.out.println("Invalid Arguments ls takes no Arguments, Write help for more details");
                    return false;
                }
                // terminal.ls();
                cmd = "|";
                break;
            //======================================================================================================    
            case "help":
                if (args.size() > 1) {
                    System.out.println("Invalid Arguments help takes no Arguments");
                    return false;
                }
                // terminal.help();
                cmd = "help";
                //System.out.println("xxxxxxxxxx");
                break;
            //======================================================================================================
            case "date":
                if (args.size() > 1) {
                    System.out.println("Invalid Arguments help takes no Arguments, Write help for more details");
                    return false;
                }
                //terminal.date();
                cmd = "date";
                break;
            //======================================================================================================
            case "args":
                if (args.size() != 2) {
                    System.out.println("Invalid Arguments args takes 1 Argument, Write help for more details");
                    return false;
                }
                cmd = "args";
                //System.out.println(terminal.args(CLI.get(1)));
                break;
            //======================================================================================================
            case "more":
                if (args.size() != 2) {
                    System.out.println("Invalid Arguments more takes 1 Argument, Write help for more details");
                    return false;
                }
                cmd = "more";
                //terminal.more(CLI.get(1));
                break;
            //======================================================================================================
            case "cat":
                if (args.size() < 2) {
                    System.out.println("Invalid Arguments cat takes at least 1 Argument, Write help for more details");
                    return false;
                }
                cmd = "cat";
                break;

            //======================================================================================================
            case "exit":
                System.exit(0);
                break;
            //======================================================================================================

        }
//        for(int k = 0 ; k < args.size(); k++){
//            args.remove(k);
//        }
        return true;
    }
}
