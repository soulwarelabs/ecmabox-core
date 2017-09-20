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
package com.soulwarelabs.ecmabox.api.log;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(RecordLevel.class)
@RunWith(PowerMockRunner.class)
public class RecordLevelTest {

    private List<RecordLevel> elements;

    @Before
    public void prepareTypes() {
        elements = Arrays.asList(
                RecordLevel.TRACE,
                RecordLevel.DEBUG,
                RecordLevel.INFO,
                RecordLevel.WARN,
                RecordLevel.ERROR,
                RecordLevel.FATAl
        );
    }

    @Test
    public void doesNotContainExtraElements() {
        Assert.assertEquals(elements.size(), RecordLevel.values().length);
    }

    @Test
    public void precedencesAreInOrder() {
        RecordLevel previous = null;
        for (final RecordLevel level : elements) {
            if (previous != null) {
                Assert.assertTrue(previous.lowerThan(level));
            }
            previous = level;
        }
    }
}