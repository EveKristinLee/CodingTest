#include <iostream>
#include <stack>

using namespace std;

int main(void) {
	stack<int> st;
	int K;
	int sum = 0;
	cin >> K;
	for (int k = 0; k < K; k++) {
		int a;
		cin >> a;
		if (a == 0) {
			if (!st.empty()) {
				st.pop();
			}
		}
		else {
			st.push(a);
		}
	}

	for (int i = 0; !st.empty(); i++) {
		sum += st.top();
		st.pop();
	}
	cout << sum << "\n";
	return 0;
}