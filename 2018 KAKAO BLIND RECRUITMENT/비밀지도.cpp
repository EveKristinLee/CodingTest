#include <iostream>
#include <string>
#include <vector>
#include <bitset>

using namespace std;

string toBinary(int num, int size) {
	string s = "";
	
	for (int i = 0; i < size; i++) {
		if (num % 2 == 0 && num != 0) {
			s += "0";
		}
		else if(num %2 ==1 && num != 0)  {
			s += "1";
		}
		if (num != 0) {
			num /= 2;
		}
		else {
			s += "0";
		}
	}
	reverse(s.begin(), s.end());
	return s;
}

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
	vector<string> answer;
	vector<vector<int>> map(n + 1, vector<int>(n+1, 0));
	for (int i = 0; i < arr1.size(); i++) {
		string tmpA = toBinary(arr1[i], n);
		string tmpB = toBinary(arr2[i], n);
		for (int j = 0; j < n; j++) {
			if (tmpA[j] == '1' || tmpB[j] == '1') {
				map[i][j] = 1;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		string s = "";
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 1) {
				s += "#";
			}
			else {
				s += " ";
			}
		}
		answer.push_back(s);
	}
	
	return answer;
}

int main(void) {
	int n = 5;
	vector<int> arr1 = { 9, 20, 28, 18, 11 };
	vector<int> arr2 = { 30, 1, 21, 17, 28 };
	vector<string> result = solution(n, arr1, arr2);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}