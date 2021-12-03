#include <iostream>
#include <cmath>

using namespace std;

int N;
int board[16];
int cnt = 0;

bool check(int row) {
	for (int i = 0; i < row; i++) {
		//���� �����̰ų� �밢���� ��ġ�ϸ�
		if (board[i] == board[row] || abs(board[row] - board[i]) == row - i) {
			return false;
			//board[i] == x��ǥ, i == y��ǥ
		}
		return true;
	}
}

void nqueen(int x) {
	if (x == N) {
		cnt++; //N��° ����� ��ġ �Ϸ� �ϸ� ����� ���� ����
	}
	else {
		for (int i = 0; i < N; i++) {
			board[x] = i;

			if (check(x)) {//��ȿ�ϸ� �����࿡ �� ��ġ, �ƴϸ� �ٸ���ġ�� ����
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