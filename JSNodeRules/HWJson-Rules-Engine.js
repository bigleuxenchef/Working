//'use strict'

/*
 * This is the hello-world example from the README.
 *
 * Usage:
 *   node ./examples/01-hello-world.js
 *
 * For detailed output:
 *   DEBUG=json-rules-engine node ./examples/01-hello-world.js
 */

require('colors')
require('json-rules-engine')
//let Engine = require('node_modules/json-rules-engine/dist').Engine
//let Rule = require('node_modules/json-rules-engine/dist').Rule

// rumi march 22 fix bug in the sample 
let Engine = require('json-rules-engine').Engine
let Rule = require('json-rules-engine').Rule


/**
 * Setup a new engine
 */
let engine = new Engine()

/**
 * Create a rule
 */
let rule = new Rule({
  // define the 'conditions' for when "hello world" should display
  conditions: {
    all: [{
      fact: 'displayMessage',
      operator: 'equal',
      value: true
    }]
  },
  // define the 'event' that will fire when the condition evaluates truthy
  event: {
    type: 'message',
    params: {
      data: 'hello-world!'
    }
  }
})

// add rule to engine
engine.addRule(rule)

/**
 * Define a 'displayMessage' as a constant value
 * Fact values do NOT need to be known at engine runtime; see the
 * 03-dynamic-facts.js example for how to pull in data asynchronously during runtime
 */
let facts = { displayMessage: true }

// run the engine
engine
  .run(facts)
  .then(triggeredEvents => { // engine returns a list of events with truthy conditions
    triggeredEvents.map(event => console.log(event.params.data.green))
  })
  .catch(console.log)

/*
 * OUTPUT:
 *
 * hello-world!
 */