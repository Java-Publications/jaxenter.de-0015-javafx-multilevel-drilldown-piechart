package org.rapidpm.demo.jaxenter.blog0015;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ts40 on 20.12.13.
 */

@Qualifier
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface Blog0015 {
}
