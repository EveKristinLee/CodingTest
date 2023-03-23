#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

pair<string, string> getInfo(string s) {
	pair<string, string> answer;
	int idx = s.find(" ", 0);
	string reporter = s.substr(0, idx);
	string reported = s.substr(idx + 1);
	answer.first = reported;
	answer.second = reporter;
	return answer;
}

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
	vector<int> answer;
	map<string, vector<string>> dic;
	vector<pair<string, int>> cnt;

	for (int i = 0; i < id_list.size(); i++) {
		cnt.push_back({ id_list[i], 0 });
	}

	for (int i = 0; i < report.size(); i++) {
		pair<string, string> info = getInfo(report[i]);
		dic[info.first].push_back(info.second);
	}

	for (int i = 0; i < id_list.size(); i++) {
		vector<string> reporters = dic[id_list[i]];
		sort(reporters.begin(), reporters.end());
		reporters.erase(unique(reporters.begin(), reporters.end()), reporters.end());
		if (reporters.size() >= k) {
			for (int j = 0; j < reporters.size(); j++) {
				for (int k = 0; k < cnt.size(); k++) {
					if (cnt[k].first == reporters[j]) {
						cnt[k].second += 1;
					}
				}
			}
		}
	}
	for (int i = 0; i < cnt.size(); i++) {
		answer.push_back(cnt[i].second);
	}
	return answer;
}

int main(void) {
	vector<string> id_list = { "con", "ryan" };
	vector<string> report = { "ryan con", "ryan con", "ryan con", "ryan con" };
	int k = 3;
	vector<int> result = solution(id_list, report, k);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}