#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int n;
vector<pair<double, double>> star;
vector<pair<double, pair<int, int>>> dist;
int parent[101] = { 0 };
double result = 0;

double calcDist(pair<double, double> a, pair<double, double> b) {
	return sqrt(pow((a.first - b.first), 2) + pow((a.second - b.second), 2));
}

int find(int x) {
	if (parent[x] == x) {
		return x;
	}
	else {
		return parent[x] = find(parent[x]);
	}
}

bool sameParent(int x, int y) {
	x = find(x);
	y = find(y);

	if (x == y) {
		return true;
	}
	else {
		return false;
	}
}

void Union(int x, int y) {
	x = find(x);
	y = find(y);
	if (x != y) {
		parent[y] = x;
	}
}

void kruskal() {
	sort(dist.begin(), dist.end());
	
	for(int i=0; i<dist.size(); i++) {
		if (!sameParent(dist[i].second.first, dist[i].second.second)) {
			Union(dist[i].second.first, dist[i].second.second);
			result += dist[i].first;
		}
	}
}

int main(void) {
	cin >> n;
	for (int i = 0; i < n; i++) {
		double x, y;
		cin >> x >> y;
		star.push_back(make_pair(x, y));
		parent[i] = i;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i == j || j < i) {
				continue;
			}
			double eachDist = calcDist(star[i], star[j]);
			dist.push_back(make_pair(eachDist, make_pair(i, j)));
		}
	}

	kruskal();

	//소수점 두자리까지 출력하기 위한 함수들
	cout << fixed;
	cout.precision(2);
	cout << result << "\n";

	return 0;
}