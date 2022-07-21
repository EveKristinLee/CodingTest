import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자배열회전1961 {

    public static int[][] turn(int[][] map) {
        int len = map.length;
        int[][] result = new int[len][len];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                result[j][len-1-i] = map[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t= 1; t<=T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] turn90 = turn(map);
            int[][] turn180 = turn(turn90);
            int[][] turn270 = turn(turn180);

            System.out.println("#"+t);
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(turn90[i][j]);
                    if(j == n-1) {
                        System.out.print(" ");
                    }
                }
                for(int j=0; j<n; j++) {
                    System.out.print(turn180[i][j]);
                    if(j == n-1) {
                        System.out.print(" ");
                    }
                }
                for(int j=0; j<n; j++) {
                    System.out.print(turn270[i][j]);
                    if(j == n-1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }

        }

    }

}
