import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도쿠검증1974 {

    static int[][] map = new int[9][9];

    public static boolean chkLine(int x, int y) {
        for(int i=0; i<9; i++) {
            if(y != i) {
                if(map[x][i] == map[x][y]) {
                    return false;
                }
            }
            if(x != i) {
                if(map[i][y] == map[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean chkSquare() {
        boolean[] arr = new boolean[10];
        for(int i=0; i<6; i+=3) {
            for(int ni = i; ni<i+3; ni++) {
                for(int nj = i; nj<i+3; nj++) {
                    arr[map[ni][nj]] = true;
                }
            }
        }
        for(int i=1; i<10; i++) {
            if(!arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        for(int t =1; t<=T; t++) {
            for(int i=0; i<9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<9; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean flag = true;;
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    if(!chkLine(i, j)) {
                        flag = false;
                        break;
                    }
                }
            }

            if(!flag) {
                sb.append("#"+t+" 0\n");
            }
            else {
                if(!chkSquare()) {
                    sb.append("#"+t+" 0\n");
                }
                else {
                    sb.append("#"+t+" 1\n");
                }
            }

        }
        System.out.println(sb);
    }
}

