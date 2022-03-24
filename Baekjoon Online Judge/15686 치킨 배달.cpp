#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int m;
int map[51][51];
vector<pair<int, int>> chicken;
vector<pair<int, int>> home;
bool visit[14] = { false };
int minNum = 987654321;

int calcDist(pair<int, int> a, pair<int, int> b) {
	return abs(a.first - b.first) + abs(a.second - b.second);
}

void dfs(int cnt, int idx) {
	if (cnt == m) {
		int tmpSum = 0;
		for (int i = 0; i < home.size(); i++) {
			int tmpDist = 987654321;
			for (int j = 0; j < chicken.size(); j++) {
				if (visit[j]) {
					tmpDist = min(tmpDist, calcDist(home[i], chicken[j]));
				}
			}
			tmpSum += tmpDist;
		}

		minNum = min(minNum, tmpSum);
		return;
	}

	//치킨집 개수 이상의 idx 접근을 방지하기 위함
	//치킨집을 선택하지 않고도 계속 idx가 증가하기 때문
	if (idx == chicken.size()) {
		return;
	}

	//치킨집 선택
	visit[idx] = true;
	dfs(cnt + 1, idx + 1);
	//치킨집 선택 x
	visit[idx] = false;
	dfs(cnt, idx + 1);
}

int main(void) {
	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1) {
				home.push_back(make_pair(i, j));
			}
			else if (map[i][j] == 2) {
				chicken.push_back(make_pair(i, j));
			}
		}
	}

	dfs(0, 0);
	cout << minNum << "\n";

	return 0;
}