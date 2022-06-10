#include <iostream>
#include <vector>
#include <stack>

using namespace std;

//¿Ã¡ﬂforπÆ
//vector<int> solution(vector<int> prices) {
//	vector<int> answer(prices.size(), 0);
//	for (int i = 0; i < prices.size(); i++) {
//		for (int j = i + 1; j < prices.size(); j++) {
//			answer[i]++;
//			if (prices[i] > prices[j]) {
//				break;
//			}
//		}
//	}
//	return answer;
//}

//stack
vector<int> solution(vector<int> prices) {
	vector<int> answer(prices.size(), 0);
	int size = prices.size();
	stack<int> st;
	for (int i = 0; i < size; i++) {
		while (!st.empty() && prices[st.top()] > prices[i]) {
			answer[st.top()] = i - st.top();
			st.pop();
		}
		st.push(i);
	}

	while (!st.empty()) {
		answer[st.top()] = size - (st.top() + 1);
		st.pop();
	}
	return answer;
}

int main(void) {
	vector<int> prices = { 1, 2, 3, 2, 3 };
	vector<int> result = solution(prices);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}