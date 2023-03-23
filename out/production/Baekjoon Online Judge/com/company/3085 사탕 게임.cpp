#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

//하, 우
int dx[2] = { 0, 1 };
int dy[2] = { 1, 0 };
int n;
char map[51][51];

bool isInside(int x, int y) {
	return (x >= 0) && (y >= 0) && (x < n) && (y < n);
}

//가장 긴 연속 부분 찾기
int findLongest() {
	int maxCnt = 0;

	//행 확인
	for (int i = 0; i < n; i++) {
		int tmpCnt = 1;
		for (int j = 1; j < n; j++) {
			if (map[i][j] == map[i][j - 1]) {
				tmpCnt++;
			}
			else {
				maxCnt = max(tmpCnt, maxCnt);
				tmpCnt = 1;
			}
		}
		maxCnt = max(tmpCnt, maxCnt);
	}

	//열 확인
	for (int j = 0; j < n; j++) {
		int tmpCnt = 1;
		for (int i = 1; i < n; i++) {
			if (map[i-1][j] == map[i][j]) {
				tmpCnt++;
			}
			else {
				maxCnt = max(tmpCnt, maxCnt);
				tmpCnt = 1;
			}
		}
		maxCnt = max(tmpCnt, maxCnt);
	}

	return maxCnt;
}


//인접한 두칸 교환
int change(int x, int y) {
	int maxCnt = 0;
	for (int i = 0; i < 2; i++) {
		int tmp = 0;
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (isInside(nx, ny)) {
			if (map[nx][ny] != map[x][y]) {
				//인접한 두칸 바꾸기
				swap(map[x][y], map[nx][ny]);

				//가장 긴 열 확인
				tmp = findLongest();

				//다시 원위치
				swap(map[x][y], map[nx][ny]);
			}
			else {
				continue;
			}
		}
		maxCnt = max(maxCnt, tmp);
	}
	return maxCnt;
}

int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}
	int maxCnt = 0;
	maxCnt = findLongest();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int tmpCnt = change(i, j);
			maxCnt = max(tmpCnt, maxCnt);
		}
	}
	cout << maxCnt << "\n";
	return 0;
}