#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <map>

using namespace std;

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second) {
		return a.first < b.first;
	}
	else {
		return a.second > b.second;
	}
}

double avg(vector<int> num) {
	double sum = 0;
	for (int i = 0; i < num.size(); i++) {
		sum += num[i];
	}
	sum /= num.size();
	double ans = round(sum);
	if (ans == -0) {
		return 0;
	}
	else {
		return ans;
	}
}

int mid(vector<int> num) {
	sort(num.begin(), num.end());
	return num[(num.size() / 2)];
}

int popular(vector<int> num) {
	map<int, int> cnt;
	for (int i = 0; i < num.size(); i++) {
		cnt[num[i]]++;
	}

	vector<pair<int, int>> vec(cnt.begin(), cnt.end());
	sort(vec.begin(), vec.end(), cmp);
	if (vec.size() >= 2) {
		if (vec[0].second == vec[1].second) {
			return vec[1].first;
		}
	}
	return vec[0].first;
}

int range(vector<int> num) {
	sort(num.begin(), num.end());
	return num[num.size() - 1] - num[0];
}

int main(void) {
	int n;
	vector<int> num;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		num.push_back(a);
	}
	cout << avg(num) << "\n";
	cout << mid(num) << "\n";
	cout << popular(num) << "\n";
	cout << range(num) << "\n";
	return 0;
}