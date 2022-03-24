#include <iostream>
#include <algorithm>

using namespace std;

int main(void) {
	int cost[1001][3];  //최소비용
	int color[1001][3]; //각 색의 비용
	int N;
	cin >> N;
	
	//각 집에대해 색 비용 입력
	for (int i = 1; i <= N; i++) {
		cin >> color[i][0] >> color[i][1] >> color[i][2];
	}
	//집이 빨간색으로 칠해지려면 이전 집이 파란색이나 초록색으로 칠해져야 하고
	//초록색으로 칠해지려면 이전 집이 빨간색이나 파란색으로 칠해져야 하고
	//파란색으로 칠해지려면 이전 집이 빨간색이나 초록색으로 칠해져야 한다.
	cost[1][0] = color[1][0];
	cost[1][1] = color[1][1];
	cost[1][2] = color[1][2];
	for (int i = 2; i <= N; i++) {
		cost[i][0] = color[i][0] + min(cost[i - 1][1], cost[i - 1][2]);
		cost[i][1] = color[i][1] + min(cost[i - 1][0], cost[i - 1][2]);
		cost[i][2] = color[i][2] + min(cost[i - 1][1], cost[i - 1][0]);
	}
	cout << min(cost[N][0], min(cost[N][1], cost[N][2]));
	return 0;
}