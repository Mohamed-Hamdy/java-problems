/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_1;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author AA
 */
public class ASS_1 {

    /**
     * @param args the command line arguments // * @throws java.lang.Exception
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Parser p = new Parser();
            Terminal T = new Terminal();
            System.out.println("Enter Your Command :");
            String command = scanner.nextLine();

            String[] word = command.split("\\|");
            for (int j = 0; j < word.length; j++) {
                if (p.parse(word[j])) {

                    if (p.getcmd().equals("pwd")) {
                        System.out.println(T.pwd());
                    } else if (p.getcmd().equals("date")) {
                        T.date();
                    } else if (p.getcmd().equals("help")) {
                        T.help();
                    } else if (p.getcmd().equals("clear")) {
                        T.clear();
                    } else if (p.getcmd().equals("cd") &&  p.args.size()>1) {
                        T.cd(p.args.get(1));
                    } else if (p.getcmd().equals("cd")) {
                        T.cd_1();
                    } else if (p.getcmd().equals("args")) {
                        T.args(p.args.get(1));
                    } else if (p.getcmd().equals("ls")) {
                        T.ls();
                    } else if (p.getcmd().equals("rm")) {
                        T.rm(p.args.get(1));
                    } else if (p.getcmd().equals("rm")) {
                        T.rm(p.args.get(1));
                    } else if (p.getcmd().equals("mv")) {
                        T.mv(p.args.get(1), p.args.get(2));
                    } else if (p.getcmd().equals("rmdir")) {
                        T.rmdir(p.args.get(1));
                    } else if (p.getcmd().equals("mkdir")) {
                        T.mkdir(p.args.get(1));
                    } else if (p.getcmd().equals("more")) {
                        T.more(p.args.get(1));
                    } else if (p.getcmd().equals("cp")) {
                        T.cp(p.args.get(1), p.args.get(2));
                    } else if (p.getcmd().equals("cat")) {
                        if (">".equals(p.args.get(1)) && p.args.size() == 3) { // cat //> filename  This will create file
                            T.Create_file_operator(p.args.get(2));
                        } else if (p.args.size() == 4) {
                            // cat filename1 > filename2  This will copy file1 content in file2 content not append.
                            if (">".equals(p.args.get(2))) {
                                T.copy_operator(p.args.get(1), p.args.get(3));
                            } else if (">>".equals(p.args.get(2))) {
                                // cat filename1 >> filename2  This will copy file1 content in file2 content append.
                                T.append_operator(p.args.get(1), p.args.get(3));
                            }
                        } else if (p.args.size() == 2) {
                            T.cat(p.args.get(1));
                        } else {
                            for (int i = 1; i < p.args.size(); i++) {
                                System.out.println("The File Content is : ");
                                T.cat(p.args.get(i));
                            }
                        }
                    }

                }
            }

        }
    }
}



// End // 
