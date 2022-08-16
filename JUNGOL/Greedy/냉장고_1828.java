package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 냉장고_1828 {
	public static class Chemical implements Comparable<Chemical> {
		int minTemp;
		int maxTemp;
		public Chemical(int minTemp, int maxTemp) {
			super();
			this.minTemp = minTemp;
			this.maxTemp = maxTemp;
		}
		@Override
		public int compareTo(Chemical o) { //최고온도 오름차순 -> 최저온도 오름차순
			int temp = maxTemp - o.maxTemp;
			if(temp == 0) {
				temp = minTemp - o.minTemp;
			}
			return temp;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Chemical> arr = new ArrayList<>();
		int cnt = 1;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr.add(new Chemical(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(arr);
		int maxTmp = arr.get(0).maxTemp; //첫번째 최고온도 저장
		for(int i=1; i<n; i++) {
			//maxTmp보다 현재 최저 온도가 크다면 냉장고 개수 늘리고, maxTmp 갱신
			if(maxTmp < arr.get(i).minTemp) {
				cnt++;
				maxTmp = arr.get(i).maxTemp;
			}
		}
		System.out.println(cnt);
	}
}
