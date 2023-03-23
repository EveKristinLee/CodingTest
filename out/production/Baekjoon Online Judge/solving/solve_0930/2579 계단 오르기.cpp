#include<iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int stairs[301]; //계단점수
	int cost[301]; //dp

	int n; //계단의 계수
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> stairs[i];
	}
	cost[1] = stairs[1];
	cost[2] = stairs[1] + stairs[2];
	cost[3] = max(stairs[1] + stairs[3], stairs[2] + stairs[3]);

	for (int i = 4; i <= n; i++) {
		cost[i] = max(cost[i - 2] + stairs[i], cost[i - 3] + stairs[i - 1] + stairs[i]);
	}
	cout << cost[n] << "\n";
	return 0;
}