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
package com.soulwarelabs.ecmabox.utility;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Strings.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Strings.class, ToStringBuilder.class})
public class StringsTest {

    @Mock
    private String stringMock;

    @Mock
    private Object objectMock;

    @Before
    public void prepareMocks() throws Exception {
        PowerMockito.mockStatic(ToStringBuilder.class);
        PowerMockito
                .when(ToStringBuilder.class, "reflectionToString", objectMock)
                .thenReturn(stringMock);
    }

    @Test
    public void createStringRepresentationOfObject() throws Exception {
        final String result = Strings.toString(objectMock);
        PowerMockito.verifyStatic(ToStringBuilder.class);
        Assert.assertSame(stringMock, result);
    }

    @Ignore
    @Test
    public void createStringFromNullObject() throws Exception {
        // TODO: implement this test
    }
}
