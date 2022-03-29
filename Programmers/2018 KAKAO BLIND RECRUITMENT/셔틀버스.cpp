#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> timeVec;

void timeToInt(string s) {
	int timeNum = 0;
	timeNum += stoi(s.substr(0, 2)) * 60;
	timeNum += stoi(s.substr(3, 2));
	timeVec.push_back(timeNum);
}

string solution(int n, int t, int m, vector<string> timetable) {
	string answer = "";
	for (int i = 0; i < timetable.size(); i++) {
		timeToInt(timetable[i]);
	}
	sort(timeVec.begin(), timeVec.end());
	int startTime = 540; //09:00
	int crewIdx = 0;
	int answerTime = 0;
	for (int i = 1; i <= n; i++) {
		int cnt = 0;
		for (int j = crewIdx; j < timeVec.size(); j++) {
			if (timeVec[j] <= startTime) {
				cnt++;
				crewIdx++;
			}
			if (cnt == m) {
				break;
			}
		}

		if (i == n) {
			if (cnt == m) {
				answerTime = timeVec[crewIdx - 1] - 1;
			}
			else {
				answerTime = startTime;
			}
		}
		startTime += t;
	}

	int hour = answerTime / 60;
	int min = answerTime % 60;
	char A = (hour / 10) + '0';
	char B = (hour % 10) + '0';
	char C = (min / 10) + '0';
	char D = (min % 10) + '0';
	answer += A;
	answer += B;
	answer += ":";
	answer += C;
	answer += D;
	
	return answer;
}

int main(void) {
	int n = 2;
	int t = 10;
	int m = 2;
	vector<string> timetable = { "09:10", "09:09", "08:00" };
	string result = solution(n, t, m, timetable);
	cout << result << "\n";
	return 0;
}