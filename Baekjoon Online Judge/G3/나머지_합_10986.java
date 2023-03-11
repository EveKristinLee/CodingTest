package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G3
//(S[j] - S[i-1]) % M = 0   => 구간합(i, j)를 M으로 나눈 나머지가 0인 값 확인
//(S[j]%M - S[i-1]%M) = 0
//S[j]%M = S[i-1]%M
public class 나머지_합_10986 {
    static int N, M;
    static long[] nums;
    static long[] remain;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new long[N+1];
        remain = new long[M];
        res = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            nums[i] = (Integer.parseInt(st.nextToken()) + nums[i-1]) % M;
            if(nums[i] == 0) {
                res++;
            }
            remain[(int) nums[i]]++;
        }

        for(int i=0; i<M; i++) {
            if(remain[i] > 1){
                res += (remain[i] * (remain[i]-1)) / 2;
            }
        }
        System.out.println(res);
    }
}
