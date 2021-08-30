package ass_2;

import javax.swing.JFileChooser;

/**
 *
 * @author AA
 */
public class TreeNode {

    public int NYT = 100;
    static final int NODE_IS_PARENT = -1;
    static final int NODE_IS_NYT = -2;

    public int number, counter;
    public boolean isPearent;
    public TreeNode left, right, pearent;
    public char value;
    public String code;

    TreeNode() {
        number = counter = 0;
        isPearent = false;
        left = right = pearent = null;
        code = "";
        value = '\1';
    }

//	TreeNode(TreeNode old) {
//		number = old.number;
//		counter = old.counter;
//		isPearent = old.isPearent;
//		left = old.left;
//		right = old.right;
//		pearent = old.pearent;
//		code = old.code;
//		value = old.value;
//	}
    public void rootInit() {
        isPearent = true;
        number = NYT;
        code = "";
        pearent = null;
    }

    public void increment() {
        counter++;
    }

    public boolean isRoot() {
        if (pearent == null) {
            return true;
        } else {
            return false;
        }
    }

    public TreeNode split(char newValue) {
        left = new TreeNode();
        right = new TreeNode();
        isPearent = true;

        right.code = code + "1";
        right.number = number - 1;
        right.pearent = this;
        right.value = newValue;
        right.increment();

        left.code = code + "0";
        left.number = number - 2;
        left.pearent = this;

        return left;
    }

    public String print_Tree_details() {
//		String temp = "num " + number + " " + " code " + code + " "+ " value " + value + " " + " counter "+ counter + " "
//				+ " ispearent "+isPearent;
        String temp = number + " " + code + " " + value + " " + counter + " " + isPearent;
        return temp;
    }

    public static void Swap(TreeNode x, TreeNode y) {

        // swap value 
        char tempValue = x.value;
        x.value = y.value;
        y.value = tempValue;

        // swap left sides in branth left  
        TreeNode templeft = x.left;
        x.left = y.left;
        y.left = templeft;

        // swap true and false in output  ( isPearent or not )
        boolean tempIsParent = x.isPearent;
        x.isPearent = y.isPearent;
        y.isPearent = tempIsParent;

        // swap right sides in branth right  
        TreeNode tempRight = x.right;
        x.right = y.right;
        y.right = tempRight;

        if (x.isPearent) {
            x.left.pearent = x;
            x.right.pearent = x;
        }

        if (y.isPearent) {
            y.left.pearent = y;
            y.right.pearent = y;
        }

    }
//
//	public boolean equal(TreeNode n) {
//		return number == n.number ? true : false;
//	}
}
