package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 도서관_2247 {

	public static class Student implements Comparable<Student> {
		int stime;
		int etime;
		public Student(int stime, int etime) {
			super();
			this.stime = stime;
			this.etime = etime;
		}
		@Override
		public int compareTo(Student o) {
			int time = stime - o.stime;
			if(time == 0) {
				time = etime - o.etime;
			}
			return time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Student> students = new ArrayList<>();
		int maxStayTime = Integer.MIN_VALUE;
		int maxNoStayTime = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			students.add(new Student(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
						));
		}
		Collections.sort(students);
		int startTime = students.get(0).stime;
		int endTime = students.get(0).etime;
		for(int i=1; i<n; i++) {
			if(endTime >= students.get(i).stime) {
				if(endTime < students.get(i).etime) {
					endTime = students.get(i).etime;
				}
				maxStayTime = Math.max(maxStayTime, endTime - startTime);
			}
			else {
				startTime = students.get(i).stime;
				maxNoStayTime = Math.max(maxNoStayTime, startTime - endTime);
				endTime = students.get(i).etime;
			}
		}
		System.out.println(maxStayTime + " " + maxNoStayTime);
	}
}
