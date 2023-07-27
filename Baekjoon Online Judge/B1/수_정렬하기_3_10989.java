//BOJ B1
package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 수_정렬하기_3_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[10001];
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[num]++;
        }

        for(int i=1; i<=10000; i++) {
            while(nums[i] > 0) {
                sb.append(i).append("\n");
                nums[i]--;
            }
        }
        System.out.println(sb);
    }
}
