package solving.solve_0914;
//BOJ G5 23284 모든 스택 수열

import java.util.Scanner;

public class BOJ_23284 {
    static int N;
    static StringBuilder sb;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sb = new StringBuilder();
        visit = new boolean[N+1];
        arr = new int[N+1];

        makeArr(1, 0);
        System.out.println(sb);
    }

    private static void makeArr(int cnt, int next) {
        if(cnt == N+1) {
            //완성
            for(int i=1; i<=N; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=N; i++) {
            if(visit[i]) {
                continue;
            }
            //바로 직전 수보다 크고, 다음 들어올 수보다 작으면 잘못된 수열
            //ex) 1 2 5 3 4 , next = 6
            //3보다 4가 크고, 6보다 4가 작아서 성립하지 않는 수열
            //3보다 4가 먼저 pop 되어야 함
            if(arr[cnt-1] < i && i < next) {
                break;
            }

            arr[cnt] = i;
            visit[i] = true;
            if(i >= next) {
                //새로운 수라면 next에 +1
                makeArr(cnt+1, i+1);
            }
            else {
                //이미 들어온 수를 꺼낸것이라면 next유지
                makeArr(cnt+1, next);
            }
            visit[i] = false;
        }
    }
}
