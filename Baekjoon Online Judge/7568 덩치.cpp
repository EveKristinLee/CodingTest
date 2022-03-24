#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, int>> person;
int n;

int main(void) {
	cin >> n;
	vector<int> cnt(n, 0);
	for (int i = 0; i < n; i++) {
		int w, h;
		cin >> w >> h;
		person.push_back(make_pair(w, h));
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i == j) {
				continue;
			}
			if (person[i].first < person[j].first) {
				if (person[i].second < person[j].second) {
					cnt[i]++;
				}
			}
		}
	}
	
	for (int i = 0; i < n; i++) {
		cout << cnt[i] + 1 << " ";
	}
	cout << "\n";
	
	return 0;
}