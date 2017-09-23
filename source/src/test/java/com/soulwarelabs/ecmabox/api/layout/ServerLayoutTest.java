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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(ServerLayout.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(ServerType.class)
public class ServerLayoutTest {

    private ServerType typeMock;

    @Before
    public void prepareMocks() {
        typeMock = PowerMockito.mock(ServerType.class);
    }

    @Test
    public void createNewValidInstance() throws Exception {
        final ServerLayout layout = new ServerLayout(typeMock);
        Assert.assertEquals(typeMock, layout.getType());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() throws Exception {
        new ServerLayout(null);
    }
}
