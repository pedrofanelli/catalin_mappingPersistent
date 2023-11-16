
/**
 * 
 * In a Spring Boot project, the package-info.java file is typically used to define package-level 
 * annotations and documentation. This file can be placed in any package to provide additional information 
 * about the package and its contents.
 * 
 * @author peter
 *
 */
@org.hibernate.annotations.GenericGenerator(
  name = "ID_GENERATOR",
  //strategy = "enhanced-sequence",
  type = org.hibernate.id.enhanced.SequenceStyleGenerator.class, // cambió, ahora debemos setearlo de esta manera
  parameters = {
     @org.hibernate.annotations.Parameter(
        name = "sequence_name",
        value = "SETTED_BY_ME_SEQUENCE"
     ),
     @org.hibernate.annotations.Parameter(
        name = "initial_value",
        value = "1616"
     )
})
package com.example.demo.generator;