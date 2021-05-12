#include <iostream>
#include <vector>
#include <algorithm>


using namespace std;

int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

char graph[25][25];
bool visit[25][25] = { 0, };

int n; //지도의 크기


int cnt = 0;
vector<int> house; //단지의 아파트 수



void printMap() {
	cout << "============================" << "\n";
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cout << graph[i][j];
		}
		cout << "\n";
	}
}

bool isInside(int x, int y) 
{
	return (x >= 1) && (x <= n) && (y >= 1) && (y <= n);
	
}

void dfs(int x, int y) {
	visit[x][y] = 1;

	if (graph[x][y] == '0') {
		return;
	}

	cnt++;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		
		if (isInside(nx, ny) && visit[nx][ny] == 0) {
			dfs(nx, ny);
		}
	}
	
}



int main(void) {

	cin >> n;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> graph[i][j];
		}
	}

	//printMap();

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (graph[i][j] == '1' && visit[i][j]==0) {
				cnt = 0;
				dfs(i, j);
				house.push_back(cnt);
			}
		}
	}

	sort(house.begin(), house.end());
	cout << house.size() << "\n";
	for (int i = 0; i < house.size(); i++) {
		cout << house[i] << "\n";
	}


	
	return 0;
}



