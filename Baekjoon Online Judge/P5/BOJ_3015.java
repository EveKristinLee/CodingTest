//BOJ P5 오아시스 재결합
package solving.solve_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_3015 {
    public static class Pair {
        int num; //height
        int cnt; //같은 숫자 개수

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int N;
    static long res;
    static Stack<Pair> st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        res = 0;

        st = new Stack<>();
        for(int i=0; i<N; i++) {
            int now = Integer.parseInt(br.readLine());
            Pair p = new Pair(now, 1);
            while(!st.isEmpty() && st.peek().num <= now) {
                Pair top = st.pop();
                res += top.cnt;
                if(top.num == now) { //같은 애들 개수 갱신
                    p.cnt += top.cnt;
                }
            }

            if(!st.isEmpty()) { //양 옆은 무조건 볼 수 있음
                res++;
            }
            st.push(p);
        }

        System.out.println(res);
    }
}
