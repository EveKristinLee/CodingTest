#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

string first = "";
string userid = "";
string nickname = "";

void splitString(string s) {
	int firstIdx = 0;
	int secondIdx = 0;
	first = "";
	userid = "";
	nickname = "";

	firstIdx = s.find(" ", 0);
	first = s.substr(0, firstIdx);
	secondIdx = s.find(" ", firstIdx + 1);
	userid = s.substr(firstIdx + 1, secondIdx - firstIdx - 1);
	if (first != "Leave") {
		nickname = s.substr(secondIdx + 1);
	}
}

vector<string> solution(vector<string> record) {
	vector<string> answer;
	unordered_map<string, string> names; //아이디, 닉네임
	for (int i = 0; i < record.size(); i++) {
		splitString(record[i]);
		if (first == "Enter" || first == "Change") {
			if (names.find(userid) != names.end()) {
				names[userid] = nickname;
			}
			else {
				names.emplace(userid, nickname);
			}
		}
	}

	for (int i = 0; i < record.size(); i++) {
		splitString(record[i]);
		string tmp = names[userid];
		if (first == "Leave") {
			tmp += "님이 나갔습니다.";
			answer.push_back(tmp);
		}
		else if (first == "Enter") {
			tmp += "님이 들어왔습니다.";
			answer.push_back(tmp);
		}
	}
	return answer;
}

int main(void) {
	vector<string> record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan" };
	vector<string> result = solution(record);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}