package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열_돌리기_3_16935 {
    static int N; //세로
    static int M; //가로
    static int R; //연산의 수
    static int[][] map;
    static int[] method;

    private static void one() {
        for(int i=0; i<M; i++) {
            int top = 0;
            int bottom = N-1;
            while(top < bottom) {
                int tmp = map[top][i];
                map[top][i] = map[bottom][i];
                map[bottom][i] = tmp;
                top++;
                bottom--;
            }
        }
    }
    private static void two() {
        for(int i=0; i<N; i++) {
            int left = 0;
            int right = M-1;
            while(left < right) {
                int tmp = map[i][left];
                map[i][left] = map[i][right];
                map[i][right] = tmp;
                left++;
                right--;
            }
        }
    }

    private static void three() {
        int[][] res = new int[M][N];
        int C = N-1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                res[j][C] = map[i][j];
            }
            C--;
        }
        int tmp = N;
        N = M;
        M = tmp;
        map = res;
    }

    private static void four() {
        int[][] res = new int[M][N];
        int Row = M-1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                res[Row-j][i] = map[i][j];
            }
        }
        int tmp = N;
        N = M;
        M = tmp;
        map = res;
    }

    private static void five() {
        int[][] res = new int[N][M];
        int nN = N/2;
        int nM = M/2;

        //1->2
        for(int i=0; i<nN; i++) {
            for(int j=0; j<nM; j++) {
                res[i][j+nM] = map[i][j];
            }
        }

        //2->3
        for(int i=0; i<nN; i++) {
            for(int j=nM; j<M; j++) {
                res[i+nN][j] = map[i][j];
            }
        }

        //3->4
        for(int i=nN; i<N; i++) {
            for(int j=nM; j<M; j++) {
                res[i][j-nM] = map[i][j];
            }
        }

        //4->1
        for(int i=nN; i<N; i++) {
            for(int j=0; j<nM; j++) {
                res[i-nN][j] = map[i][j];
            }
        }
        map = res;
    }

    private static void six() {
        int[][] res = new int[N][M];
        int nN = N/2;
        int nM = M/2;

        //4->3
        for(int i=nN; i<N; i++) {
            for(int j=0; j<nM; j++) {
                res[i][j+nM] = map[i][j];
            }
        }

        //3->2
        for(int i=nN; i<N; i++) {
            for(int j=nM; j<M; j++) {
                res[i-nN][j] = map[i][j];
            }
        }

        //2->1
        for(int i=0; i<nN; i++) {
            for(int j=nM; j<M; j++) {
                res[i][j-nM] = map[i][j];
            }
        }

        //1->4
        for(int i=0; i<nN; i++) {
            for(int j=0; j<nM; j++) {
                res[i+nN][j] = map[i][j];
            }
        }
        map = res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        method = new int[R];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++) {
            method[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<R; i++) {
            switch (method[i]) {
                case 1:
                    one();
                    break;
                case 2:
                    two();
                    break;
                case 3:
                    three();
                    break;
                case 4:
                    four();
                    break;
                case 5:
                    five();
                    break;
                case 6:
                    six();
                    break;
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
