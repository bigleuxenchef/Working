import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import GroovyExamples.Example

Binding binding = new Binding()
binding.setVariable("example",new Example( this ))

GroovyScriptEngine gse = new GroovyScriptEngine( [ '.' ] as String[] )
gse.run( "src/GroovyExamples/foo.groovy", binding )