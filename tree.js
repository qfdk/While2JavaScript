'use strict';

function Node(data, left, right) {
	this.data = data;
	this.left = left;
	this.right = right;
}

Node.prototype.getData = function() {
	return this.data;
};

//	 arbre binaire
function Tree() {
	this.root = null;
}

// insert le data
//1. test si on a le racine
//2. test la valeur < this, 3 sinon 4
//3.
//4.
Tree.prototype.insert = function(data) {

	let n = new Node(data, null, null);
	if (!this.root) {
		this.root = n;
		console.log("add racine");
	} else {
		let current = this.root;
		let parent;
		while (true) {
			parent = current;
			if (data < current.getData()) {
				current = current.left;
				if (!current) {
					parent.left = n;
					console.log("add left");
					break;
				}
			} else {
				current = current.right;
				if (!current) {
					parent.right = n;
					console.log("add right");
					break;
				}
			}
		}
	}
};


Tree.prototype.countNode = function(node) {
	if (!node) return 0;
	return this.countNode(node.left) + this.countNode(node.right) + 1;
};

Tree.prototype.countRight = function(node) {
	if (!node) return 0;
	return this.countNode(node.right) + 1;
};

Tree.prototype.inOrder = function(node) {
	if (node) {
		inOrder(node.left);
		console.log(node.getData());
		inOrder(node.right);
	}
};

// test
//var n = new Node(1, null, null);
// console.log(n.getData());

var tree = new Tree();

tree.insert(1);
tree.insert(6);
tree.insert(3);

tree.inOrder(this.root);

console.log(tree.countNode(this.root));
