#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

bool cmp(pair<int, string> p1, pair<int, string> p2) {
	return p1.first < p2.first;
}

int main(void) {
	int N;
	cin >> N;
	pair<int, string> p;
	vector<pair<int, string>> v;
	for (int i = 0; i < N; i++) {
		cin >> p.first >> p.second;
		v.push_back(p);
	}
	stable_sort(v.begin(), v.end(), cmp); //원래의 순서를 손상시키지 않는 정렬
	for (int i = 0; i < N; i++) {
		cout << v[i].first << " " << v[i].second << "\n";
	}
	return 0;
}