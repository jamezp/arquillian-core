/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2026 IBM, and individual contributors
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

package org.jboss.arquillian.integration.test.resource.injection;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit5.container.annotation.ArquillianTest;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

/**
 * @author <a href="mailto:jperkins@ibm.com">James R. Perkins</a>
 */
@ArquillianTest
public class InContainerMultiDeploymentArquillianResourceTest extends AbstractMultiDeploymentArquillianResourceTest {

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    protected Context context1;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    protected InitialContext initialContext1;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    protected Context context2;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    protected InitialContext initialContext2;

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkContext1() throws Exception {
        Assertions.assertNotNull(context1, "The Context should have been injected");
        final Object bm = context1.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    @DisabledIfSystemProperty(named = "javax.naming.Context.parameter", matches = "skip")
    public void checkContextParameter1(@ArquillianResource final Context context) throws Exception {
        Assertions.assertNotNull(context, "The Context should have been injected");
        final Object bm = context.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkInitialContext1() throws Exception {
        Assertions.assertNotNull(initialContext1, "The InitialContext should have been injected");
        final Object bm = initialContext1.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    @DisabledIfSystemProperty(named = "javax.naming.Context.parameter", matches = "skip")
    public void checkInitialContextParameter1(@ArquillianResource final InitialContext initialContext) throws Exception {
        Assertions.assertNotNull(initialContext, "The InitialContext should have been injected");
        final Object bm = initialContext.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkContext2() throws Exception {
        Assertions.assertNotNull(context2, "The Context should have been injected");
        final Object bm = context2.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    @DisabledIfSystemProperty(named = "javax.naming.Context.parameter", matches = "skip")
    public void checkContextParameter2(@ArquillianResource final Context context) throws Exception {
        Assertions.assertNotNull(context, "The Context should have been injected");
        final Object bm = context.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkInitialContext2() throws Exception {
        Assertions.assertNotNull(initialContext2, "The InitialContext should have been injected");
        final Object bm = initialContext2.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    @DisabledIfSystemProperty(named = "javax.naming.Context.parameter", matches = "skip")
    public void checkInitialContextParameter2(@ArquillianResource final InitialContext initialContext) throws Exception {
        Assertions.assertNotNull(initialContext, "The InitialContext should have been injected");
        final Object bm = initialContext.lookup("java:comp/BeanManager");
        Assertions.assertNotNull(bm);
    }
}
