/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009 Red Hat Inc. and/or its affiliates and other contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.container.impl;

import java.util.logging.Logger;
import org.jboss.arquillian.container.spi.Container;
import org.jboss.arquillian.container.spi.ServerKillProcessor;

/**
 * DefaultServerKillProcessor
 *
 * @author <a href="mailto:mgencur@redhat.com">Martin Gencur</a>
 */
public class DefaultServerKillProcessor implements ServerKillProcessor {
    private final Logger log = Logger.getLogger(DefaultServerKillProcessor.class.getName());

    @Override
    public void kill(Container container) throws Exception {
        log.warning("Triggering default implementation of a server kill() command => using stop() internally");
        container.getDeployableContainer().stop();
    }
}
