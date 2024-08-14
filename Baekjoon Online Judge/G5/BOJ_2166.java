package solving.solve_0914;
//BOJ G5 2166 다각형의 면적

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2166 {
    //가우스의 면적 공식
    /*
    * 삼각형의 꼭지점이 주어졌을 경우 삼각형의 면적
    * |x1y2 + x2y3 + x3y1 - x2y1 - x3y2 - x1y3| * 1/2
    *
    * 사각형의 꼭지점이 주어졌을 경우 사각형의 면적
    * |x1y2 + x2y3 + x3y4 + x4y1 - x2y1 - x3y2 - x4y3 - x1y4| * 1/2
    * */

    private static class Pos {
        double x;
        double y;

        public Pos(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pos[] pos = new Pos[N+1];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            pos[i] = new Pos(x, y);
        }

        double res = 0;
        for(int i=1; i<N; i++) {
            res += pos[i].x * pos[i+1].y;
        }
        res += pos[N].x * pos[1].y;

        for(int i=1; i<N; i++) {
            res -= pos[i+1].x * pos[i].y;
        }
        res -= pos[1].x * pos[N].y;
        res = Math.abs(res);
        res *= 0.5;
        res = Math.round(res * 100) / 100.0;
        System.out.printf("%.1f", res);
    }
}
