#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, int>> com; //회의시간
int cnt = 0; //최대 회의 수

//최대 회의수를 구해야하기 때문에 시작시간이 아닌 종료시간을 기준으로 정렬
//종료시간을 기준으로 오름차순으로 정렬
//종료시간이 같을 경우 시작시간을 기준으로 오름차순으로 정렬한다.
bool comp(pair<int, int> a, pair<int, int> b) {
	if (a.second != b.second) {
		return b.second > a.second;
	}
	else {
		return b.first > a.first;
	}
}

int main(void) {
	int N; //전체 회의수
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start, end;
		cin >> start >> end;
		com.push_back(make_pair(start, end));
	}
	//회의시간 정렬
	sort(com.begin(), com.end(), comp);
	int endTime = com[0].second;
	cnt = 1;
	//전 회의의 종료 시간보다 큰 시작시간이라면 회의 수에 포함
	for (int i = 1; i <= N; i++) {
		if (com[i].first >= endTime) {
			cnt++;
			endTime = com[i].second;
		}
	}
	cout << cnt << "\n";
	return 0;
}