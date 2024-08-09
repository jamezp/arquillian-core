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

import org.jboss.arquillian.core.api.Event;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.core.spi.ServiceLoader;
import org.jboss.arquillian.test.spi.TestEnricher;
import org.jboss.arquillian.test.spi.event.enrichment.EnrichmentEvent;

/**
 * The observer used to process method parameters provided by Arquillian.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class MethodParameterObserver {

    @Inject
    private Instance<ServiceLoader> serviceLoader;

    @Inject
    private Event<EnrichmentEvent> enrichmentEvent;

    /**
     * Updates the stored {@link MethodParameters} for method parameters which can be provided by Arquillian.
     *
     * @param event the fired event
     */
    public void injectParameters(@Observes MethodParameterInjectionEvent event) {
        event.enrichParameters(serviceLoader.get().all(TestEnricher.class), enrichmentEvent);
    }
}
