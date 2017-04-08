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
require('os')
let Engine = require('json-rules-engine').Engine
let Rule = require('json-rules-engine').Rule


/**
 * Setup a new engine
 */
let engine = new Engine()

/**
 * Create a rule
 */

// if (a > b) a + c else b + c
var a = 8;
var b = 9;
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
      k : { fact: 'b'}
    }
  },
  onFailure: function (event, almanac) {console.log('failure ' + event.params.data + 'k ' + JSON.stringify(event.params.k))},
  onSuccess: function (event, almanac)
     {
	  let  aa = almanac.factValue('a').then(a => {return 9}).then(function (done){return done});
	  let  bb = almanac.factValue('b').then(a => {return 7}).then(function (done){return done});
	  let cc = aa + bb
     // Promise.all(aa,bb,cc).prototype.catch();
	  console.log('aa ' + aa +'bb '+ bb +'cc ' + cc);
	  almanac.addRuntimeFact('k',almanac.factValue('a').then(a => {return a}))
	  console.log('success ' + event.params.data + almanac.factValue('b').then(info => {console.log('info ' + info); return info; }))
	  console.log('event ' + almanac.factValue('k').then(info => {console.log('** info ' + info); return info; }))
	  
	  
	//  almanac.addRuntimeFact('exitvalue',event.params.k)
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

// add rule to engine
engine.addRule(rule)
engine.addRule(rule2)
engine.addRule(rule3)




// run the engine



i = 7

// facts can be defined using dynamic paramter resolved at runtime.

let facts = { a: i, b : 6 , c : i}
// engine is an asynchronous process that uses promise mechanism
engine
  .run(facts).then(events => { // run() returns events with truthy conditions
  events.map(event => {console.log("returned value " + JSON.stringify(event)); 
  return k+=event.params.k;})
  })
  
  
  //.then(events => { // run() returns events with truthy conditions
   // events.map(event => {console.log("returned value " + event.type + " k " ); 
    //return k+=event.params.k;})
 // })
/*
		tmp.then(function (fulfilled) {
    // yay, you got a new phone
    console.log('fullfiled' + JSON.stringify(fulfilled));
		})
.catch(function (error) {
    // ops, mom don't buy it
    console.log(error.message);
})*/
  


  
