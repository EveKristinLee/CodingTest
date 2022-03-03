#include <iostream>
#include <cmath>

using namespace std;

int n;
int map[16] = { 0 };
int cnt = 0;

//대각선에 존재하는 좌표의 경우
//(x, y) (a,b)
//x - a == y - b
bool check(int level) {
	for (int i = 0; i < level; i++) {
		if (map[level] == map[i] || level - i == abs(map[level] - map[i]))
			return false;
		//map[i] = x좌표
		//i = y좌표
	}
	return true;
}

void n_Queen(int x) {
	if (x == n) {
		cnt++;
	}
	else {
		for (int i = 0; i < n; i++) {
			map[x] = i;
			if (check(x)) {
				n_Queen(x + 1);
			}
		}
	}
}


int main(void) {
	cin >> n;
	n_Queen(0);
	cout << cnt << "\n";
	return 0;
}