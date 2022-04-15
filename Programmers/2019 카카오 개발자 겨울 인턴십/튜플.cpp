#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool comp(vector<int> a, vector<int> b) {
	return a.size() < b.size();
}

vector<int> solution(string s) {
	vector<int> answer;
	vector<vector<int>> num;
	bool isOpen = false;
	string tmp = "";
	vector<int> tmpNum;
	bool check[10001] = { false };
	for (int i = 1; i < s.size() - 1; i++) {
		if (!isOpen && s[i] == '{') {
			isOpen = true;
		}
		else if (isOpen && (s[i] >= '0' && s[i] <= '9')) {
			tmp += s[i];
		}
		else if (isOpen && s[i] == ',') {
			tmpNum.push_back(stoi(tmp));
			tmp = "";
		}
		else if (isOpen && s[i] == '}') {
			tmpNum.push_back(stoi(tmp));
			num.push_back(tmpNum);
			isOpen = false;
			tmp = "";
			tmpNum.clear();
		}
	}
	sort(num.begin(), num.end(), comp);
	for (int i = 0; i < num.size(); i++) {
		for (int j = 0; j < num[i].size(); j++) {
			if (!check[num[i][j]]) {
				check[num[i][j]] = true;
				answer.push_back(num[i][j]);
			}
		}
	}
	return answer;
}

int main(void) {
	string s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
	vector<int> result = solution(s);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}