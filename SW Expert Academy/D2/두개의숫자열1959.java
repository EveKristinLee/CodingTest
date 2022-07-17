import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 두개의숫자열1959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int maxSum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++){
                b[i] = Integer.parseInt(st.nextToken());
            }
            if(n <= m) {
                for(int i=0; i<m-n+1; i++) {
                    int sum = 0;
                    for(int j=0; j<n; j++) {
                        sum += a[j] * b[i+j];
                    }
                    maxSum = Math.max(sum, maxSum);
                }
            }
            if(n > m) {
                for(int i=0; i<n-m+1; i++) {
                    int sum = 0;
                    for(int j=0; j<m; j++) {
                        sum += a[i+j] * b[j];
                    }
                    maxSum = Math.max(sum, maxSum);
                }
            }
            sb.append("#"+t+" "+maxSum+"\n");
        }
        System.out.println(sb);
    }
}
