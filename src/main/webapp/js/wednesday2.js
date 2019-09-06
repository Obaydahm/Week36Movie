//JavaScript functions and Callbacks
console.log("JavaScript functions and Callbacks");
//1) Create a new JavaScript file and add these three functions
console.log("1. -------------------------------------");
function add(n1, n2){
   return n1 + n2;
}

var sub = function(n1,n2){
  return n1 - n2
} 

var mul = function(n1, n2){
	return n1 / n2;
}

var cb = function(n1,n2, callback){
	try{
		typeof n1 === "number";
		typeof n2 === "number";
		typeof callback === "TypeError";
	  	return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);
	}catch(e){
		console.log(e.name + ": " + e.log);
	}
};

//2) Call the functions above as sketched below. It’s not about doing it as fast as you can, but about understanding what's happening, so make sure you understand each line.
console.log( add(1,2) )     // What will this print?: 3
console.log( add )          // What will it print and what does add represent?: it will print the function add
console.log( add(1,2,3) ) ; // What will it print: 3
console.log( add(1) );	  // What will it print: NaN - because we need to give it 1 more parameter.
console.log( cb(3,3,add) ); // What will it print: Result from the two numbers: 3 + 3 = 6
console.log( cb(4,3,sub) ); // What will it print: Result from the two numbers: 4 + 3 = 1
console.log(cb(3,3,add())); // What will it print: TypeError: callback is not a function - because when u add brackets u no longer reference to the method, instead u pass in the method.
console.log(cb(3,"hh",add));// What will it print Result from the two numbers: 3 + hh = 3hh

//5) Call cb, this time with an anonymous function that divides the first argument with the second
console.log( cb(10,2,mul) );

//Callbacks (with map, filter and forEach)
console.log("Callbacks (with map, filter and forEach)");
/*
1) Declare a JavaScript array and initialize it with some names (Lars, Jan, Peter, Bo, Frederik etc.). Use the filter method to create a new array with only names of length <=3.
Use the forEach method to iterate and print (console.log) both the original and the new array.
*/
var names = ["Obaydah", "Sinan", "Shpat", "Younes", "Kim", "Ali", "Bo"];
const filterNames = names.filter(x => x.length <= 3);
filterNames.forEach(function(x){
	console.log(x);
});

//2) Use the names-array created above, and, using its map method, create a new array with all names uppercased.
const mapNames = names.map(x => x.toUpperCase());
console.log(mapNames);