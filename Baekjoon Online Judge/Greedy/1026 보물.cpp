#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

bool comp(int a, int b) {
	return a > b;
}

int main(void) {
	int n;
	vector<int> a;
	vector<int> b;
	int sum = 0;

	cin >> n;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		a.push_back(x);
	}
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		b.push_back(x);
	}
	sort(b.begin(), b.end(), comp);//����
	sort(a.begin(), a.end());//����

	//s�� �ּڰ�
	for (int i = 0; i < n; i++) {
		sum += a[i] * b[i];
	}
	cout << sum << "\n";

	return 0;
}