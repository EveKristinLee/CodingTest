import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어디에단어가들어갈수있을까1979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[][] map = new int[n][n];
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int cnt = 0;
            for(int i=0; i<n; i++) {
                int chk = 0;
                for(int j=0; j<n; j++) {
                    if(map[i][j] == 0) {
                        if(chk == k) {
                            cnt++;
                        }
                        chk = 0;
                    }
                    else {
                        chk++;
                    }
                }
                if(chk == k) {
                    cnt++;
                }
            }
            for(int j=0; j<n; j++) {
                int chk = 0;
                for(int i=0; i<n; i++) {
                    if(map[i][j] == 0) {
                        if(chk == k) {
                            cnt++;
                        }
                        chk = 0;
                    }
                    else {
                        chk++;
                    }
                }
                if(chk == k) {
                    cnt++;
                }
            }
            sb.append("#"+t+" "+cnt+"\n");
        }
        System.out.println(sb);
    }
}
