import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파리퇴치2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] map = new int[n][n];
            int maxCnt = 0;
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i=0; i<n-m+1; i++) {
                for(int j=0; j<n-m+1; j++){
                    int cnt = 0;
                    for(int k=0; k<m; k++) {
                        for(int z=0; z<m; z++) {
                            cnt += map[i+k][j+z];
                        }
                    }
                    maxCnt = Math.max(cnt, maxCnt);
                }
            }
            sb.append("#"+t+" "+maxCnt+"\n");
        }
        System.out.println(sb);
    }
}
