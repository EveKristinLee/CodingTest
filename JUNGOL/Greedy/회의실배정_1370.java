package jungol.bk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 더 많은 회릐를 배정하기 위해서
 * 종료시간이 짧은 회의들을 많이 배정한다.
 * ==> 종료 시간을 기준으로 오름 차순 정렬
 */
public class Main_Bk_1370_회의실배정 {
	
	public static class Room implements Comparable<Room> {
		int rnum;
		int stime;
		int etime;
		public Room(int rnum, int stime, int etime) {
			super();
			this.rnum = rnum;
			this.stime = stime;
			this.etime = etime;
		}
		
		@Override
		public int compareTo(Room o) {
			int time = etime - o.etime;
			if(time==0) {
				time = stime - o.stime;
			}
			return time;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Room> room = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			room.add(new Room(
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken())
								));
		}
		
//		회의실에 배정한 회의의 번호를 담을 리스트
		ArrayList<Integer> cnums = new ArrayList<>(N);
		
//		그리디로 선택하기 위해 회의를 종료시간 기준으로 정렬한다.
		Collections.sort(room);
		
//		정렬한 회의들 중 첫번째는 가장 빨리 끝나는 회의이므로 무조건 선택
		cnums.add(room.get(0).rnum);
		int endTime = room.get(0).etime; 	//종료시간 다음으로 오는 모든 회의 시작 시간과 비교하기 위한 변수
		//첫번째 회의는 이미 선택했으므로 두번째 회의 부터 비교
		for(int i=1, end = room.size(); i<end; i++) {
			//이전 회의의 종료 시간이 다음 회의의 시작 시간과 일치 하거나  일찍 종료했다면 다음 회의로 선택
			if(endTime <= room.get(i).stime) {
				cnums.add(room.get(i).rnum);
				endTime = room.get(i).etime; //선택한 회의의 종료시간을 다음 회의의 시작 시간과 비교하기 위해 갱신
			}
		}
//		배정된 회의 수 출력
		System.out.println(cnums.size());
//		배정된 회의 번호 출력
		for (Integer num : cnums) {
			System.out.print(num + " ");
		}
	}
}
