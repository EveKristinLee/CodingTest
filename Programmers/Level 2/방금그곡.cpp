#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

vector<string> getInfo(string musicInfo) {
	istringstream ss(musicInfo);
	string tmp;
	vector<string> v;

	while (getline(ss, tmp, ',')) {
		v.push_back(tmp);
	}

	for (int i = 0; i < v.size(); i++) {
		cout << v[i] << " ";
	}
	cout << "\n";
	return v;
}

int getTime(string start, string end) {
	int time = 0;
	string startHour = start.substr(0, 2);
	string startMin = start.substr(3, 2);
	string endHour = end.substr(0, 2);
	string endMin = end.substr(3, 2);

	int startH = stoi(startHour);
	int startM = stoi(startMin);
	int endH = stoi(endHour);
	int endM = stoi(endMin);

	time += endM - startM;
	time += (endH - startH) * 60;

	return time;
}

string getTotalPlay(int time, string play) {
	string totalPlay;
	if (time < play.size()) {
		for (int i = 0; i < time; i++) {
			totalPlay += play[i];
		}
	}
	else {
		int cnt = time / play.size();
		for (int c = 0; c < cnt; c++) {
			for (int i = 0; i < play.size(); i++) {
				totalPlay += play[i];
			}
		}
		int plus = time % play.size();
		if (plus != 0) {
			for (int p = 0; p < plus; p++) {
				totalPlay += play[p];
			}
		}
	}
	
	cout << "totalPlay : " << totalPlay << "\n";
	return totalPlay;
}

//#을 소문자로
string change(string s) {
	for (int i = 1; i < s.size(); i++) {
		if (s[i] == '#') {
			s[i - 1] = tolower(s[i - 1]);
			s.erase(s.begin() + i);
		}
	}
	cout << s << "\n";
	return s;
}

bool comp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

string solution(string m, vector<string> musicinfos) {
	string answer = "";
	string changeM = change(m);
	vector<pair<int, string>> fitVec;
	int cnt = 0; //조건을 만족하는 음악 개수
	for (auto song : musicinfos) {
		vector<string> musicInfo;
		musicInfo = getInfo(song);
		int time = getTime(musicInfo[0], musicInfo[1]);
		cout << time << "\n";
		string play = change(musicInfo[3]);
		string totalPlay = getTotalPlay(time, play);
		if (totalPlay.find(changeM) != string::npos) {
			cnt++;
			fitVec.push_back({ time, musicInfo[2] });
		}
	}
	if (cnt == 1) {
		return fitVec[0].second;
	}
	else if (cnt >= 2) {
		sort(fitVec.begin(), fitVec.end(), comp);
		return fitVec[0].second;
	}
	return "(None)";
}

int main(void) {
	string m = "ABC";
	vector<string> musicinfos = { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
	string result = solution(m, musicinfos);
	cout << result << "\n";
	return 0;
}