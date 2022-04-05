#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> pre;
vector<int> post;

struct Node {
	int x;
	int y;
	int idx;
	Node* left = NULL;
	Node* right = NULL;
};

bool comp(Node a, Node b) {
	if (a.y == b.y) {
		return a.x < b.x;
	}
	else {
		return a.y > b.y;
	}
}

void makeTree(Node* parent, Node* child) {
	if (child->x < parent->x) {
		if (parent->left == NULL) {
			parent->left = child;
		}
		else {
			makeTree(parent->left, child);
		}
	}
	else {
		if (parent->right == NULL) {
			parent->right = child;
		}
		else {
			makeTree(parent->right, child);
		}
	}
}

void preorder(Node* node) {
	if (node == NULL) {
		return;
	}
	pre.push_back(node->idx);
	preorder(node->left);
	preorder(node->right);
}

void postorder(Node* node) {
	if (node == NULL) {
		return;
	}
	postorder(node->left);
	postorder(node->right);
	post.push_back(node->idx);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
	vector<vector<int>> answer;
	vector<Node> nodes;
	for (int i = 0; i < nodeinfo.size(); i++) {
		Node tmp;
		tmp.x = nodeinfo[i][0];
		tmp.y = nodeinfo[i][1];
		tmp.idx = i + 1;
		nodes.push_back(tmp);
	}
	sort(nodes.begin(), nodes.end(), comp);

	Node* root = &nodes[0];
	for (int i = 1; i < nodes.size(); i++) {
		makeTree(root, &nodes[i]);
	}

	preorder(root);
	postorder(root);
	answer.push_back(pre);
	answer.push_back(post);
	return answer;
}

int main(void) {
	vector<vector<int>> nodeinfo = { {5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2} };
	vector<vector<int>> result = solution(nodeinfo);
	for (int i = 0; i < result.size(); i++) {
		for (int j = 0; j < result[i].size(); j++) {
			cout << result[i][j] << " ";
		}
		cout << "\n";
	}
	return 0;
}