package solving;
//BOJ S1 1991 트리 순회

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1991 {
    static int N; //이진트리 노드의 개수
    static char[] tree;
    static Map<Character, Integer> idx;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int len = (int)Math.pow(2, N);
        tree = new char[len+2];
        Arrays.fill(tree, '.');
        idx = new HashMap<>();
        sb = new StringBuilder();
        idx.put('A', 1);
        tree[1] = 'A';
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char now = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            int nowIdx = idx.get(now);
            if(left != '.') {
                tree[nowIdx * 2] = left;
                idx.put(left, nowIdx * 2);
            }
            if(right != '.') {
                tree[nowIdx * 2 + 1] = right;
                idx.put(right, nowIdx*2+1);
            }
        }

        preOrder(1);
        sb.append("\n");
        inOrder(1);
        sb.append("\n");
        postOrder(1);
        sb.append("\n");
        System.out.println(sb);
    }

    private static void preOrder(int now) {
        if(tree[now] == '.') {
            return;
        }
        sb.append(tree[now]);
        preOrder(now*2);
        preOrder(now*2+1);
    }

    private static void inOrder(int now) {
        if(tree[now] == '.') {
            return;
        }
        inOrder(now*2);
        sb.append(tree[now]);
        inOrder(now*2+1);
    }

    private static void postOrder(int now) {
        if(tree[now] == '.') {
            return;
        }
        postOrder(now*2);
        postOrder(now*2+1);
        sb.append(tree[now]);
    }
}
