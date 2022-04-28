#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int isIn(string city, vector<pair<int, string>> cache) {
	if (!cache.empty()) {
		for (int i = 0; i < cache.size(); i++) {
			if (cache[i].second == city) {
				return i;
			}
		}
	}
	return -1;
}

bool comp(pair<int, string> a, pair<int, string> b) {
	return a.first > b.first;
}

int solution(int cacheSize, vector<string> cities) {
	int answer = 0;
	vector<pair<int, string>> cache;
	int idx = 0;
	for (int i = 0; i < cities.size(); i++) {
		transform(cities[i].begin(), cities[i].end(), cities[i].begin(), ::tolower);
	}
	if (cacheSize <= 0) {
		return cities.size() * 5;
	}
	for (int i = 0; i < cities.size(); i++) {
		//캐시에 있을때
		int state = isIn(cities[i], cache);
		if (state != -1) {
			//최근 사용을 변경
			cache[state].first = idx++;
			answer++;
		}
		else {//캐시에 없을때
			//캐시가 꽉 찼을때
			if (cache.size() >= cacheSize) {
				sort(cache.begin(), cache.end(), comp);
				cache.pop_back();
				cache.push_back({ idx++, cities[i] });
				answer += 5;
			}
			else {
				cache.push_back({ idx++, cities[i] });
				answer += 5;
			}
		}
	}
	return answer;
}

int main(void) {
	int cacheSize = 3;
	vector<string> cities = { "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA" };
	int result = solution(cacheSize, cities);
	cout << result << "\n";
	return 0;
}