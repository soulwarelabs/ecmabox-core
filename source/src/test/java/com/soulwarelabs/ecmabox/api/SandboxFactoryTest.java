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
package com.soulwarelabs.ecmabox.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.api.layout.LayoutBuilder;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(SandboxFactory.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({SandboxFactory.class, LayoutBuilder.class})
public class SandboxFactoryTest {

    private SandboxFactory factory;

    @Mock
    private LayoutBuilder layoutBuilderMock;

    @Before
    public void prepareFactory() throws Exception {
        factory = new SandboxFactory() {

            @Override
            public Sandbox create(Layout layout) {
                return null;
            }
        };
    }

    @Before
    public void prepareMocks() throws Exception {
        PowerMockito
                .mockStatic(LayoutBuilder.class);
        PowerMockito
                .whenNew(LayoutBuilder.class)
                .withNoArguments()
                .thenReturn(layoutBuilderMock);
    }

    @Test
    public void invokeLayoutBuilderConstructor() {
        final LayoutBuilder builder = factory.layoutBuilder();
        Assert.assertSame(layoutBuilderMock, builder);
        PowerMockito.verifyNew(LayoutBuilder.class);
    }
}
