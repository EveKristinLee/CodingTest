#include <iostream>

using namespace std;


int N; //정점의 개수
int M; //간선의 개수
int map[1001][1001] = { 0, };
bool visit[1001] = { false, };


int cnt = 0; //연결요소의 개수

void dfs(int x) {
	visit[x] = true;

	for (int i = 1; i <= N; i++) {
		if (map[x][i] == 1 && visit[i] == false) {
			dfs(i);
		}
	}

}

int main(void) {
	cin >> N >> M;
	for (int i = 0; i <M; i++) {
		int a, b;
		cin >> a >> b;
		map[a][b] = 1;
		map[b][a] = 1;
	}

	for (int i = 1; i <= N; i++) {
		if (visit[i] == false) {
			cnt++;
			dfs(i);
		}
	}

	cout << cnt << "\n";

	return 0;
}