package javainterop2

import org.codehaus.groovy.control.CompilerConfiguration

def config = new CompilerConfiguration()                                
config.scriptBaseClass = 'MyBaseClass'                                  
def shell = new GroovyShell(this.class.classLoader, config)             
shell.evaluate """
    setName 'Judith'                                                    
    greet()
"""