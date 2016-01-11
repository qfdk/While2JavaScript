'use strict';

function Node(data, left, right) {
	this.data = data;
	this.left = left;
	this.right = right;
}

Node.prototype.getData = function() {
	return this.data;
};

//arbre binaire
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
	} else {
		let current = this.root;
		let parent;
		while (true) {
			parent = current;
			if (data < current.getData()) {
				current = current.left;
				if (!current) {
					parent.left = n;
					break;
				}
			} else {
				current = current.right;
				if (!current) {
					parent.right = n;
					break;
				}
			}
		}
	}
};

// nombre de node totale
Tree.prototype.countNode = function(node) {
	if (!node) return 0;
	return this.countNode(node.left) + this.countNode(node.right) + 1;
};
// nombre de node a doite
Tree.prototype.countRight = function(node) {
	if (!node) return 0;
	return this.countRight(node.right) + 1;
};
// un parcours
Tree.prototype.affichage = function(node) {
	if (node) {
		console.log(node.getData());
		this.affichage(node.left);
		this.affichage(node.right);
	}
};

function coucou()
{
	console.log("coucou");
}

// reuperer une valeur de inputX
function getValeur(v) {
	var rest = document.getElementsByTagName("input")[v];
	return rest.value;
}

function calculer() {
	var c = getValeur(0) + getValeur(2);
	document.getElementById("resultat").value = c;
	document.getElementById("res").innerHTML = c;
}


var tree = new Tree();

tree.insert("cons");
tree.insert("x");
tree.insert("nil");

tree.insert(tree);

console.log(tree.affichage(tree.root));
console.log(tree.countRight(tree.root));
