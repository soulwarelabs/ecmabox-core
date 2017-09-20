/*
   Copyright 2017 Ilia Gubarev

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.soulwarelabs.ecmabox.api.layout;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.api.dependecy.Dependency;
import com.soulwarelabs.ecmabox.api.dependecy.DependencyResolver;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(LayoutBuilder.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        BrowserLayout.class,
        Dependency.class,
        DependencyResolver.class,
        EnvironmentType.class,
        LogLayout.class,
        ServerLayout.class,
        Layout.class,
        LayoutBuilder.class})
public class LayoutBuilderTest {

    @Mock
    private BrowserLayout browserLayoutMock;

    @Mock
    private DependencyResolver customDependencyResolverMock;

    @Mock
    private Dependency dependencyMock;

    @Mock
    private EnvironmentType environmentTypeMock;

    @Mock
    private LogLayout logLayoutMock;

    @Mock
    private ServerLayout serverLayoutMock;

    @Mock
    private Layout layoutMock;

    private LayoutBuilder builder;

    @Before
    public void prepareBuilder() {
        this.builder = new LayoutBuilder();
    }

    @Before
    public void prepareLayoutMock() throws Exception {
        PowerMockito
                .whenNew(Layout.class)
                .withAnyArguments()
                .thenReturn(layoutMock);
    }

    @Test(expected = NullPointerException.class)
    public void failToAddNullDependency() throws Exception {
        builder.dependency(null);
    }

    @Test
    public void invokeLayoutConstructorWithCustomProperties() throws Exception {
        final Layout layout = builder
                .browserLayout(browserLayoutMock)
                .customDependencyResolver(customDependencyResolverMock)
                .dependency(dependencyMock)
                .environmentType(environmentTypeMock)
                .logLayout(logLayoutMock)
                .serverLayout(serverLayoutMock)
                .build();
        Assert.assertEquals(layoutMock, layout);
        PowerMockito
                .verifyNew(Layout.class)
                .withArguments(
                        ArgumentMatchers.same(browserLayoutMock),
                        ArgumentMatchers.same(customDependencyResolverMock),
                        ArgumentMatchers.eq(Collections.singletonList(dependencyMock)),
                        ArgumentMatchers.same(environmentTypeMock),
                        ArgumentMatchers.same(logLayoutMock),
                        ArgumentMatchers.same(serverLayoutMock)
                );
    }
}
