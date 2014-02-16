package org.rapidpm.demo.jaxenter.blog0015.demologic;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ts40 on 29.01.14.
 */
@Qualifier
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface DefaultImpl {
}
