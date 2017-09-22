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
package com.soulwarelabs.ecmabox.api.content;

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

@UnitTest(Content.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Content.class, ContentType.class})
public class ContentTest {

    private ContentType typeMock;
    private Object valueMock;

    @Before
    public void prepareMocks() {
        typeMock = PowerMockito.mock(ContentType.class);
        valueMock = PowerMockito.mock(Object.class);
    }

    @Test
    public void castValueOnDemand() {
        final Double value = 42.0;
        final Content content = Content.of(ContentType.NUMBER, value);
        Assert.assertEquals(value,content.getValueAsDouble());
    }

    @Test
    public void copyValueIfList() {
        final List<Object> value = Collections.singletonList(new Object());
        final Content content = Content.of(ContentType.ARRAY, value);
        Assert.assertEquals(value,content.getValue());
        Assert.assertFalse(value == content.getValue());
    }

    @Test
    public void createNewValidInstance() {
        final Content content = createNewInstanceWithMocks();
        Assert.assertEquals(typeMock, content.getType());
        Assert.assertEquals(valueMock, content.getValue());
    }

    @Test
    public void createNewInstanceWithObjectTypeAndNullValue() {
        Content.of(ContentType.OBJECT, null);
    }

    @Test
    public void createNewInstanceWithUndefinedTypeAndNullValue() {
        Content.of(ContentType.UNDEFINED, null);
    }

    @Test(expected = ClassCastException.class)
    public void failToCastValueOnDemand() {
        final Double value = 42.0;
        final Content content = Content.of(ContentType.NUMBER, value);
        Assert.assertEquals("42.0", content.getValueAsString());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() {
        Content.of(null, valueMock);
    }

    @Test
    public void receiveUndefinedInstance() {
        final Content content = Content.undefined();
        Assert.assertEquals(ContentType.UNDEFINED, content.getType());
        Assert.assertNull(content.getValue());
    }

    private Content createNewInstanceWithMocks() {
        return Content.of(typeMock, valueMock);
    }
}
