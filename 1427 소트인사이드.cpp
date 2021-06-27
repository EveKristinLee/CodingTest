#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

bool cmp(int a, int b) {
	return a > b;
}

int main(void) {
	string s;
	cin >> s;
	vector<int> v;
	for (int i = 0; i < s.size(); i++) {
		int tmp;
		tmp = s[i] - '0';
		v.push_back(tmp);
	}
	sort(v.begin(), v.end(), cmp);
	for (int i = 0; i < v.size(); i++) {
		cout << v[i];
	}
	return 0;
}