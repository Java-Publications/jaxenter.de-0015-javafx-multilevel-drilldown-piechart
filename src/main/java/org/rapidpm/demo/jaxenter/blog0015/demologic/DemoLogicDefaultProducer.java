package org.rapidpm.demo.jaxenter.blog0015.demologic;

import org.rapidpm.demo.jaxenter.blog0015.ManagedInstanceCreator;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by ts40 on 29.01.14.
 */
public class DemoLogicDefaultProducer {

    @Inject ManagedInstanceCreator creator;

    @Produces @DefaultImpl
    public DemoLogic create(){
        final DemoLogic logic = new DemoLogic(){};
        return creator.activateCDI(logic);
    }
}
