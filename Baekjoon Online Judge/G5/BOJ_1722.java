package solving.solve_0914;
//BOJ G5 1722 순열의 순서

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1722 {
    static int N;
    static int[] nums;
    static long order;
    static long resOrder; //순열 순서
    static int[] resNum; //순열
    static long[] fac;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        fac = new long[21];
        fac[0] = 1;
        for (int i=1; i<=20; i++) {
            fac[i] = fac[i-1] * i;
        }

        visit = new boolean[N+1];
        if(k == 1) {
            resNum = new int[N+1];
            order = Long.parseLong(st.nextToken());

            for(int i=1; i<=N; i++) { //순열의 위치
                for(int j=1; j<=N; j++) { //순열에 들어갈 숫자
                    if(visit[j]) {
                        continue;
                    }

                    if(fac[N-i] < order) {
                        order -= fac[N-i];
                    }
                    else{
                        visit[j] = true;
                        resNum[i] = j;
                        break;
                    }
                }
            }
            for(int i=1; i<=N; i++) {
                System.out.print(resNum[i] + " ");
            }
            System.out.println();
        }
        else {
            nums = new int[N+1];
            for(int i=1; i<=N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            resOrder = 1;
            for(int i=1; i<=N; i++) {
                for(int j=1; j<nums[i]; j++) {
                    if(!visit[j]) {
                        resOrder += fac[N-i];
                    }
                }
                visit[nums[i]] = true;
            }
            System.out.println(resOrder);
        }
    }
}
