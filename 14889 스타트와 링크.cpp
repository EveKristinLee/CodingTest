#include <iostream>
#include <cmath>

using namespace std;

int N;
int S[21][21] = { 0, };
bool visit[21] = { false, };
int min = 987654321;

void divide(int cnt, int idx) {
	if (cnt == N / 2) { //ÀÎ¿ø¼ö Ã¼Å©
		int start = 0;
		int link = 0;
		//true¸é startÆÀ, false¸é linkÆÀ
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (visit[i] && visit[j]) {
					start += S[i][j];
				}
				else if (!visit[i] && !visit[j]) {
					link += S[i][j];
				}
			}
		}

		int tmp = abs(start - link);
		if (tmp < min) {
			min = tmp;
		}
		return;
	}

	for (int i = idx; i <= N; i++) {
		visit[i] = true;
		divide(cnt + 1, idx + 1);
		visit[i] = false;
	}
}

int main(void) {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> S[i][j];
		}
	}
	divide(0, 1);
	cout << min << "\n";
	return 0;
}