
import java.util.Scanner;
import java.util.Vector;

public class LZW {
    static public String Compress(String a) {
        a+=" ";
        Vector <String> dict = new Vector <>();
        Vector <Integer> code = new Vector <>();
        for (int i = 0; i < 26 * 2; i++)
            if (i < 26) dict.add(Character.toString((char) (65 + i)));
            else  dict.add(Character.toString((char) (97 + i - 26)));
        for (int i = 0; i < a.length(); ) {
            int idx = 0;
            int next = i + 1;
            String temp = String.valueOf(a.charAt(i));
            while (true) {
                if (dict.contains(temp)) {
                    idx = dict.indexOf(temp);
                    temp += a.charAt(next);
                    next++;
                } else {
                    code.add(idx);
                    i += temp.length() - 1;
                    temp = "";
                    break;
                }
                if (next == a.length()) {
                    code.add(idx);
                    dict.add(temp);
                    i++;
                    break;
                }
            }
            if (next == a.length()) break;
        }
        String res = "";
        for (Integer integer : code) {
            var x = integer.toString();
            while (x.length() < 3) {
                x = '0' + x;
            }
            res += x;
        }
        return res;
    }

    static public String Decompress(String a) {
        Vector <String> dict = new Vector <>();
        String res="";

        for (int i = 0; i < 26 * 2; i++) {
            if (i < 26)
                dict.add(Character.toString((char) (65 + i)));
            else
                dict.add(Character.toString((char) (97 + i - 26)));
        }
        String prev = dict.get(Integer.parseInt(a.substring(0,3)));
        res+=prev;
        for (int i = 3; i < a.length();i+=3 ) {
            int idx = Integer.parseInt(a.substring(i,i+3));
                dict.add(prev);
                String current = dict.get(idx);
                prev+=current.charAt(0);
                dict.removeElementAt(dict.size()-1);
                dict.add(prev);
                prev = current;
                res+=dict.elementAt(idx);
                dict.add(res);

        }

        return res;
    }

    public static void main(String[] args) {
        String a ;
        System.out.println((a=Compress(new Scanner(System.in).nextLine())));
        System.out.println(Decompress(a));

    }
}


//"abaababbaabaabaaaababbbbbbbb".toUpperCase()
//"abaababbaabaabaaaababbbbbbbb"
//System.out.println(Collections.max(code));
