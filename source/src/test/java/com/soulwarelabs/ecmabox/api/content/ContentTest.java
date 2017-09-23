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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Content.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Content.class, ContentType.class})
public class ContentTest {

    private Object sampleValue = "Sample value";

    // TODO: add/verify unit tests

    @Test
    public void castValueOnDemand() throws Exception {
        final Double value = 42.0;
        final Content content = Content.of(value);
        Assert.assertEquals(value, content.getValueAsDouble());
    }

    @Test
    public void copyValueIfList() throws Exception {
        final List<Object> value = Collections.singletonList(new Object());
        final Content content = Content.of(value);
        Assert.assertEquals(value, content.getValue());
        Assert.assertFalse(value == content.getValue());
    }

    @Ignore
    @Test
    public void copyValueIfMap() throws Exception {
        // TODO: implement this test
    }

    @Test
    public void createNewValidInstance() throws Exception {
        final Content content = createNewInstanceWithSampleValue();
        Assert.assertEquals(ContentType.STRING, content.getType());
        Assert.assertEquals(sampleValue, content.getValue());
    }

    @Test
    public void createNewInstanceWithObjectTypeAndNullValue() throws Exception {
        Content.of(null);
    }

    @Test(expected = ClassCastException.class)
    public void failToCastValueOnDemand() throws Exception {
        final Double value = 42.0;
        final Content content = Content.of(value);
        Assert.assertEquals("42.0", content.getValueAsString());
    }

    @Test
    public void validUndefinedInstance() throws Exception {
        final Content content = Content.undefined();
        Assert.assertEquals(ContentType.UNDEFINED, content.getType());
        Assert.assertNull(content.getValue());
    }

    @Ignore
    @Test
    public void validNullInstance() throws Exception {
        // TODO: implement this test
    }

    private Content createNewInstanceWithSampleValue() {
        return Content.of(sampleValue);
    }
}
