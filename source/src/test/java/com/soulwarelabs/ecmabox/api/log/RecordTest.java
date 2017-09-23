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

import java.time.Instant;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Record.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({RecordLevel.class})
public class RecordTest {

    @Mock
    private RecordLevel levelMock;

    private String content = "some test log content";
    private Instant when = Instant.now();
    private Instant whenOlder = when.minusMillis(42);

    @Test
    public void createAndCompareTwoInstances() throws Exception {
        final Record record = new Record(
                content,
                levelMock,
                when
        );
        final Record recordOlder = new Record(
                content,
                levelMock,
                whenOlder
        );
        Assert.assertTrue(recordOlder.compareTo(record) < 0);
    }

    @Test
    public void createNewValidInstance() throws Exception {
        final Record record = new Record(
                content,
                levelMock,
                when
        );
        Assert.assertEquals(content, record.getContent());
        Assert.assertEquals(levelMock, record.getLevel());
        Assert.assertEquals(when, record.getWhen());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullContent() throws Exception {
        new Record(
                null,
                levelMock,
                when
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullLevel() throws Exception {
        new Record(
                content,
                null,
                when
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullWhen() throws Exception {
        new Record(
                content,
                levelMock,
                null
        );
    }
}
