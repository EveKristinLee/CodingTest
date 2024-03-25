//BOJ B3 2525 오븐 시계

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());
        a *= 60;
        int total = a + b + c;
        int hour = total / 60;
        int min = total % 60;
        hour %= 24;
        System.out.println(hour + " " + min);
    }
}
