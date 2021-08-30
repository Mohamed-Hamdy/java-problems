/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass_5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class StandardHuffman {

    static Hashtable<Character, String> data = new Hashtable<Character, String>();

    public static class Node {

        int number;
        double prob;
        String code = "";
        char c;
        String str;
        boolean other;
        Node right;
        Node left;

        public Node(int n, char ch) {
            number = n;
            c = ch;
        }

        public Node(double n, String str) {
            prob = n;
            this.str = str;
        }

        public Node() {
            c = '-';
        }

        public static boolean contains(String str, Vector<Node> list) {
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    if (str.equals(list.get(i).str)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String toString() {
            return String.format("%s: %.3f : %s", str, prob, code);
        }
        public static class MyComparator2 implements Comparator<Node> {

        public int compare(Node x, Node y) {
            return Double.compare(y.prob, x.prob);
        }
    }
    }

    

    public static void compress(List<Node> l) {

        int size = l.size();
        for (int i = size - 1; i >= 0; i--) {
            if (i == 0) {
                break;
            }
            l.add(Create_parent(l.get(i), l.get(i - 1)));
            Collections.sort(l, new Node.MyComparator2());
        }
    }
    public static Node Create_parent(Node Node1, Node Node2) {
        Node node = new Node();
        node.left = Node1;
        node.right = Node2;
        node.prob = Node1.prob + Node2.prob;
        node.str = Node1.str + Node2.str;
        return node;
    }

    public static void Huffman_SetCode(Node root) {
        if (root.right != null) {
            root.right.code = root.code + "1";
            Huffman_SetCode(root.right);
        }
        if (root.left != null) {
            root.left.code = root.code + "0";
            Huffman_SetCode(root.left);

        }

    }

    public static void print_tree(Node root) throws IOException {
        String str;
        if (root.right == null && root.left == null) {

            FileWriter ob = new FileWriter("Tree.txt", true);
            str = root.str + ": " + root.code + "\n";
            ob.write(str);
            ASS_5.codes.put(root.str, root.code);
            ob.close();
            return;
        }
        print_tree(root.right);
        print_tree(root.left);

    }
}

 