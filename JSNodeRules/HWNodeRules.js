/**
 * 
 */

var RuleEngine = require('node-rules');
 
//define the rules 
var rules = [{
    "condition": function(R) {
        R.when(this && (this.transactionTotal < 500));
    },
    "consequence": function(R) {
        this.result = false;
        R.stop();
    }
}];
 
//sample fact to run the rules on	 
var fact = {
    "name":"user4",
    "application":"MOB2",
    "transactionTotal":400,
    "cardType":"Credit Card",
};
 
//initialize the rule engine 
var R = new RuleEngine(rules);
 
//Now pass the fact on to the rule engine for results 
R.execute(fact,function(result){ 
 
    if(result.result) 
        console.log("Payment Accepted"); 
    else 
        console.log("Payment Rejected");
    
});