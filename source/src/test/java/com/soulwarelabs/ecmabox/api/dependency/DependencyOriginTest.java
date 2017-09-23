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
package com.soulwarelabs.ecmabox.api.dependency;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(DependencyOrigin.class)
@RunWith(PowerMockRunner.class)
public class DependencyOriginTest {

    private List<DependencyOrigin> elements;

    @Before
    public void prepareElements() {
        elements = Arrays.asList(
                DependencyOrigin.CDN,
                DependencyOrigin.NPM
        );
    }

    @Test
    public void doesNotContainExtraElements() throws Exception {
        Assert.assertEquals(elements.size(), DependencyOrigin.values().length);
    }
}
