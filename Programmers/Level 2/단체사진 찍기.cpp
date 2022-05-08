#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

char p[8] =  { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
bool visit[8] = { false };
int answer;

void dfs(int cnt, string line, vector<string> data) {
	if (cnt == 8) {
		for (int i = 0; i < data.size(); i++) {
			char first = data[i][0];
			char sec = data[i][2];
			char op = data[i][3];
			int dist = data[i][4] - '0';
			dist++;

			int firIdx = -1;
			int secIdx = -1;
			for (int j = 0; j < 8; j++) {
				if (line[j] == first) {
					firIdx = j;
				}
				if (line[j] == sec) {
					secIdx = j;
				}
				if (firIdx != -1 && secIdx != -1) {
					break;
				}
			}

			if (op == '=') {
				if (abs(firIdx - secIdx) != dist) {
					return;
				}
			}
			if (op == '>') {
				if (abs(firIdx - secIdx) <= dist) {
					return;
				}
			}
			if (op == '<') {
				if (abs(firIdx - secIdx) >= dist) {
					return;
				}
			}
		}
		answer++;
	}

	for (int i = 0; i < 8; i++) {
		if (!visit[i]) {
			visit[i] = true;
			line.push_back(p[i]);
			dfs(cnt + 1, line, data);
			line.pop_back();
			visit[i] = false;
		}
	}
}

int solution(int n, vector<string> data) {
	answer = 0;
	dfs(0, "", data);
	return answer;
}

int main(void) {
	int n = 2;
	vector<string> data = { "N~F=0", "R~T>2" };
	int result = solution(n, data);
	cout << result << "\n";
	return 0;
}