package javainterop

import java.util.Calendar
def builder = new ClassBuilder(this.class.classLoader)
builder.setName("MyClass");

builder.addImport(Calendar)

builder.addField('field1', Integer)
builder.addField('field2', Integer)

builder.addMethod('sum') { field1 + field2 }

builder.addMethod('product') { field1 * field2 }

builder.addMethod('testCalendar') {
    println Calendar.getInstance().getTime()
}

Class myClass = builder.getCreatedClass()
def myInstance = myClass.newInstance()

myInstance.field1 = 1
myInstance.field2 = 2

println myInstance.sum()
println myInstance.product()

myInstance.setField2(1500)
println myInstance.getField2()

myInstance.testCalendar()