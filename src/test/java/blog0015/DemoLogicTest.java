package org.rapidpm.demo.jaxenter.blog0015;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapidpm.demo.jaxenter.blog0015.demologic.DemoLogic;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by ts40 on 29.01.14.
 */
@RunWith(Arquillian.class)
public class DemoLogicTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.rapidpm")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Inject Instance<DemoLogic> demoLogicInstance;
    @Inject Context context;

    @Test
    public void testNotNull() throws Exception {
        Assert.assertNotNull(demoLogicInstance);
        final DemoLogic demoLogic = demoLogicInstance
//                .select(new AnnotationLiteral<Blog0015>() {})
                .get();
        Assert.assertNotNull(demoLogic);
        Assert.assertTrue(demoLogic.workOnString().equals("DemoLogicDefault"));

        context.defaultImpl = false;
        final DemoLogic demoLogicKotlin = demoLogicInstance
//                .select(new AnnotationLiteral<Blog0015>() {})
                .get();
        Assert.assertNotNull(demoLogicKotlin);
        Assert.assertTrue(demoLogicKotlin.workOnString().equals("DemoLogicKotlin"));

    }


}
