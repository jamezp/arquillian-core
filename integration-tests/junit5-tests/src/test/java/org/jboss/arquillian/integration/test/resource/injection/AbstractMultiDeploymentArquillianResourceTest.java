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

import java.net.URI;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.integration.test.common.TestEnvironment;
import org.jboss.arquillian.integration.test.common.app.Greeter;
import org.jboss.arquillian.junit5.container.annotation.ArquillianTest;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:jperkins@ibm.com">James R. Perkins</a>
 */
@ArquillianTest
abstract class AbstractMultiDeploymentArquillianResourceTest {
    static final String DEPLOYMENT_NAME_1 = "injection-one";
    static final String DEPLOYMENT_NAME_2 = "injection-two";

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    protected URL url1;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    protected URI uri1;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    protected URL url2;

    @ArquillianResource
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    protected URI uri2;

    @Deployment(name = DEPLOYMENT_NAME_1)
    public static WebArchive createDeployment1() {
        return ShrinkWrap.create(WebArchive.class, DEPLOYMENT_NAME_1 + ".war")
            .addClasses(Greeter.class, AbstractMultiDeploymentArquillianResourceTest.class, TestEnvironment.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Deployment(name = DEPLOYMENT_NAME_2)
    public static WebArchive createDeployment2() {
        return ShrinkWrap.create(WebArchive.class, DEPLOYMENT_NAME_2 + ".war")
            .addClasses(Greeter.class, AbstractMultiDeploymentArquillianResourceTest.class, TestEnvironment.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkUrl1() {
        Assertions.assertNotNull(url1, "The URL should have been injected");
        Assertions.assertEquals(TestEnvironment.protocol(), url1.getProtocol());
        checkHost(url1.getHost());
        Assertions.assertEquals(TestEnvironment.port(), url1.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_1 + "/", url1.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkParameterUrl1(@ArquillianResource final URL url) {
        Assertions.assertNotNull(url, "The URL should have been injected");
        Assertions.assertEquals(TestEnvironment.protocol(), url.getProtocol());
        checkHost(url.getHost());
        Assertions.assertEquals(TestEnvironment.port(), url.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_1 + "/", url.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkUri1() {
        Assertions.assertNotNull(uri1, "The URI should have been injected");
        checkHost(uri1.getHost());
        Assertions.assertEquals(TestEnvironment.port(), uri1.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_1 + "/", uri1.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_1)
    public void checkParameterUri1(@ArquillianResource final URI uri) {
        Assertions.assertNotNull(uri, "The URI should have been injected");
        checkHost(uri.getHost());
        Assertions.assertEquals(TestEnvironment.port(), uri.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_1 + "/", uri.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkUrl2() {
        Assertions.assertNotNull(url2, "The URL should have been injected");
        Assertions.assertEquals(TestEnvironment.protocol(), url2.getProtocol());
        checkHost(url2.getHost());
        Assertions.assertEquals(TestEnvironment.port(), url2.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_2 + "/", url2.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkParameterUrl2(@ArquillianResource final URL url) {
        Assertions.assertNotNull(url, "The URL should have been injected");
        Assertions.assertEquals(TestEnvironment.protocol(), url.getProtocol());
        checkHost(url.getHost());
        Assertions.assertEquals(TestEnvironment.port(), url.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_2 + "/", url.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkUri2() {
        Assertions.assertNotNull(uri2, "The URI should have been injected");
        checkHost(uri2.getHost());
        Assertions.assertEquals(TestEnvironment.port(), uri2.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_2 + "/", uri2.getPath());
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_NAME_2)
    public void checkParameterUri2(@ArquillianResource final URI uri) {
        Assertions.assertNotNull(uri, "The URI should have been injected");
        checkHost(uri.getHost());
        Assertions.assertEquals(TestEnvironment.port(), uri.getPort());
        Assertions.assertEquals("/" + DEPLOYMENT_NAME_2 + "/", uri.getPath());
    }

    protected void checkHost(final String host) {
        // localhost and 127.0.0.1 should be treated as the same
        final String expectedHost = TestEnvironment.host();
        if ("127.0.0.1".equals(expectedHost)) {
            Assertions.assertEquals(expectedHost, host.replace("localhost", "127.0.0.1"));
        } else if ("localhost".equals(expectedHost)) {
            Assertions.assertEquals(expectedHost, host.replace("127.0.0.1", "localhost"));
        } else {
            Assertions.assertEquals(expectedHost, host);
        }
    }
}
