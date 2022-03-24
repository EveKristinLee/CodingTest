#include <iostream>
#include <queue>

using namespace std;

int M; //������ ����
int N; //������ ����
int box[1001][1001];
queue<pair<int, int>> q;

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

int day = 0; //�丶�䰡 �� �ʹµ� �ɸ��� �ּҽð�

bool isInside(int x, int y) {
	return (x >= 0) && (x < N) && (y >= 0) && (y < M);
}

void bfs() {
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInside(nx, ny) && box[nx][ny] == 0) {
				q.push(make_pair(nx, ny));
				//������ ���ÿ� �ɸ��� ��¥�� ����
				box[nx][ny] = box[x][y] + 1;
			}
		}
	}
}

int main(void) {
	cin >> M >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> box[i][j];
			if (box[i][j] == 1) {
				//���� �丶�� ť�� �ֱ�
				q.push(make_pair(i, j));
			}
		}
	}

	bfs();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (box[i][j] == 0) {
				cout << "-1" << "\n";
				return 0;
			}
			if (day < box[i][j]) {
				day = box[i][j];
			}
		}
	}

	//ó���� �丶�䰡 �;��ִٰ� ǥ���ϱ� ���� 1�� ���ش�
	cout << day - 1 << "\n";
	return 0;
}