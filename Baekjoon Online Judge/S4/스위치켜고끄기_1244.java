package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int sN = Integer.parseInt(br.readLine());
		for(int i=0; i<sN; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(gender == 1) { //남학생
				for(int j=num; j<=n; j+=num) {
					switches[j] = switches[j] == 0 ? 1 : 0;
				}
			}
			else { //여학생
				int l = num-1;
				int r = num+1;
				while(true) {
					if(l < 1 || r > n) { //구간X
						break;
					}
					if(switches[l] != switches[r]) { //대칭X
						break;
					}
					l--;
					r++;
				}
				l++; //마지막 undo
				r--;
				while(l <= r) {
					switches[l] = switches[l] == 0 ? 1 : 0;
					l++;
				}
				
			}
			System.out.println("-------------------");
			for(int j=1; j<=n; j++) {
				System.out.print(switches[j] + " ");
				if(i%20 == 0) {
					System.out.println();
				}
			}
			System.out.println("-------------------");
		}
		for(int i=1; i<=n; i++) {
			System.out.print(switches[i] + " ");
			if(i%20 == 0) {
				System.out.println();
			}
		}
	}
}
