#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int checkDist(pair<int, int> p1, pair<int, int>p2) {
	return abs(p1.first - p2.first) + abs(p1.second - p2.second);
}

bool checkWall(pair<int, int> p1, pair<int, int> p2, vector<vector<char>> room) {
	if (p1.first == p2.first) {
		if (room[p1.first][(p1.second + p2.second) / 2] != 'X') {
			return false;
		}
	}
	else if (p1.second == p2.second) {
		if (room[(p1.first + p2.first) / 2][p1.second] != 'X') {
			return false;
		}
	}
	else {
		int cnt = 0;
		for (int i = p1.first; i <= p2.first; i++) {
			if (p1.second < p2.second) {
				for (int j = p1.second; j <= p2.second; j++) {
					if (room[i][j] == 'X') {
						cnt++;
					}
				}
			}
			else {
				for (int j = p2.second; j <= p1.second; j++) {
					if (room[i][j] == 'X') {
						cnt++;
					}
				}
			}
		}
		if (cnt == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	return true;
}

vector<int> solution(vector<vector<string>> places) {
	vector<int> answer; 
	for (auto place : places) {
		vector<vector<char>> room(6, vector<char>(6));
		vector<pair<int, int>> person;
		for (int i = 0; i < place.size(); i++) {
			for (int j = 0; j < place[i].size(); j++) {
				room[i][j] = place[i][j];
				if (place[i][j] == 'P') {
					person.push_back({ i, j });
				}
			}
		}

		bool isAlright = true;
		for (int i = 0; i < person.size(); i++) {
			for (int j = i; j < person.size(); j++) {
				if (i == j) {
					continue;
				}
				//거리가 2이하
				if (checkDist(person[i], person[j]) <= 2) {
					if (checkWall(person[i], person[j], room) == false) {
						isAlright = false;
						break;
					}
					else {
						continue;
					}
				}
			}
		}
		if (isAlright == true) {
			answer.push_back(1);
		}
		else {
			answer.push_back(0);
		}
	}
	return answer;
}

int main(void) {
	vector<vector<string>> places = { {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" }, { "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" }, { "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
	vector<int> result = solution(places);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
}