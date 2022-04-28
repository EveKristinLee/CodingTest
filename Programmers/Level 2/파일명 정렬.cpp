#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<pair<string, string>, int>> splitFiles;

void split(string s, int idx) {
	string head = "";
	string num = "";
	int i;
	for (i = 0; i < s.size(); i++) {
		if (('a' <= s[i] && 'z' >= s[i]) || s[i] == ' ' || s[i] == '-') {
			head += s[i];
		}
		else if ('0' <= s[i] && s[i] <= '9') {
			num += s[i];
			if (('a' <= s[i + 1] && 'z' >= s[i + 1]) || s[i + 1] == ' ' || s[i + 1] == '-' || s[i + 1] == '.') {
				break;
			}
		}
	}
	splitFiles.push_back({{ head, num }, idx});
}

bool comp(pair<pair<string, string>, int> a, pair<pair<string, string>, int> b) {
	if (a.first.first == b.first.first) {
		if (stoi(a.first.second) == stoi(b.first.second)) {
			return a.second < b.second;
		}
		else {
			return stoi(a.first.second) < stoi(b.first.second);
		}
	}
	else {
		return a.first.first < b.first.first;
	}
}

vector<string> solution(vector<string> files) {
	vector<string> answer;
	for (int i = 0; i < files.size(); i++) {
		string tmpString = "";
		for (int j = 0; j < files[i].size(); j++) {
			if ('A' <= files[i][j] && files[i][j] <= 'Z') {
				tmpString += files[i][j] + 32;
			}
			else {
				tmpString += files[i][j];
			}
		}
		split(tmpString, i);
	}
	sort(splitFiles.begin(), splitFiles.end(), comp);

	for (int i = 0; i < splitFiles.size(); i++) {
		answer.push_back(files[splitFiles[i].second]);
	}
	return answer;
}

int main(void) {
	vector<string> files = { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
	vector<string> result = solution(files);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}