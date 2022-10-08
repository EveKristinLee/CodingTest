package solving.solve_0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {
	
	static int n; //구역 개수
	static ArrayList<Integer>[] town; 
	static int[] person;
	static int minDiff; //최소 인구수
	static int totalPerson;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		town = new ArrayList[n+1];
		person = new int[n+1];
		minDiff = Integer.MAX_VALUE;
		totalPerson = 0;
		for(int i=1; i<=n; i++) {
			town[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			totalPerson += person[i];
		}
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				town[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i=1; i<=n/2; i++) {
			combi(0, i, 1, new ArrayList<>());
		}
		if(minDiff != Integer.MAX_VALUE) {
			System.out.println(minDiff);			
		}
		else {
			System.out.println("-1");
		}
	}

	private static void combi(int cnt, int end, int start, ArrayList<Integer> select) {
		if(cnt == end) {
			Gary(select);
			return;
		}
		
		for(int i=start; i<=n; i++) {
			select.add(i);
			combi(cnt+1, end, i+1, select);
			select.remove(select.size()-1);
		}
	}

	private static void Gary(ArrayList<Integer> select) {
		//연결확인
		if(haveRoot(select)) {
			ArrayList<Integer> rest = new ArrayList<>();
			for(int i=1; i<=n; i++) {
				if(!select.contains(i)) {
					rest.add(i);
				}
			}
			if(haveRoot(rest)) {
				int aPerson = 0;
				int bPerson = 0;
				for(int i=0; i<select.size(); i++) {
					aPerson += person[select.get(i)];
				}
				bPerson = totalPerson - aPerson;
				minDiff = Math.min(minDiff, Math.abs(aPerson - bPerson));
			}
		}
	}
	
	private static boolean haveRoot(ArrayList<Integer> select) {
		boolean[] visit = new boolean[n+1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(select.get(0));
		visit[select.get(0)] = true;
		int nowCnt = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			nowCnt++;
			for(int i=0; i<town[cur].size(); i++) {
				if(!visit[town[cur].get(i)] && select.contains(town[cur].get(i))) {
					q.offer(town[cur].get(i));
					visit[town[cur].get(i)] = true;
				}
			}
		}
		if(nowCnt == select.size()) {
			return true;
		}
		return false;
	}

}
