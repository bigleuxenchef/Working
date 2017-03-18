// Rumi march 2017
// this script will be consumed by GroovyShell through Java Script/Groovy API
// this script illustrate how a POJO can b injected in a groovy script to provide the whole set of context that goes with it
// T is an object being binded to the script, for this demo T is a Trade java object.
if (displayon)
	{println " T.amount1: ${T.amount1} - T.amount2: ${T.amount2} - c: {$c} "
		T.ret = 150
		
	}
if (T.amount1 > T.amount2) T.amount1 + c else T.amount2 + c


