//BOJ G3 소수의 연속합
package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//아리스토테네스의 체 => 소수 찾기
//투포인터로 경우의 수 찾기
public class BOJ_1644 {
    static int N;
    static boolean[] isPrime; //소수면 false, 아니면 true
    static List<Integer> nums;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        findPrime();
        res = 0;

        int front = 0;
        int end = 0;
        int sum = 0;
        int len = nums.size();
        while (true) {
            if(sum >= N) {
                sum -= nums.get(front++);
            }
            else if(end == len) {
                break;
            }
            else {
                sum += nums.get(end++);
            }

            if(sum == N) {
                res++;
            }
        }
        System.out.println(res);
    }

    private static void findPrime() {
        isPrime = new boolean[N+1];
        nums = new ArrayList<>();
        isPrime[0] = true;
        isPrime[1] = true;

        for(int i=2; i<=Math.sqrt(N); i++) {
            if(!isPrime[i]) {
                for(int j=i*i; j<=N; j+=i) {
                    isPrime[j] = true;
                }
            }
        }

        for(int i=2; i<=N; i++) {
            if(!isPrime[i]) {
                nums.add(i);
            }
        }
    }
}