/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
a) Create the two arrays below, spelled exactly as they are given. This will form the start for all the following questions.
*/
var boys = ["Peter", "lars", "Ole"];
var girls = ["Janne", "hanne", "Sanne"];
console.log("a. -------------------------------------");
console.log(boys);
console.log(girls);

/* 
b.
The array type has  a method concat() to merge two or more arrays (does concat mutate an existing array?)
answer: no, it creates a new one.
*/
console.log("b. -------------------------------------");
var all = boys.concat(girls);
console.log(all);

/*
c) The array type has a cool method to reduce an array into a single string join() (you will love this method)
·         Create a comma separated string containing all the names from the all-array, separated by commas.
·         Create a hyphen (-) separated string containing all the names from the all-array, separated by hyphens.
*/
console.log("c. -------------------------------------");
var comma = all.join();
var hyphen = all.join("-");
console.log(comma);
console.log(hyphen);

/*
The array type provides methods to add items to the start - unshift() and to the end push(..) of an array.
d)  Add the names Lone and Gitte to the end of the array (remember, all can be done in one-liners)
*/
console.log("d. -------------------------------------");
all.push("Lone","Gitte");
console.log(all);

/*
e)  Add the names Hans and Kurt to the start of the array
*/
console.log("e. -------------------------------------");
all.unshift("Hans","Kurt");
console.log(all);


/*
The array type provides methods to remove items from the start shift() and from the end pop(..) of an array.
f)  Remove the first name in the array (Hans)
*/
console.log("f. -------------------------------------")
all.shift();
console.log(all);

//g)  Remove the last name from the array (Gitte)
console.log("g. -------------------------------------");
all.pop();
console.log(all);

/*
The array type provides a method splice(..) which changes the array by removing existing elements and/or adding new
h) Remove Ole and Janne from the middle of the array
*/
console.log("h. -------------------------------------");
all.splice(3, 2);
console.log(all);

/*
The array type provides a method reverse() to reverse the elements of an array
i) Sanne thinks it’s unfair that the boys have to come first, reverse the all array, so that the girls come first.
*/
console.log("i. -------------------------------------");
all.reverse();
console.log(all);

/*
The array type provides a method sort() to sort the elements of an array
j) Peter thinks that this is just as unfair and suggests that the array should be sorted. Sort the array.

*/
console.log("j. -------------------------------------");
all.sort();
console.log(all);

//k) The default sort algorithm doesn’t handle the situation where the name can be either capitalized or not. Write a user-defined sort method to fix this problem.
console.log("k. -------------------------------------");
all.sort(function(a,b){
	return a.toLowerCase().localeCompare(b.toLowerCase());
});
console.log(all);

/*
The array type provides a method map() which returns a new array of the return value from executing the callback on every array item.
l) Convert all the names in the array to uppercase.

*/
console.log("l. -------------------------------------");
const uppercaseMap =  all.map(x => x.toUpperCase());
console.log(uppercaseMap);


/*
The array type has a method filter() that creates a new array with all elements that pass the test implemented by the provided callback
m) Create a new array containing all the names that start with either “l” or “L” (hint: use the filter function with a sufficient callback). 
*/
console.log("m. -------------------------------------");
const filterAll =  all.filter(x => x.charAt(0) == "l" || x.charAt(0) == "L");
console.log(filterAll);