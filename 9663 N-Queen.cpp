#include <iostream>
#include <cmath>

using namespace std;

int N;
int board[16];
int cnt = 0;

bool check(int row) {
	for (int i = 0; i < row; i++) {
		//같은 라인이거나 대각선에 위치하면
		if (board[i] == board[row] || abs(board[row] - board[i]) == row - i) {
			return false;
			//board[i] == x좌표, i == y좌표
		}
		return true;
	}
}

void nqueen(int x) {
	if (x == N) {
		cnt++; //N번째 행까지 배치 완료 하면 방법의 개수 증가
	}
	else {
		for (int i = 0; i < N; i++) {
			board[x] = i;

			if (check(x)) {//유효하면 다음행에 퀸 배치, 아니면 다른위치로 변경
				nqueen(x + 1);
			}
		}
	}
}

int main(void) {
	cin >> N;
	nqueen(0);
	cout << cnt << "\n";
	return 0;
}