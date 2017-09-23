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

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(ContentType.class)
@RunWith(PowerMockRunner.class)
public class ContentTypeTest {

    private List<ContentType> elements;

    // TODO: add/verify unit tests

    @Before
    public void prepareElements() {
        elements = Arrays.asList(
                ContentType.ARRAY,
                ContentType.BOOLEAN,
                ContentType.FUNCTION,
                ContentType.NUMBER,
                ContentType.OBJECT,
                ContentType.STRING,
                ContentType.UNDEFINED
        );
    }

    @Ignore
    @Test
    public void detectTypeOfNullObject() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void failToDetectUnknownType() throws Exception {
        // TODO: implement this test
    }

    @Test
    public void doesNotContainExtraElements() throws Exception {
        Assert.assertEquals(elements.size(), ContentType.values().length);
    }

    @Ignore
    @Test
    public void elementArrayIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementBooleanIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementNumberIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementFunctionIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementObjectIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementStringIsValid() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void elementUndefinedIsValid() throws Exception {
        // TODO: implement this test
    }
}
