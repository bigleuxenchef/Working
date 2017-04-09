/**
 * http://usejsdoc.org/
 */




/*
 * This program is based on the hello-world example 
 * modify by Rumi
 * 
 * For detailed output: DEBUG=json-rules-engine node
 */

require('colors')
require('json-rules-engine')
let Engine = require('json-rules-engine').Engine
let Rule = require('json-rules-engine').Rule


/**
 * Setup a new engine
 */
let engine = new Engine()

/**
 * Create a rule
 */
//example of implementing the following rule
//		if (a > b) a + c else b + c

var a = 8;
//var b = 50;
var i = 8888; // interesting to demonstrate that the rules is evaluated in this case once at run time
var k = 0;
var kk = 0;


let rule = new Rule({
	// define the 'conditions' for when "hello world" should display
	conditions: {
		all: [{
			fact: 'a',
			operator: 'greaterThan',
			value: { fact: 'b'}
		}]
	},
	// define the 'event' that will fire when the condition evaluates truthy
	event: {
		type: 'message',
		params: {
			data: '*** greaterThan ***',
			fact : 'a',
			k : 'to be assigned'
		}
	},
	onFailure: function (event, almanac) {
		console.log('failure ' + event.params.data);
		almanac.factValue('b')
		.then(a => 
		{almanac.factValue('c')
			.then(c => 
			{event.params.k = b + c;
			console.log('failure ' + event.params.data);
			console.log('event ' + JSON.stringify(event));
			})})		
	
	},
	onSuccess: function (event, almanac)
	{
		almanac.factValue('a')
		.then(a => 
		{almanac.factValue('c')
			.then(c => 
			{event.params.k = a + c;
			console.log('success ' + event.params.data);
			console.log('event ' + JSON.stringify(event));
			})})






	}  

})

let rule2 = new Rule({
	// define the 'conditions' for when "hello world" should display
	conditions: {
		all: [{
			fact: 'a',
			operator: 'lessThanInclusive',
			value: { fact: 'b'}
		}]
	},
	// define the 'event' that will fire when the condition evaluates truthy
	event: {
		type: 'message',
		params: {
			data: '*** lessThanInclusive ***',
			fact : 'a',
			k : { fact: 'b'}
		}
	}
})

let rule3 = new Rule({
	// define the 'conditions' for when "hello world" should display
	conditions: {
		all: [{
			fact: 'a',
			operator: 'greaterThanInclusive',
			value: { fact: 'b'}
		}]
	},
	// define the 'event' that will fire when the condition evaluates truthy
	event: {
		type: 'message',
		params: {
			data: '*** greaterThanInclusive ***',
			fact : 'a',
			k : { fact: 'b'}
		}
	}
})

//add rule to engine
engine.addRule(rule)
engine.addRule(rule2)
engine.addRule(rule3)




//run the engine



i = 5

//facts can be defined using dynamic parameter resolved at runtime.

let facts = { a: i, b : 6 , c : i/2}

//engine is an asynchronous process that uses promise mechanism

engine
.run(facts)
.then(events => { // run() returns events with truthy conditions
	events.map(event => {console.log("returned value " + JSON.stringify(event)); 
	//return k+=event.params.k;
	
	})
})






