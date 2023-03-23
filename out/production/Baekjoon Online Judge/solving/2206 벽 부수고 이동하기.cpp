#include <iostream>
#include <queue>

using namespace std;

int N; //����
int M; //����

char map[1001][1001];
int path[1001][1001][2]; // x, y, �� ������ �ִ� Ƚ��

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

bool isInside(int x, int y) {
	return (x > 0) && (x <= N) && (y > 0) && (y <= M);
}

void bfs(int x, int y, int flag) {
	queue<pair<pair<int, int>, int>> q;
	q.push(make_pair(make_pair(x, y), flag));
	path[x][y][flag] = 1;

	while (!q.empty()) {
		x = q.front().first.first;
		y = q.front().first.second;
		flag = q.front().second;

		q.pop();

		if (x == N && y == M) {
			cout << path[x][y][flag] << "\n";
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (isInside(nx, ny)) {
				//���� ���� �� && ���� �ȶվ��� ��
				if (map[nx][ny] == '1' && flag == 1) {
					path[nx][ny][flag - 1] = path[x][y][flag] + 1;
					q.push(make_pair(make_pair(nx, ny), flag - 1));
				}
				//���� �ִ� �� && �湮���� x
				if (map[nx][ny] == '0' && path[nx][ny][flag] == 0) {
					path[nx][ny][flag] = path[x][y][flag] + 1;
					q.push(make_pair(make_pair(nx, ny), flag));
				}
			}
		}
	}
	//������ ���� x
	cout << -1 << "\n";
	return;
}

int main(void) {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cin >> map[i][j];
		}
	}
	bfs(1, 1, 1);

	return 0;
}