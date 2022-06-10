#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.first == b.first) {
		return a.second < b.second;
	}
	return a.first > b.first;
}

bool cmp2(pair<string, int> a, pair<string, int> b) {
	return a.second > b.second;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
	vector<int> answer;
	map<string, vector<pair<int, int>>> dic;
	map<string, int> cnt;
	for (int i = 0; i < genres.size(); i++) {
		dic[genres[i]].push_back(make_pair(plays[i], i));
		cnt[genres[i]] += plays[i];
	}

	vector<pair<string, int>> v(cnt.begin(), cnt.end());
	sort(v.begin(), v.end(), cmp2);

	for (int i = 0; i < v.size(); i++) {
		if (dic[v[i].first].size() >= 2) {
			sort(dic[v[i].first].begin(), dic[v[i].first].end(), cmp);
			answer.push_back(dic[v[i].first][0].second);
			answer.push_back(dic[v[i].first][1].second);
		}
		else {
			answer.push_back(dic[v[i].first][0].second);
		}
	}
	return answer;
}

int main(void) {
	vector<string> genres = { "classic", "pop", "classic", "classic","jazz","pop", "Rock", "jazz" };
	vector<int> plays = { 500, 600, 150, 800, 1100, 2500, 100, 1000 };
	vector<int> result = solution(genres, plays);
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << " ";
	}
	cout << "\n";
	return 0;
}