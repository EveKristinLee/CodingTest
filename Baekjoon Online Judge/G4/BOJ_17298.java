package solving.solve_1028;
//BOJ G4 17298 오큰수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298 {
    static int N;
    static int[] num;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        res = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<N; i++) {

            while(!stack.isEmpty() && num[stack.peek()] < num[i]) {
                res[stack.pop()] = num[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb);
    }
}
