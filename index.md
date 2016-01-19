## Documentation compilateur While
### Introduction
Ce projet a pour objectif de compiler un programme écrit avec le langage While en un programme JavaScript. Nous travaillons en mode agile,les différents sprints ont une durée de une à deux semaines. A chaque fin de sprint,nous fournissons un délivrable pour construire le compilateur. Voici les membres de notre groupe :

Youssouf Maiga : Chef de projet
Bastien Cloarec
Haozhi Li
Salifou Nguetcheu
Baptiste Buron

### Getting start
Nous avons créé un dépôt git pour avoir une bonne gestion du projet

    git clone http://qfdk.github.io/While2JavaScript && cd While2JavaScript && make

## Machine cible
### Sémantique opérationnelle
Le langage ciblé étant le JavaScript, la gestion (statique, pile, tas) n'est pas de notre responsabilité.
Lorsqu'on utilise JavaScript, la mémoire est allouée lors de la création des objets puis libérée « automatiquement » lorsque ceux-ci ne sont plus utilisés. Cette libération automatique est appelée garbage collection en anglais ou ramasse-miettes. Le fait que ce processus soit automatique est souvent source de confusion et donne parfois l'impression que JavaScript (ou d'autres langages de haut niveau) ne permet pas de gérer la mémoire : nous allons voir que ce n'est pas le cas.
En 2012, l'ensemble des navigateurs web modernes disposent d'un ramasse-miettes implémentant cet algorithme mark-and-sweep. L'ensemble des améliorations apportées dans ce domaine de JavaScript représentent des améliorations basées sur cet algorithme, ce ne sont pas de nouveaux algorithmes ou une nouvelle définition pour les objets à supprimer.

## Style de machine cible
### Délivrables
1. Pretty Printer
2. Table des symboles
3. Code 3@
4. La bibliotheque du JavaScript

## Spécification : Traduction de while en javascript
Pas d'erreur à la l'exécution implique que toutes les variables doivent être initialisés. Nous nous arrangeons à ce qu'il n'y ait pas d'erreur à l’exécution.

### Variable non initialisé :
La valeur par défaut en while est nil ===> undefined
undefined en javascript signifie variable non initialisé alors que null  est utilipsé par le programme pour dire. En faisant seulement var nil, sans initialisation, sa valeur est underfined

``` javascript
    var nil;
```

### 1. nop
Le rôle des commande est de modifier l'état de la mémoire.
La commande nop ne fait rien. On la traduit par l'instruction 0+0 par exemple;

``` javascript
   console.log()
```
### 2. Commande c1;c2:
Nous devons exécuter C1 puis C2. La traduction en JavaScript donnera: 	

``` javascript
    traduction (C1)
    traduction (C2)
```
### 3. if E then C1 else C2 fi
Sa traduction donne :

``` javascript
if  (traduction(E)!=nil)
     traduction(C1)
else
      traduction (C2)
```

### 4.if E then C1 fi
Sa traduction donne :

``` javascript
if  (traduction(E)!= nil)
     traduction(C1)
```
### 5. while E do C od
Sa traduction donne :

``` javascript
while (E!= nil)
{
    traduction(C)
}
```
###6. for E do C od
Sa traduction donne :

``` JavaScript
int cpt0 = LibWh2JS.countRight(E);
for (var i0 = 0; i0 < cpt0; i0++){
    traduction(C)
}
```
### 7. foreach X in E do C od
``` JavaScript


function function_foreach (X,E)
{
    X=value(E);
    if(tl(X)!=nil)
    {
        function_foreach(X,tl(X))
    }
    if(hd(X)!=nil)
    {
        function_foreach(X,hd(X))
    }
    if((tl(X)==nil)&&(hd(X)==nil))
    {
        traduction(X);
    }
}
```


### 8. V1,...,Vn = E1,...En
Les Vi et les Ei designeront les traductions des noms de variable données par la table des variables.

```JavaScript
var tampon = new Array();
for (int i=1;i<=n;i++)
{
	tampon.push(Ei)
}
for(int i=1;i<=n;i++)
{
	Vi = tampon[i];
}

```

### 9.V1,...,Vm := f(E1,...,En)
Les Vi et les Ei designeront les traductions des noms de variable données par la table des variables.

``` javascript
var tampon = new Array(); // Pour stocker les write de f
executer(f,n,tampon) // Execution de la fonction f ayant n parametres
for(int i=0;i<m;i++)
{
	Vi+1 = tampon[i];
}

```
### 10. for X do X := E od
X désignera la traduction du nom de variable X comme le définit la table des variables.

``` JavaScript
X'=LibWh2JS.clone(X);
feuilleHd=LibWh.getFeuilleHd(X);
hd(feuilleHd)= new Tree(X');

```

### 11. X := (cons a b)

``` JavaScript
 // Un construction que nous allons définir
X = new BinTree(a,b)
```
## Code 3@ - Machine

### nop

```
	<nop,_,_,_>
```

### C1;C2

```
	<C1,_,_,_>
	<C2,_,_,_>
```

### X := (cons a b)

```
	<cons,X,a,b>
```

### X := (list a b)

```
	<list,X,a,b>
```

### X := (tl Y)

```
	<tl,X,Y,_>
```

### X := (hd Y)

```
	<hd,X,Y,_>
```

### if E then C fi

```
	<if C,_,E,_>
```

### if E then C1 else C2 fi

```
	<if C1 C2,_,E,_>
```

### While E do C od
```
	<while C,_,E,_>
```

### for E do C od
```
	<for C,_,E,_>
```

### foreach X in E do C od
```
	<foreach C,X,E,_>
```

### R -> E and E'
```
	<and,R,E,E'>
```

###  R -> E or E'
```
	<or,R,E,E'>
```

###  R -> not E
```
	<not,R,E,_>
```
###  R -> E =? E'
```
	<=?,R,E,E'>
```

## La bibliothèque du JavaScript

### TreeBinaire

```javascript
'use strict';

//On définie la variable nil, comme dit précédemment, nil = undefined
var  nil;


function Node(data, left, right) {
	this.data = data;
	this.left = left;
	this.right = right;
}

Node.prototype.getData = function() {
	return this.data;
};

//Créer un arbre binaire
function Tree(data,dataL,dataR) {
	this.root = null;
	this.insert(data,dataL,dataR);
}

// Insérer les données dans un arbre
//1. On crée un nouveau noeud dont racine à deux fils. Le premier paramètre correspond à la racine de l'arbre
//2. Le deuxième paramètre correspond au fils gauche de la racine
//3. Le troisième paramètre correspond au fils droit de la racine
Tree.prototype.insert = function(data,dataL,dataR) {

		this.root = new Node(data, null, null);
		this.root.left = dataL;
		this.root.right = dataR;
};

Tree.prototype.getRight=function()
{
	return this.root.right;
};

Tree.prototype.getLeft=function()
{
	return this.root.left;
};

Tree.prototype.getRoot=function()
{
	return this.root;
};

// Obtenir le nombre de noeuds total
Tree.prototype.countNode = function(node) {
	if (!node) return 0;
	return this.countNode(node.left) + this.countNode(node.right) + 1;
};

//Obtenir le nombre de noeuds à droite de la racine de l'arbre
Tree.prototype.countRight = function() {
	var tmp = this;
	var cpt=0;
	while ((tmp != nil)  && (tmp.root != nil)) {
		cpt++;
		tmp=tmp.root.right;
	}
	return cpt;
};

// Récupérer une valeur de inputX
function getValeur(v) {
	var rest = document.getElementsByTagName("input")[v];
	return rest.value;
}

var t=new Tree(1,2,0);
var t1=new Tree("nil",nil,new Tree("nil",nil,new Tree("nil",nil,new Tree("nil",nil,nil))));

console.log(t1.countRight());

```
