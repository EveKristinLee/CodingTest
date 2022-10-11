package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G4
public class 회전초밥_15961 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N; //접시의 수
		int d; //초밥의 가짓수
		int k; //연속해서 먹는 접시의 수
		int c; //쿠폰 번호
		int res = 0;
		int cnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int[] num = new int[N];
		int[] visit = new int[d+1];
		
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<k; i++) {
	 		visit[num[i]]++;
			if(visit[num[i]] == 1) {
				cnt++;
			}
		}
		res = cnt;
		
		int idx = 1;
		while(true) {
			visit[num[idx-1]]--;
			if(visit[num[idx-1]] == 0) { //먹은 적이 없어지면
				cnt--;
			}
			if(visit[num[(idx + (k-1)) % N]] == 0) { //다음 먹을게 먹었던 적이 없으면 종류++
				cnt++;
			}
			visit[num[(idx + (k-1)) % N]]++;
			
			if(visit[c] == 0) {
				res = Math.max(res, cnt+1);
			}
			else {
				res = Math.max(res, cnt);
			}
			
			idx++;
			if(idx == N) { //한바퀴 다돌았을때
				break;
			}
		}
		System.out.println(res);
	}

}
