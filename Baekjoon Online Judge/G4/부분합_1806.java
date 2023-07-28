//BOJ G4
package solving.solve_1028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
//            if(i > 0){
//                nums[i] += nums[i-1];
//            }
        }
        int start = 0;
        int end = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        while(true) {
            if(sum >= S) {
                sum -= nums[start++];
            }
            else if(end == N) {
                break;
            }
            else {
                sum += nums[end++];
            }

            if(sum >= S) {
                res = Math.min(res, (end - start));
            }
        }
        if(res == Integer.MAX_VALUE) res = 0;
        System.out.println(res);
    }
}
