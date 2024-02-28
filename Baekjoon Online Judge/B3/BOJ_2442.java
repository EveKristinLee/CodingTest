//BOJ B3 2442 별 찍기 - 5

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=1; i<=n; i++) {
            for(int j=i; j<n; j++) {
                System.out.print(" ");
            }
            for(int j=0; j<i*2-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
