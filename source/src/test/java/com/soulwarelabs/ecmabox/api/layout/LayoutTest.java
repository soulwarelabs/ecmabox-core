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
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolver;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Layout.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        BrowserLayout.class,
        Dependency.class,
        DependencyResolver.class,
        EnvironmentType.class,
        LogLayout.class,
        ServerLayout.class
})
public class LayoutTest {

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

    private List<Dependency> dependencies;

    @Before
    public void prepareMocks() {
        dependencies = Collections.singletonList(dependencyMock);
    }

    @Test
    public void copyDependenciesOnGet() throws Exception {
        final Layout layout = new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                dependencies,
                environmentTypeMock,
                logLayoutMock,
                serverLayoutMock
        );
        Assert.assertEquals(dependencies, layout.getDependencies());
        Assert.assertFalse(dependencies == layout.getDependencies());
    }

    @Test
    public void createNewValidInstance() throws Exception {
        final Layout layout = new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                dependencies,
                environmentTypeMock,
                logLayoutMock,
                serverLayoutMock
        );
        Assert.assertEquals(browserLayoutMock, layout.getBrowserLayout());
        Assert.assertEquals(customDependencyResolverMock, layout.getCustomDependencyResolver());
        Assert.assertEquals(dependencies, layout.getDependencies());
        Assert.assertEquals(environmentTypeMock, layout.getEnvironmentType());
        Assert.assertEquals(logLayoutMock, layout.getLogLayout());
        Assert.assertEquals(serverLayoutMock, layout.getServerLayout());
    }

    @Test
    public void createNewInstanceWithNullResolver() throws Exception {
        final Layout layout = new Layout(
                browserLayoutMock,
                null,
                dependencies,
                environmentTypeMock,
                logLayoutMock,
                serverLayoutMock
        );
        Assert.assertNotNull(layout);
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullBrowserLayout() throws Exception {
        new Layout(
                null,
                customDependencyResolverMock,
                dependencies,
                EnvironmentType.BROWSER,
                logLayoutMock,
                serverLayoutMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullDependencies() throws Exception {
        new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                null,
                environmentTypeMock,
                logLayoutMock,
                serverLayoutMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullEnvironment() throws Exception {
        new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                dependencies,
                null,
                logLayoutMock,
                serverLayoutMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullLogging() throws Exception {
        new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                dependencies,
                environmentTypeMock,
                null,
                serverLayoutMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullServerLayout() throws Exception {
        new Layout(
                browserLayoutMock,
                customDependencyResolverMock,
                dependencies,
                EnvironmentType.SERVER,
                logLayoutMock,
                null
        );
    }
}
