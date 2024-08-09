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

package org.jboss.arquillian.junit5.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to qualify a parameter and tell Arquillian not to provide the parameter, even if Arquillian could produce it.
 * This is particularly useful for in-container tests where a JUnit {@link org.junit.jupiter.api.extension.ParameterResolver}
 * may be responsible for supplying the method parameter.
 *
 * <pre>
 *     &#64;Test
 *     public void checkConnection(&#64;ArquillianResource final URI uri, &#64;Vetoed &#64;TempDir final Path path) {
 *         ...
 *     }
 * </pre>
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Vetoed {
}
