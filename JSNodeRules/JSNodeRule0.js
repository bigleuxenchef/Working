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


var i = 8888; // interesting to demonstrate that the rules is evaluated in this case once at run time
var k = 0;
var p = [];

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
		almanac.factValue('b')
		.then(b => 
		{almanac.factValue('c')
			.then(c => 
			{event.params.k = b + c;
			k+=event.params.k;

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
			k+=event.params.k;
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
			fact : 'a'
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
			fact : 'a'
		}
	}
})

//add rules to engine
engine.addRule(rule)
engine.addRule(rule2)
engine.addRule(rule3)

//run the engine



i = 56

//facts can be defined using dynamic parameter resolved at runtime.

for (i=0;i<9000;i++)
{
let facts = { a: 10, b : 9 , c : i}

//engine is an asynchronous process that uses promise mechanism

p.push(engine

.run(facts)
.then(events => { // run() returns events with truthy conditions
	//this will show in this promise that only one structure of data can be kept at the same time for event.
	events.map(event => {console.log("returned value " + JSON.stringify(event)); 	
	})
	console.log("k " + k);
}))

}

  
  
console.log("##### final p " + JSON.stringify(p) +
		Promise.resolve(p).then(console.log('promise solved'))
		


)



	


