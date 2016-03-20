'use strict';

var  nil;
// Definition du Node
function Node(data, left, right) {
	this.data = data;
	this.left = left;
	this.right = right;
}
// obtenir l'element
Node.prototype.getData = function() {
	return this.data;
};

// arbre binaire
function Tree(data,dataL,dataR) {
	this.root = null;
	this.insert(data,dataL,dataR);
}

// insert le data pour consture un arbre
Tree.prototype.insert = function(data,dataL,dataR) {

		this.root = new Node(data, null, null);
		this.root.left = dataL;
		this.root.right = dataR;
};
// obtenir le fils droit
Tree.prototype.getRight=function()
{
	return this.root.right;
};
// obtenir le fils gauche
Tree.prototype.getLeft=function()
{
	return this.root.left;
};

// obtenir le node
// Tree.prototype.getRoot=function()
// {
// 	return this.root;
// };

// nombre de node totale
Tree.prototype.countNode = function(node) {
	if (!node) return 0;
	return this.countNode(node.left) + this.countNode(node.right) + 1;
};
// compter le fils droit
Tree.prototype.countRight = function() {
	var tmp = this;
	var cpt=0;
	// cas d'arret
	while ((tmp != nil)  && (tmp.root != nil)) {
		cpt++;
		tmp=tmp.root.right;
	}
	return cpt;
};

// reuperer une valeur de inputX
function getValeur(v) {
	var rest = document.getElementsByTagName("input")[v];
	return rest.value;
}

//Test

// var t=new Tree(1,2,0);
// var t1=new Tree("nil",nil,new Tree("nil",nil,new Tree("nil",nil,new Tree("nil",nil,nil))));
// t.insert(1,2,new Tree(3,5,new Tree(3,5,4)));

// console.log(t.getRight().getRight().getRight().getRight());
// console.log(t1.countRight());
