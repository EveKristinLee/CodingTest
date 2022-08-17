package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 배_1092 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> crane = new ArrayList<>();
		ArrayList<Integer> box = new ArrayList<>();
		for(int i=0; i<n; i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(crane);
		Collections.sort(box);
		if(crane.get(n-1) < box.get(m-1)) {
			System.out.println("-1");
			return;
		}
		int time = 0;
		while(!box.isEmpty()) {
			int idx = box.size() -1;
			for(int i=n-1; i>=0; i--) {
				if(idx == -1) {
					break;
				}
				if(crane.get(i) >= box.get(idx)) {
					box.remove(idx--);
				}
				else {
					idx--;
					i++; //크레인 유지
				}
			}
			time++;
		}
		System.out.println(time);
	}
}
