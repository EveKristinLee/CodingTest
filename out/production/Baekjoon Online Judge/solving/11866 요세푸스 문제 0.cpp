#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int main(void) {
	int n, k;
	cin >> n >> k;
	queue<int> person;
	vector<int> answer;
	for (int i = 1; i <= n; i++) {
		person.push(i);
	}
	int cnt = 0;
	while (!person.empty()) {
		int tmp = person.front();
		person.pop();
		cnt++;
		if (cnt != k) {
			person.push(tmp);
		}
		else {
			cnt = 0;
			answer.push_back(tmp);
		}
	}
	cout << "<";
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i];
		if (i != answer.size() - 1) {
			cout << ", ";
		}
	}
	cout << ">\n";
	return 0;
}