#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(string a, string b) {
	return a + b > b + a;
}

string solution(vector<int> numbers) {
	string answer = "";
	vector<string> num;
	for (int i = 0; i < numbers.size(); i++) {
		num.push_back(to_string(numbers[i]));
	}
	sort(num.begin(), num.end(), cmp);
	if (num[0] == "0") {
		return "0";
	}
	for (int i = 0; i < num.size(); i++) {
		answer += num[i];
	}
	return answer;
}

int main(void) {
	vector<int> numbers = { 3, 30, 34, 5, 9 };
	string result = solution(numbers);
	cout << result << "\n";
	return 0;
}