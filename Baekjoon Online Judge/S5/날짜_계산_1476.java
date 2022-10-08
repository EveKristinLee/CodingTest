package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 날짜_계산_1476 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int e = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ne = 1;
		int ns = 1;
		int nm = 1;
		
		int year = 1;
		
		while(true) {
			if(ne == e && ns == s && nm == m) {
				break;
			}
			ne++;
			ns++;
			nm++;
			year++;
			if(ne > 15) {
				ne = 1;
			}
			if(ns > 28) {
				ns = 1;
			}
			if(nm > 19) {
				nm = 1;
			}
		}
		System.out.println(year);
	}
}
