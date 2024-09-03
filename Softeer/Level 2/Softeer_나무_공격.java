import java.io.*;
import java.util.*;

public class Softeer_나무_공격 {
    static int n;
    static int m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int cnt = 0;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cnt+=1;
                }
            }
        }

        for(int t=0; t<2; t++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            for(int i=l; i<=r; i++) {
                for(int j=0; j<m; j++) {
                    if(map[i][j] == 1) {
                        map[i][j] = 0;
                        cnt-=1;
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
