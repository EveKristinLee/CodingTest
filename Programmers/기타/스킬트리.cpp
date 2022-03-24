#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(string skill, vector<string> skill_trees) {
	int answer = 0;

	for (int i = 0; i < skill_trees.size(); i++) {

		vector<char> tmp;
		for (int j = 0; j < skill_trees[i].size(); j++) {
			for (int k = 0; k < skill.size(); k++) {
				if (skill_trees[i][j] == skill[k]) {
					tmp.push_back(skill[k]);
				}
			}
		}

		bool flag = true;
		for (int j = 0; j < tmp.size(); j++) {
			if (tmp[j] != skill[j]) {
				flag = false;
				break;
			}
		}
		if (flag) {
			answer++;
		}
	}
	return answer;
}

int main(void) {

	string skill = "CBD";
	vector<string> skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };
	int result = solution(skill, skill_trees);

	cout << result << "\n";

	return 0;
}