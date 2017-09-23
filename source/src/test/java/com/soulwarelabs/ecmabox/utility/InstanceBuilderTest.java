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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@Ignore
@UnitTest(InstanceBuilder.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({InstanceBuilder.class})
public class InstanceBuilderTest {

    @Before
    public void prepare() throws Exception {

    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failOnNullInstantiationMethod() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failOnNullInstantiationType() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void handleInstantiationException() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void useCustomProperties() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void useDefaultProperties() throws Exception {
        // TODO: implement this test
    }
}
