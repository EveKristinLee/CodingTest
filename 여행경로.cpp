#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> route;
bool visit[10000] = { false };

bool dfs(string status, vector<vector<string>> tickets, int cnt) {
	route.push_back(status);

	if (cnt == tickets.size()) {
		return true;
	}

	for (int i = 0; i < tickets.size(); i++) {
		if (status == tickets[i][0] && !visit[i]) {
			visit[i] = true;
			bool tmp = dfs(tickets[i][1], tickets, cnt + 1);
			if (tmp) {
				return true;
			}
			visit[i] = false;
		}
	}
	route.pop_back();
	return false;
}

vector<string> solution(vector<vector<string>> tickets) {
	vector<bool> visit(tickets.size(), false);
	sort(tickets.begin(), tickets.end());
	dfs("ICN", tickets, 0);
	return route;
}

int main(void) {
	vector<vector<string>> tickets = { {"ICN", "AOO"}, 
	{"AOO", "BOO"}, {"BOO", "COO"}, {"COO", "DOO"}, {"DOO", "EOO"}, {"EOO", "DOO"}, {"DOO", "COO"}, 
	{"COO", "BOO"}, {"BOO", "AOO"} };

	vector<string> result = solution(tickets);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " " << "\n";
	}

	return 0;
}