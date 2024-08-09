/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2024 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.arquillian.junit5;

import java.lang.reflect.Method;
import java.util.Collection;

import org.jboss.arquillian.core.api.Event;
import org.jboss.arquillian.test.spi.LifecycleMethodExecutor;
import org.jboss.arquillian.test.spi.TestEnricher;
import org.jboss.arquillian.test.spi.event.enrichment.AfterEnrichment;
import org.jboss.arquillian.test.spi.event.enrichment.BeforeEnrichment;
import org.jboss.arquillian.test.spi.event.enrichment.EnrichmentEvent;
import org.jboss.arquillian.test.spi.event.suite.TestLifecycleEvent;

/**
 * An event which to load parameters which can be resolved from {@link TestEnricher#resolve(Method)}.
 * <p>
 * Note the order of the {@link TestEnricher} matters and the last enricher which can resolve the parameter will
 * be the supplier of the parameter value.
 * </p>
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class MethodParameterInjectionEvent extends TestLifecycleEvent {

    private final MethodParameters testParameterHolder;

    MethodParameterInjectionEvent(final Object testInstance, final Method testMethod, final MethodParameters testParameterHolder) {
        super(testInstance, testMethod, LifecycleMethodExecutor.NO_OP);
        this.testParameterHolder = testParameterHolder;
    }

    /**
     * Adds the parameters to the {@link MethodParameters} from the test enrichers.
     *
     * @param testEnrichers   the test enrichers used to resolve the values
     * @param enrichmentEvent the event to fire the {@link BeforeEnrichment} and {@link AfterEnrichment} events
     */
    void enrichParameters(final Collection<TestEnricher> testEnrichers, final Event<EnrichmentEvent> enrichmentEvent) {
        final Object testInstance = getTestInstance();
        final Method testMethod = getTestMethod();
        enrichmentEvent.fire(new BeforeEnrichment(testInstance, testMethod));
        for (TestEnricher enricher : testEnrichers) {
            final Object[] values = enricher.resolve(testMethod);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    testParameterHolder.add(i, values[i]);
                }
            }
        }
        enrichmentEvent.fire(new AfterEnrichment(testEnrichers, testMethod));
    }
}
