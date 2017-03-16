import groovy.lang.Binding
import groovy.util.GroovyScriptEngine

Binding binding = new Binding()
binding.example = new Example( this )
GroovyScriptEngine gse = new GroovyScriptEngine( [ '.' ] as String[] )
gse.run( "foo.groovy", binding )