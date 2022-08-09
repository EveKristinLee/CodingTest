package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사칙연산_유효성_검사_1233 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine());
			boolean valid = true;
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int nodeIdx = Integer.parseInt(st.nextToken()); //노드 번호
				char node = st.nextToken().charAt(0); //두번째 인자
				
				if(st.hasMoreTokens()) { //node = 연산자
					if(node >= '0' && node <= '9') {
						valid = false;
					}
				}
				else { //node = 숫자
					if(node < '0' || node > '9') {
						valid = false;
					}
				}
			}
			if(valid) {
				sb.append("#"+t+" 1\n");
			}
			else {
				sb.append("#"+t+" 0\n");
			}
		}
		System.out.println(sb);
	}
}
