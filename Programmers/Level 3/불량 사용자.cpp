#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int n;
int answer = 0;
set<vector<int>> s;

bool cmpId(string banId, string userId) {
	for (int i = 0; i < banId.size(); i++) {
		if (banId[i] != userId[i] && banId[i] != '*') {
			return false;
		}
	}
	return true;
}

void dfs(int bannIdx, int cnt, vector<bool>& visit, vector<string> user_id, vector<string> banned_id, vector<int> &id) {
	if (cnt == n) {
		vector<int> tmp = id;
		sort(tmp.begin(), tmp.end());
		s.insert(tmp);
		return;
	}

	for (int i = 0; i < user_id.size(); i++) {
		if (!visit[i]) {
			if ((banned_id[bannIdx].size() == user_id[i].size()) && cmpId(banned_id[bannIdx], user_id[i]) ){
				visit[i] = true;
				id.push_back(i);
				dfs(bannIdx + 1, cnt + 1, visit, user_id, banned_id, id);
				visit[i] = false;
				id.pop_back();
			}
		}
	}
}

int solution(vector<string> user_id, vector<string> banned_id) {
	vector<bool> visit(user_id.size() + 1, false);
	n = banned_id.size();
	vector<int> id;

	dfs(0, 0, visit, user_id, banned_id, id);
	return s.size();
}

int main(void) {
	vector<string> user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
	vector<string>banned_id = { "fr*d*", "*rodo", "******", "******" };
	int result = solution(user_id, banned_id);
	cout << result << "\n";
	return 0;
}