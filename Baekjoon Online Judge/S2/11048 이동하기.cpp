#include <iostream>
#include <algorithm>

using namespace std;
 
int d[1001][1001] = { 0 };
int map[1001][1001];

int main(void) {
	int n, m;
	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> map[i][j];
		}
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			map[i][j] = map[i][j] + max(map[i - 1][j], max(map[i - 1][j - 1], map[i][j - 1]));
		}
	}
	cout << map[n][m] << "\n";
	return 0;
}