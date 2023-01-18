package solve_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G4
public class 제기차기_23830 {
    static int N; //전교생 수
    static long[] score; //제기차기 점수
    static long p, q, r, S;
    static long res;
    //기준 정수 K
    //K+r 초과 => K+r-p
    //K미만 => K+q

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());
        r = Long.parseLong(st.nextToken());
        S = Long.parseLong(st.nextToken());

        long max = S;
        long min = 1;
        res = Long.MAX_VALUE;

        while(min <= max) {
            long mid = (min+max)/2;
            long tmp = chk(mid);
            if(S <= tmp) {
                res = Math.min(res, mid);
                max = mid-1;
            }
            else {
                min = mid+1;
            }
        }
        if(res == Long.MAX_VALUE) {
            System.out.println(-1);
        }
        else{
            System.out.println(res);
        }
    }

    private static long chk(long num) {
        long sum = 0;
        for(int i=0; i<N; i++) {
            if(score[i] > num+r) {
                sum += (score[i]-p);
            }
            else if(score[i] < num) {
                sum += (score[i]+q);
            }
            else {
                sum += score[i];
            }
        }
        return sum;
    }
}
