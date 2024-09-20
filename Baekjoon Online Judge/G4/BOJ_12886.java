package solving.solve_1028;
//BOJ G4 12886 돌 그룹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12886 {
    static boolean res;
    static int A;
    static int B;
    static int C;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visit = new boolean[1501][1501]; //두개가 정해지면 나머지 하나의 개수가 정해짐
        res = false;

        rock(A, B, C);
        if(res) {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }

    private static void rock(int a, int b, int c) {
        if(a == b && b == c) {
            res = true;
            return;
        }

        if(res) {
            return;
        }

        if(a != b) {
            if(a > b) {
                if(!visit[a-b][b+a]) {
                    visit[a-b][b+a] = true;
                    rock(a - b, b + b, c);
                }
            }
            if(b > a) {
                if(!visit[a+a][b-a]) {
                    visit[a+a][b-a] = true;
                    rock(a + a, b - a, c);
                }
            }
        }

        if(b != c) {
            if(c > b) {
                if(!visit[b+b][c-b]) {
                    visit[b + b][c - b] = true;
                    rock(a, b + b, c - b);
                }
            }
            if(b > c) {
                if(!visit[b-c][c+c]) {
                    visit[b-c][c+c] = true;
                    rock(a, b - c, c + c);
                }
            }
        }

        if(a != c) {
            if(a > c) {
                if(!visit[a-c][c+c]) {
                    visit[a-c][c+c] = true;
                    rock(a - c, b, c + c);
                }
            }
            if(c > a) {
                if(!visit[a+a][c-a]) {
                    visit[a+a][c-a] = true;
                    rock(a + a, b, c - a);
                }
            }
        }
    }
}
