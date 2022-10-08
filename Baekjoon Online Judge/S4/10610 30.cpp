#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
//30�� ��� => 0�� �ϳ��� ���� && 3�� ���(�� �ڸ����� ���� 3�� ���)

vector<int> num;

bool comp(int a, int b) {
	return a > b;
}

int main(void) {
	string s;
	int sum = 0;
	bool zero = false;
	cin >> s;
	for (int i = 0; i < s.size(); i++) {
		int tmp = s[i] - '0';
		sum += (tmp);
		num.push_back(tmp);
		if (tmp == 0) {
			zero = true;
		}
	}
	if (sum % 3 != 0 || !zero) {
		cout << -1 << "\n";
	}
	else {
		sort(num.begin(), num.end(), comp);
		for (int i = 0; i < num.size(); i++) {
			cout << num[i];
		}
	}
	cout << "\n";
	return 0;
}