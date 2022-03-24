#include <iostream>
#include <string>
#include <vector>
using namespace std;

bool isInside(int x, int y) {
	return (x >= -5) && (y >= -5) && (x <= 5) && (y <= 5);
}

int solution(string dirs) {
	int answer = 0;
	int map[11][11][11][11] = { 0 };
	int x = 0;
	int y = 0;

	for (int i = 0; i < dirs.size(); i++) {
		if (dirs[i] == 'U') {
			int nx = x;
			int ny = y + 1;
			if (isInside(nx, ny)) {
				if (map[x][y][nx][ny] != 1) {
					map[x][y][nx][ny] = 1;
					map[nx][ny][x][y] = 1;
					answer++;
				}
				y = ny;
			}
		}
		else if (dirs[i] == 'D') {
			int nx = x;
			int ny = y - 1;
			if (isInside(nx, ny)) {
				if (map[x][y][nx][ny] != 1) {
					map[x][y][nx][ny] = 1;
					map[nx][ny][x][y] = 1;
					answer++;
				}
				y = ny;
			}
		}
		else if (dirs[i] == 'L') {
			int nx = x - 1;
			int ny = y;
			if (isInside(nx, ny)) {
				if (map[x][y][nx][ny] != 1) {
					map[x][y][nx][ny] = 1;
					map[nx][ny][x][y] = 1;
					answer++;
				}
				x = nx;
			}
		}
		else if (dirs[i] == 'R') {
			int nx = x + 1;
			int ny = y;
			if (isInside(nx, ny)) {
				if (map[x][y][nx][ny] != 1) {
					map[x][y][nx][ny] = 1;
					map[nx][ny][x][y] = 1;
					answer++;
				}
				x = nx;
			}
		}
	}

	return answer;
}

int main(void) {
	string dirs = "LULLLLLLU";
	cout << solution(dirs) << "\n";

	return 0;
}
