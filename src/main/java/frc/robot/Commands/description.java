package frc.robot.Commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//interfacezinha de anotação
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface description{
        String identifier();
}
