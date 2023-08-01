//BOJ S4
package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수들의_합_2_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int res = 0;
        int start = 0;
        int end = 0;
        int sum = 0;
        while(true) {
            if(sum >= M) { //합이 M보다 크다면
                sum -= nums[start++];
            }
            else if(end==N){ //end가 끝에 도착 했다면
                break;
            }
            else { //합이 M보다 작다면
                sum += nums[end++];
            }

            if(sum == M) {
                res++;
            }
        }
        System.out.println(res);
    }
}
