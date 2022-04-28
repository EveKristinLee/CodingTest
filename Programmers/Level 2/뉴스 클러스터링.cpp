#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> toSet(string s) {
	vector<string> result;
	for (int i = 0; i < s.size() -1; i++) {
		if (('A' <= s[i] && 'Z' >= s[i]) || ('a' <= s[i] && 'z' >= s[i])) {
			if (('A' <= s[i + 1] && 'Z' >= s[i + 1]) || ('a' <= s[i + 1] && 'z' >= s[i + 1])) {
				result.push_back(s.substr(i, 2));
			}
		}
	}
	return result;
}

int get_intersection(vector<string> a, vector<string> b) {
	int cnt = 0;
	int size = max(a.size(), b.size());
	cout << size << "\n";
	vector<string> in;
	vector<bool> visit(size, false);
	for (int i = 0; i < a.size(); i++) {
		for (int j = 0; j < b.size(); j++) {
			if (a[i] == b[j] && !visit[i]) {
				visit[i] = true;
				in.push_back(a[i]);
				cnt++;
			}
		}
	}
	for (int i = 0; i < in.size(); i++) {
		cout << in[i] << " ";
	}
	cout << "\n";
	return cnt;
}

int get_union(vector<string> a, vector<string> b) {
	int cnt = a.size();
	
	for (int i = 0; i < a.size(); i++) {
		for (int j = 0; j < b.size(); j++) {
			if (a[i] == b[j]) {
				break;
			}
			if (j == b.size() - 1) {
				cnt++;
			}
		}
	}
	return cnt;
}

int solution(string str1, string str2) {
	int answer = 0;
	vector<string> a, b;
	transform(str1.begin(), str1.end(), str1.begin(), ::tolower);
	transform(str2.begin(), str2.end(), str2.begin(), ::tolower);
	a = toSet(str1);
	b = toSet(str2);

	if (a.empty() && b.empty()) {
		return 65536;
	}

	sort(a.begin(), a.end());
	sort(b.begin(), b.end());

	vector<string> unionVec(a.size() + b.size());
	vector<string> interVec(a.size() + b.size());

	auto iter1 = set_union(a.begin(), a.end(), b.begin(), b.end(), unionVec.begin());
	unionVec.erase(iter1, unionVec.end());
	auto iter2 = set_intersection(a.begin(), a.end(), b.begin(), b.end(), interVec.begin());
	interVec.erase(iter2, interVec.end());

	int unionCnt = unionVec.size();
	int interCnt = interVec.size();

	if (unionCnt == 0) {
		return 65536;
	}
	answer = ((double)interCnt / (double)unionCnt) * 65536;
	
	return answer;
}

int main(void) {
	string str1 = "FRANCE";
	string str2 = "french";
	int result = solution(str1, str2);
	cout << result << "\n";
	return 0;
}