package org.jboss.arquillian.junit5;

import org.jboss.arquillian.core.api.annotation.ApplicationScoped;
import org.jboss.arquillian.core.spi.ManagerBuilder;
import org.jboss.arquillian.test.impl.EventTestRunnerAdaptor;
import org.jboss.arquillian.core.impl.loadable.LoadableExtensionLoader;

/**
 * Extend the default EventTestRunnerAdaptor to allow for setting method parameters.
 */
public class JunitEventTestRunnerAdaptor extends EventTestRunnerAdaptor {

    public static JunitEventTestRunnerAdaptor build() {
        ManagerBuilder builder = ManagerBuilder.from()
            .extension(LoadableExtensionLoader.class);
        return new JunitEventTestRunnerAdaptor(builder);
    }

    JunitEventTestRunnerAdaptor(ManagerBuilder builder) {
        super(builder);
    }

    void setMethodParameters(MethodParameters methodParameters) {
        getManager().bind(ApplicationScoped.class, MethodParameters.class, methodParameters);
    }
}
