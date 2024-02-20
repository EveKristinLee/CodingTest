package solving.solve_1028;
//BOJ G4 13422 도둑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13422 {
    static int N; //집의 개수
    static int M; //연속된 집의 개수
    static int K; //방범 장치가 작동하는 돈의 양
    static int[] house;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            house = new int[N+M-1];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                house[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=N; i<N+M-1; i++) {
                house[i] = house[i - N];
            }

            res = 0;
            int sum = 0;
            for(int i=0; i<N+M-1; i++) {
                sum += house[i];
                if(i >= M-1) {
                    if(sum < K) {
                        res++;
                    }
                    sum -= house[i-(M-1)];
                }

                //N == M이 같을경우 한번만 확인
                if(N==M && i == N-1) {
                    break;
                }
            }
            System.out.println(res);
        }
    }
}
