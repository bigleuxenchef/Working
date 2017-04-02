continuing on after groovy to investigate some ways to implement rules engines, trying to move away from scripting like format and moving towards a json approach

## npm install node-rules

### Examples of rules

```json
{
	"condition" : function(R) {
		R.when(this.transactionTotal < 500);
	},
	"consequence" : function(R) {
		this.result = false;
		R.stop();
	},
	"priority" : 4
}  
```


## npm install json-rules-engine
```json
{
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
}
```

### Notice
the engine if mutli threaded and cannot be invoked as a function that returns a value, this is difficult to run something like 

```
for (i=0;i<8;i++)
{
	
	let facts = { a: i, b : 6 , c : i}

 returnvalue = engine.run(facts) // this is going to return a "promise"
 
  
  }

```

### Examples of Rules



## npm install nools


