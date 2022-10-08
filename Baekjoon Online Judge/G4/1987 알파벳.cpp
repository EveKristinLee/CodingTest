#include <iostream>

using namespace std;

int R; //세로
int C; //가로

char map[21][21];
int visit[27] = { false, }; //알파벳 = 26

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int cnt = 0; //말이 지난 칸수
int MaxCnt = 1; //말이 지날수 있는 최대 칸수

bool isInside(int x, int y) {
	return (x >= 1) & (x <= R) && (y >= 1) && (y <= C);
}

void dfs(int x, int y, int cnt) {
	if (cnt > MaxCnt) {
		MaxCnt = cnt;
	}

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (isInside(nx, ny) && !visit[map[nx][ny] - 'A']) {
			//백트래킹이 필요한 이유
			//이미 지나온 칸에서 더 많이 갈 수 있는 경로가 있을 수 있기때문
			visit[map[nx][ny] - 'A'] = true;
			dfs(nx, ny, cnt + 1);
			visit[map[nx][ny] - 'A'] = false;
		}
	}
}

int main(void) {
	cin >> R >> C;
	for (int i = 1; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			cin >> map[i][j];
		}
	}
	visit[map[1][1] - 'A'] = true; //시작하는 칸 포함
	dfs(1, 1, 1);

	cout << MaxCnt << "\n";

	return 0;
}