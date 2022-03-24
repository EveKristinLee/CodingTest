#include <iostream>

using namespace std;
int N;
int map[11][11];
bool visit[11] = { false, };
int minCost = 987654321;

void dfs(int start, int now, int cnt, int charge) {
	if (cnt == N && start == now) {
		if (charge < minCost) {
			minCost = charge;
			return;
		}
	}

	for (int i = 1; i <= N; i++) {
		if (map[now][i] == 0) { //연결 x
			continue;
		}

		if (!visit[i]) {
			visit[i] = true;
			charge += map[now][i];

			if (charge <= minCost) { //최소값보다 작아야 방문
				dfs(start, i, cnt + 1, charge);
			}

			visit[i] = false;
			charge -= map[now][i];
		}
	}
}

int main(void) {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> map[i][j];
		}
	}

	for (int i = 1; i <= N; i++) {
		dfs(i, i, 0, 0);
	}
	cout << minCost << "\n";
	return 0;
}