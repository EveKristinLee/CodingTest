#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n; 
int k;
int map[101][101] = { 0 };
int l;
vector<pair<int, char>> moveVec;
int sec = 0;
deque<pair<int, int>> snake; //���� ��ġ ����
int nowD = 1;//���� ���� = ������

//��, ��, ��, ��
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };


bool isInside(int x, int y) {
	return (x >= 1) && (y >= 1) && (x <= n) && (y <= n);
}

bool moving() {
	int x = snake.front().first;
	int y = snake.front().second;
	int nx = x + dx[nowD];
	int ny = y + dy[nowD];

	//���̳� �� ���������� false;
	if (!isInside(nx, ny) || map[nx][ny] == -1) {
		return false;
	}

	//����� ������ ���� ��ĭ ���̱�
	if (map[nx][ny] == 0) {
		int bx = snake.back().first;
		int by = snake.back().second;
		snake.pop_back();
		map[bx][by] = 0;
	}

	snake.push_front(make_pair(nx, ny));
	map[nx][ny] = -1;
	return true;
}

int main(void) {
	cin >> n;
	cin >> k;
	for (int i = 0; i < k; i++) {
		int x, y;
		cin >> x >> y;
		map[x][y] = 1;
	}
	cin >> l;
	for (int i = 0; i < l; i++) {
		int x;
		char c;
		cin >> x >> c;
		moveVec.push_back(make_pair(x, c));
	}

	snake.push_back(make_pair(1, 1));
	map[1][1] = -1; //���� ������ -1

	for (int i = 0; i < l; i++) {
		int x = moveVec[i].first;
		char c = moveVec[i].second;
		while (sec < x) {
			if (!moving()) {
				cout << sec + 1 << "\n";
				return 0;
			}
			sec++;
		}
		if (c == 'D') {
			nowD += 1;
			if (nowD == 4) {
				nowD = 0;
			}
		}
		else {
			nowD -= 1;
			if (nowD == -1) {
				nowD = 3;
			}
		}
	}
	//moveVec�� ������ ���� ���������� �̵��ؾ���
	while (1) {
		if (!moving()) {
			cout << sec + 1 << "\n";
			return 0;
		}
		sec++;
	}
	return 0;
}