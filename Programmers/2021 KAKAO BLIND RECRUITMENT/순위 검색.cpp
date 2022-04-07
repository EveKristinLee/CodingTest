#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <sstream>
#include <bitset>
#include <algorithm>

using namespace std;

vector<string> getInfo(string s, char separate) {
	istringstream iss(s);
	string buffer;

	vector<string> result;
	while (getline(iss, buffer, separate)) {
		result.push_back(buffer);
	}
	return result;
}

pair<string, int> getQuery(string s) {
	pair<string, int> result;
	string tmp = "";
	string tmpScore = "";
	for (int i = 0; i < s.size(); i++) {
		if (s[i] != ' ') {
			if (s[i] >= '0' && s[i] <= '9') {
				tmpScore += s[i];
			}
			else {
				tmp += s[i];
			}
		}
		else {
			if (s[i + 1] == 'a') {
				i += 4;
			}
		}
	}
	result.first = tmp;
	result.second = stoi(tmpScore);
	return result;
}

vector<int> solution(vector<string> info, vector<string> query) {
	vector<int> answer;
	map<string, vector<int>> dic;
	for (int i = 0; i < info.size(); i++) {
		vector<string> tmp = getInfo(info[i], ' ');
		for (int j = 0; j < 16; j++) {
			string tmpCase = "";
			string bit = bitset<4>(j).to_string();
			for (int k = 0; k < 4; k++) {
				if (bit[k] == '1') {
					tmpCase += tmp[k];
				}
				else {
					tmpCase += '-';
				}
			}
			int score = stoi(tmp[4]);
			dic[tmpCase].push_back(score);
		}
	}

	for (auto it = dic.begin(); it != dic.end(); it++) {
		sort(it->second.begin(), it->second.end());
	}
	
	for (int i = 0; i < query.size(); i++) {
		pair<string, int> tmp = getQuery(query[i]);

		auto itr = lower_bound(dic[tmp.first].begin(), dic[tmp.first].end(), tmp.second);
		int cnt = dic[tmp.first].size() - (itr - dic[tmp.first].begin());
		
		//int cnt = dic[tmp.first].end() - lower_bound(dic[tmp.first].begin(), dic[tmp.first].end(), tmp.second);
		answer.push_back(cnt);
	}
	return answer;
}

int main(void) {
	vector<string> info = { "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50" };
	vector<string> query = { "java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150" };
	vector<int> result = solution(info, query);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}