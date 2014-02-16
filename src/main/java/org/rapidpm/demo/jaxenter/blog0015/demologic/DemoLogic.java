package org.rapidpm.demo.jaxenter.blog0015.demologic;


import org.rapidpm.demo.jaxenter.blog0015.CDINotMapped;

/**
 * Created by ts40 on 29.01.14.
 */
@CDINotMapped
public interface DemoLogic {
    public default String workOnString() {
        return "DemoLogicDefault";
    }
}
