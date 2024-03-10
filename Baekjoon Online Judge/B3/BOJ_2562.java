//BOJ B3 2562 최댓값

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxNum = Integer.MIN_VALUE;
        int idx = -1;
        for (int i=0; i<9; i++) {
            int num = Integer.parseInt(br.readLine());
            if(maxNum < num) {
                maxNum = num;
                idx = i+1;
            }
        }

        System.out.println(maxNum);
        System.out.println(idx);
    }
}
