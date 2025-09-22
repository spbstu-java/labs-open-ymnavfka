package HeroMethods;

import java.lang.annotation.*;

/**
 * Аннотация с целочисленным параметром.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Repeat {
    int value();
}