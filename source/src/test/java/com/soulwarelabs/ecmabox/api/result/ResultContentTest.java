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
package com.soulwarelabs.ecmabox.api.result;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(ResultContent.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({ResultContent.class, ResultContentType.class})
public class ResultContentTest {

    private ResultContentType typeMock;
    private Object valueMock;

    @Before
    public void prepareMocks() {
        typeMock = PowerMockito.mock(ResultContentType.class);
        valueMock = PowerMockito.mock(Object.class);
    }

    @Test
    public void castValueOnDemand() {
        final Double value = 42.0;
        final ResultContent content = ResultContent.of(ResultContentType.NUMBER, value);
        Assert.assertEquals(value,content.getValueAs(Double.class));
    }

    @Test
    public void copyValueIfList() {
        final List<Object> value = Collections.singletonList(new Object());
        final ResultContent content = ResultContent.of(ResultContentType.ARRAY, value);
        Assert.assertEquals(value,content.getValue());
        Assert.assertFalse(value == content.getValue());
    }

    @Test
    public void createNewValidInstance() {
        final ResultContent content = createNewInstanceWithMocks();
        Assert.assertEquals(typeMock, content.getType());
        Assert.assertEquals(valueMock, content.getValue());
    }

    @Test
    public void createNewInstanceWithObjectTypeAndNullValue() {
        ResultContent.of(ResultContentType.OBJECT, null);
    }

    @Test
    public void createNewInstanceWithUndefinedTypeAndNullValue() {
        ResultContent.of(ResultContentType.UNDEFINED, null);
    }

    @Test(expected = ClassCastException.class)
    public void failToCastValueOnDemand() {
        final Double value = 42.0;
        final ResultContent content = ResultContent.of(ResultContentType.NUMBER, value);
        Assert.assertEquals("42.0", content.getValueAs(String.class));
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() {
        ResultContent.of(null, valueMock);
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullValue() {
        ResultContent.of(typeMock, null);
    }

    @Test
    public void receiveUndefinedInstance() {
        final ResultContent content = ResultContent.undefined();
        Assert.assertEquals(ResultContentType.UNDEFINED, content.getType());
        Assert.assertNull(content.getValue());
    }

    private ResultContent createNewInstanceWithMocks() {
        return ResultContent.of(typeMock, valueMock);
    }
}
