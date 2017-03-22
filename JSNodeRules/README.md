continuing after groovy to investigate some ways to implement rules engines, trying to move away from scripting like format and moving towards a json approach

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

### Examples of Rules



## npm install nools


