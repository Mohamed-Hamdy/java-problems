/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_2;

import javax.swing.JOptionPane;

/**
 *
 * @author AA
 */
public class Compress {

    static TreeNode root;
    static int maxswapnumber = -1;

    public String compress_function(String data) {

        String result = "";

        root = new TreeNode();
        root.rootInit();

        TreeNode NYT = root;
        TreeNode search;

        char target;

        for (int i = 0; i < data.length(); i++) {

            target = data.charAt(i);
            search = Search_by_value(root, target);
            System.out.println("Binary Code of " + data.charAt(i) + " is " + String.format("%07d", Integer.parseInt(Integer
                    .toBinaryString(target))));
            if (search == null) {
                System.out.println("YES " + NYT.code + "*"); // NYT == Root .
                System.out.println("NYT.code " + NYT.code);
                result += NYT.code;
                result += "--";
                result += String.format("%07d", Integer.parseInt(Integer.toBinaryString(target)));
                result += "--";

                updateTree(NYT, true, target);
                NYT = NYT.left;
            } else {
                System.out.println("Char " + data.charAt(i) + " ||| Short Code in Tree = " + search.code);

                result += search.code;
                result += "--";
                updateTree(search, false, target);
            }
            printTree(root, "");
            System.out.println();
            System.out.println("result " + result);
            System.out.println();

        }
        printTree(root, "");
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // check if value or element in tree or not 
    static TreeNode Search_by_value(TreeNode ro, char target) {

        TreeNode tem1 = null, tem2 = null;
        if (ro.isPearent) {
            if (ro.left != null) {
                tem1 = Search_by_value(ro.left, target);
            }
            if (ro.right != null) {
                tem2 = Search_by_value(ro.right, target);
            }
        } else {
            if (ro.value == target) {
                return ro;
            }
            return null;
        }

        // in case ro not found in tree in left side or right side in tree .
        if (tem1 == null && tem2 == null) {
            return null;
        } else if (tem1 != null) {  // found it in left side 
            return tem1;
        } else { // found it in right side 
            return tem2;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    // do split for tree and increment counter and call updata swap function
    public static void updateTree(TreeNode ro, boolean status, char input_char) {

        System.out.println("Update Tree Now .");
        if (status) {
            // not found input_char in tree 
            System.out.println("case One .");

            ro.increment();
            ro.split(input_char);

            if (!ro.isRoot()) {
                //System.out.println("parent " + NYT.pearent + " " + newValue);
                ro = ro.pearent;
                updateSwap(ro);
            }

        } else { // found input_char in tree (repeated input char) .
            System.out.println("case Two .");
            updateSwap(ro);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // get best node i will swap with it (best node --> get value and loc and num_of_this_value and counter )
    static void updateSwap(TreeNode ro) {

        while (!ro.isRoot()) {
            TreeNode temp = bestNode(root, ro.number, ro.counter);
            if (temp != null) {

                System.out.println("I will swap : ( best Node ) " + temp.number + " " + temp.value + " " + temp.counter + " with "
                        + ro.number + " " + ro.counter + " " + ro.value);

                TreeNode.Swap(ro, temp); // swap branthes 

                increase_short_code_digit(root, ""); // updata short code in last step 
                //increase_short_code_digit(temp, temp.code); // updata short code after swap

                ro = temp;

            }

            ro.increment();
            ro = ro.pearent;
        }

        ro.increment();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    static TreeNode bestNode(TreeNode ro, int num, int counter) {

        maxswapnumber = -1;
        Get_max_number_function(root, num, counter);

//        if (maxswapnumber == -1) {
//            return null;
//        } else {
        if (maxswapnumber != -1) {
            return Search_by_number(root, maxswapnumber);
        }
        return null;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    static void increase_short_code_digit(TreeNode x, String code) {
        x.code = code;
        if (x.right != null) {
            increase_short_code_digit(x.right, code + "1");
        }
        if (x.left != null) {
            increase_short_code_digit(x.left, code + "0");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // help best node function (search about best or high node in tree to swap with it )
    // to now max number if i have option of swap of high level (i will swap will with high level and get number of this level)  
    static void Get_max_number_function(TreeNode ro, int num, int counter) {
        // two condition if node is not pearent && numbers not equal 
        if (ro == null) {
            return;
        }

        if (counter >= ro.counter && ro.number > num) {
            boolean flag = true;
//            if (ro.isPearent && ro.left.number == num && ro.right.number == num) {
//                flag = false;
//            }
            if (flag) {
                maxswapnumber = Math.max(maxswapnumber, ro.number);
            }
        }

        Get_max_number_function(ro.left, num, counter);
        Get_max_number_function(ro.right, num, counter);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // help best node function (search about best or high node in tree to swap with it by maxswapnumber (it return from rec function) )
    static TreeNode Search_by_number(TreeNode ro, int target) {
        TreeNode tem1 = null, tem2 = null;
        if (ro.isPearent) {
            if (ro.number == target) {
                return ro;
            }
            if (ro.left != null) {
                tem1 = Search_by_number(ro.left, target);
            }
            if (ro.right != null) {
                tem2 = Search_by_number(ro.right, target);
            }
        } else {
            if (ro.number == target) {
                return ro;
            }

            return null;
        }

        if (tem1 == null && tem2 == null) {
            return null;
        } else if (tem1 != null) {
            return tem1;
        } else {
            return tem2;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    static void printTree(TreeNode ro, String space) {
        System.out.println(space + ro.print_Tree_details()
                + (ro.isRoot() ? " Root " : " "));

        if (ro.right != null) {
            printTree(ro.right, space + "\t");
        }

        if (ro.left != null) {
            printTree(ro.left, space + "\t");
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
}
