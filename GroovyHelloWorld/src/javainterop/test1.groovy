package javainterop

static Class getDynamicClass(ClassLoader loader) {
    def builder = new ClassBuilder(new GroovyClassLoader(loader))
    builder.setName('AnotherClass')
    builder.addField('field1', Integer)
    builder.addField('field2', Integer)
    builder.addMethod('sum') { return field1 + field2 }

    return builder.getCreatedClass()
}