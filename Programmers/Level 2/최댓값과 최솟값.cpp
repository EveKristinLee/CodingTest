#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

vector<string> split(string s, char del) {
	istringstream ss(s);
	string buffer;
	vector<string> result;

	while (getline(ss, buffer, del)) {
		result.push_back(buffer);
	}
	return result;
}

string solution(string s) {
	string answer = "";
	vector<string> nums = split(s, ' ');
	int minNum = 0;
	int maxNum = 0;
	for (int i = 1; i < nums.size(); i++) {
		if (stoi(nums[minNum]) > stoi(nums[i])) {
			minNum = i;
		}
		else if (stoi(nums[maxNum]) < stoi(nums[i])) {
			maxNum = i;
		}
	}
	answer += nums[minNum];
	answer += " ";
	answer += nums[maxNum];
	return answer;
}

int main(void) {
	string s = "1 2 3 4";
	string result = solution(s);
	cout << result << "\n";
	return 0;
}