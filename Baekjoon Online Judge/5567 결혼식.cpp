#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int n; //상근이의 동기의 수
int m; //리스트의 길이
vector<int> fri[10001];
int ans = 0;

//상근이로부터 거리를 담을 배열
int visit[10001];

void bfs(int x) {
	queue<int> q;
	q.push(x);
	visit[x] = 1;

	while (!q.empty()) {
		x = q.front();
		q.pop();

		for (int i = 0; i < fri[x].size(); i++) {
			int next = fri[x][i];
			if (visit[next] == 0) {
				visit[next] = visit[x] + 1;
				q.push(next);
			}

		}
	}
}

int main(void) {
	cin >> n;
	cin >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		fri[a].push_back(b);
		fri[b].push_back(a);
	}

	bfs(1);

	for (int i = 2; i <= n; i++) {
		if (visit[i] == 2 || visit[i] == 3) {
			ans++;
		}
	}
	cout << ans << "\n";

	return 0;
}