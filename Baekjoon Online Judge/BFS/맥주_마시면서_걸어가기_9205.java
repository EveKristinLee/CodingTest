package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 맥주_마시면서_걸어가기_9205 {

	static int n;
	static Integer[] start;
	static Integer[] end;
	static boolean[] visit;
	static List<Integer[]> pos;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- >0) {
			n = Integer.parseInt(br.readLine());
			start = new Integer[2];
			end = new Integer[2];
			pos= new ArrayList<Integer[]>();
			visit = new boolean[n+2];
			
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pos.add(new Integer[] {x, y});
			}
			
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			pos.add(end); //시작점부터 한번에 못가면 마지막 지점까지 확인해줘야하기때문에 pos에 end좌표 저장
			
			if(bfs()) {
				System.out.println("happy");
			}
			else {
				System.out.println("sad");
			}
		}
	}

	private static boolean bfs() {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.offer(start);
		visit[0] = true;
		
		//시작점에서 끝점까지 한번에 갈 수 있음 + 없어도 가능
		if(Math.abs(start[0]-end[0]) + Math.abs(start[1] - end[1]) <= 1000) {
			return true;
		}
		
		//아니면 while로 확인해줘야 한다.
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			
			
			for(int i=0; i<n+1; i++) {
				if(!visit[i+1]) {
					if(Math.abs(top[0] - pos.get(i)[0]) + Math.abs(top[1] - pos.get(i)[1]) <= 1000) {
						if(i == n) {
							return true;
						}
						q.offer(new Integer[] {pos.get(i)[0], pos.get(i)[1]});
						visit[i+1] = true;
					}
				}
			}
		}
		return false;
	}

}
