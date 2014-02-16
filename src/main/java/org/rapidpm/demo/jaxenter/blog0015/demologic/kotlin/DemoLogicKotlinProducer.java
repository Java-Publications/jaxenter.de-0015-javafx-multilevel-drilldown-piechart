package org.rapidpm.demo.jaxenter.blog0015.demologic.kotlin;

import org.rapidpm.demo.jaxenter.blog0015.ManagedInstanceCreator;
import org.rapidpm.demo.jaxenter.blog0015.demologic.DemoLogic;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by ts40 on 29.01.14.
 */
public class DemoLogicKotlinProducer {

    @Inject ManagedInstanceCreator creator;
    @Inject DemoLogicKotlin kotlin;

    @Produces @KotlinImpl
    public DemoLogic create(){
        final DemoLogic logic = new DemoLogicKotlinWrapper();
        return creator.activateCDI(logic);
    }
}
