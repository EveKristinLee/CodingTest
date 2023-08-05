//BOJ B1
package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pen_Pineapple_Apple_Pen_15881 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String p = "pPAp";

        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);

        int res = 0;
        while(matcher.find()) {
            res++;
        }
        System.out.println(res);
    }
}
