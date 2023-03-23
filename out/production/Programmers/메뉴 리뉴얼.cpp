#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

map<string, int> dic;

//조합 만들기
void dfs(string& order, string combi, int depth, int idx) {
	if (combi.size() == depth) {
		dic[combi]++;
		return;
	}

	for (int i = idx; i < order.size(); i++) {
		dfs(order, combi + order[i], depth, i + 1);
	}
}

bool comp(pair<string, int> a, pair<string, int> b) {
	return a.second > b.second;
}

vector<string> solution(vector<string> orders, vector<int> course) {
	vector<string> answer;
	
	for (int i = 0; i < orders.size(); i++) {
		sort(orders[i].begin(), orders[i].end());
		for (int j = 0; j < course.size(); j++) {
			dfs(orders[i], "", course[j], 0);
		}
	}

	vector<pair<string, int>> sorted;
	for (auto& order : dic) {
		if (order.second > 1) {
			sorted.push_back({ order.first, order.second });
		}
	}
	sort(sorted.begin(), sorted.end(), comp);

	for (int i = 0; i < course.size(); i++) {
		int cnt = 0;
		for (int j = 0; j < sorted.size(); j++) {
			if (sorted[j].first.size() != course[i]) {
				continue;
			}
			//제일 높은 숫자
			if (cnt == 0) {
				answer.push_back(sorted[j].first);
				cnt = sorted[j].second;
			}
			//같은 숫자
			else if (cnt == sorted[j].second) {
				answer.push_back(sorted[j].first);
			}
		}
	}
	
	sort(answer.begin(), answer.end());
	return answer;
}

int main(void) {
	vector<string> orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
	vector<int> course = { 2, 3, 4 };
	vector<string> result = solution(orders, course);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}