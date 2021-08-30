/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_2;

import static ass_2.Compress.root;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author AA
 */
public class DeCompress {

    static TreeNode root, object;
    static int maxswapnumber = -1;

    public String deCompress_function(String data) {
        System.out.println("\n\nDeCompression Function\n");
        String result = "", temp = "";
        root = new TreeNode();
        root.rootInit();
        TreeNode NYT = root;
        int Search_result;

        char newValue1 = toChar(data.substring(0, 7));
        result += newValue1;
        updateTree(NYT, true, newValue1);
        NYT = NYT.left;

        for (int i = 7; i < data.length(); i++) {
            temp += data.charAt(i);

            Search_result = treeSearch(root, temp, 0);
            //System.out.println("search result is :" + Search_result);
            // If it is “NYT” then next bits are Short Codes of Symbol
            if (Search_result == TreeNode.NODE_IS_NYT) {
                char newValue = toChar(data.substring(i, i + 8));
                result += newValue;
                i += 7;
                updateTree(NYT, true, newValue);
                NYT = NYT.left;
                temp = "";
            } else if (Search_result != TreeNode.NODE_IS_PARENT) { // this char is repeated in tree . 
                char newValue = (char) Search_result;
                updateTree(object, false, newValue);
                result += newValue;
                temp = "";
            }
        }
        printTree(root, "");

        return result;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    static char toChar(String temp) {
        int ch = 0, len = temp.length() - 1;
        for (int i = 0; i < len + 1; i++) {
            ch += (temp.charAt(i) == '1' ? 1 : 0) * Math.pow(2, len - i);
        }
        //System.out.println((char) ch);
        return (char) ch;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

     static int treeSearch(TreeNode ro, String code, int lev) {

        if (code.equals("")) {
            return TreeNode.NODE_IS_NYT;
        }
        // System.out.println(code.equals(""));
        if (code.equals(ro.code)) {
            if (ro.isPearent) {
                return TreeNode.NODE_IS_PARENT;
            } else if (ro.value == '\1') {
                return TreeNode.NODE_IS_NYT;
            } else {
                object = ro;
                return ro.value;
            }

        } else if (ro.left != null
                && code.charAt(lev) == ro.left.code.charAt(lev)) {
            return treeSearch(ro.left, code, lev + 1);
        } else if (ro.right != null
                && code.charAt(lev) == ro.right.code.charAt(lev)) {
            return treeSearch(ro.right, code, lev + 1);
        }

        return TreeNode.NODE_IS_PARENT;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // do split for tree and increment counter and call updata swap function
    public static void updateTree(TreeNode ro, boolean status, char input_char) {

        if (status) {
            // not found input_char in tree 

            ro.increment();
            ro.split(input_char);

            if (!ro.isRoot()) {
                ro = ro.pearent;
                updateSwap(ro);
            }

        } else { // found input_char in tree (repeated input char) .
            updateSwap(ro);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // get best node i will swap with it (best node --> get value and loc and num_of_this_value and counter )
    static void updateSwap(TreeNode ro) {

        while (!ro.isRoot()) {
            TreeNode temp = bestNode(root, ro.number, ro.counter);

            if (temp != null) {

                TreeNode.Swap(ro, temp); // swap branthes 

                increase_short_code_digit(root, ""); // updata short code in last step 
//                increase_short_code_digit(temp, temp.code); // updata short code after swap

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

            // return null;
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
}
