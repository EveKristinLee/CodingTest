//BOJ B2
package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 니모를_찾아서_10173 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        StringBuilder sb = new StringBuilder();
        while(!(s = br.readLine()).equals("EOI")) {
            if(s.toLowerCase().matches(".*(nemo).*")) {
                sb.append("Found\n");
            }
            else {
                sb.append("Missing\n");
            }
        }
        System.out.println(sb);
    }
}
