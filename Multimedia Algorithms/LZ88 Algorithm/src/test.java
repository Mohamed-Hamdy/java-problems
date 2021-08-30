import java.io.*;
import java.util.Vector;

public class test{
    public static void main(String[] args) throws IOException {
        var a = "0101010010000000001";
        var x = Integer.parseInt(a,2);
        var a2 = "01010100100001010110010000000001";
        var x2 = Integer.parseInt(a2,2);

        System.out.println(x);
        System.out.println(x2);

        for (char c= 'a';c<='z';c++){
                var n = (int)c;
            System.out.println(Integer.toBinaryString(n));
            }
            var ccc = new int[]{1,2,3};
        Vector<String> v = new Vector<>();
        x2 = Math.min(x2,x );
        System.out.println();
    }

}
