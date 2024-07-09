package solving.solve_1028;
//BOJ G4 9019 DSLR

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019 {
    private static class Turn {
        int num;
        String op;

        public Turn(int num, String op) {
            this.num = num;
            this.op = op;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            String res = BFS(A, B);
            if(res != null) {
                sb.append(res).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String BFS(int A, int B) {
        Queue<Turn> q = new ArrayDeque<>();
        q.offer(new Turn(A, ""));
        boolean[] visit = new boolean[10_000];
        visit[A] = true;

        while(!q.isEmpty()) {
            Turn now = q.poll();
            if(now.num == B) {
                return now.op;
            }

            int D = (now.num * 2) % 10_000; //n*2를 10_000으로 나눈 나머지
            int S = (now.num == 0 ? 9999 : now.num-1); //n이 0이면 9999, 아니면 n-1
            //ex) 1234 -> 2341
            //1234 % 1000 => 234 * 10 => 2340
            //1234 / 1000 => 1 + 2340 => 2341
            int L = ((now.num % 1000) * 10) + (now.num / 1000);
            //ex) 1234 -> 4123
            //1234 % 10 => 4 * 1000 => 4000
            //1234 / 10 => 123 + 4000 => 4123
            int R = ((now.num % 10) * 1000) + (now.num / 10);

            if(!visit[D]) {
                visit[D] = true;
                q.offer(new Turn(D, now.op + "D"));
            }
            if(!visit[S]) {
                visit[S] = true;
                q.offer(new Turn(S, now.op + "S"));
            }
            if(!visit[L]) {
                visit[L] = true;
                q.offer(new Turn(L, now.op + "L"));
            }
            if(!visit[R]) {
                visit[R] = true;
                q.offer(new Turn(R, now.op + "R"));
            }
        }

        return null;
    }

}
