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
@UnitTest(Discoverables.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Discoverables.class})
public class DiscoverablesTest {

    @Before
    public void prepare() throws Exception {

    }

    @Ignore
    @Test
    public void discoverNoTypes() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void discoverOneTypes() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void discoverSeveralTypes() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failOnNullPackageName() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failOnNullTag() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void useDefaultPackageName() throws Exception {
        // TODO: implement this test
    }
}
