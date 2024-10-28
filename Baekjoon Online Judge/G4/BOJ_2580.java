package solving.solve_1028;
//BOJ G4 2580 스도쿠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2580 {
    static int[][] map;
    static final int N = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        make(0, 0);
    }

    private static void make(int x, int y) {
        if(y == 9) { //행이 다 찼으면 다음 열의 시작으로
            make(x+1, 0);
            return;
        }

        if(x == 9) { //끝까지 다 찼으면 출력 후 종료
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            System.exit(0);
        }

        if(map[x][y] == 0) {
            for(int i=1; i<=9; i++) {
                if(isFitNum(x, y, i)) {
                    map[x][y] = i;
                    make(x, y+1);
                    map[x][y] = 0;
                }
            }
        }
        else {
            make(x, y+1);
        }
    }

    private static boolean isFitNum(int x, int y, int num) {
        //가로
        for(int i=0; i<N; i++) {
            if(map[x][i] == num) {
                return false;
            }
        }

        //세로
        for(int i=0; i<N; i++) {
            if(map[i][y] == num) {
                return false;
            }
        }

        //정사각형
        //시작점 구하기
        int nx = (x / 3) * 3;
        int ny = (y / 3) * 3;
        for(int i = nx; i<(nx+3); i++) {
            for(int j=ny; j<(ny+3); j++) {
                if(map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
