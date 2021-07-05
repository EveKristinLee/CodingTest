#include <iostream>
#include <algorithm>

using namespace std;

bool comp(int x, int y) {
	return x > y;
}

int main(void) {
	int N; //동전의 종류
	int K; //만들어야하는 합
	int total = 0; //동전 개수의 최솟값
	int coin[11];
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> coin[i];
	}
	sort(coin, coin + N, comp);

	for (int i = 0; i < N; i++) {
		while (K >= coin[i]) {
			K -= coin[i];
			total++;
		}
	}
	cout << total << "\n";
	return 0;
}