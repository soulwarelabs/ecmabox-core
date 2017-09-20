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

@UnitTest(LogRecord.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({LogRecordType.class})
public class LogRecordTest {

    @Mock
    private LogRecordType typeMock;

    private String value = "some test log value";
    private Instant when = Instant.now();
    private Instant whenOlder = when.minusMillis(42);

    @Test
    public void createAndCompareTwoInstances() {
        final LogRecord record = new LogRecord(
                typeMock,
                value,
                when
        );
        final LogRecord recordOlder = new LogRecord(
                typeMock,
                value,
                whenOlder
        );
        Assert.assertTrue(recordOlder.compareTo(record) < 0);
    }

    @Test
    public void createNewValidInstance() {
        final LogRecord record = new LogRecord(
                typeMock,
                value,
                when
        );
        Assert.assertEquals(typeMock, record.getType());
        Assert.assertEquals(value, record.getValue());
        Assert.assertEquals(when, record.getWhen());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() {
        new LogRecord(
                null,
                value,
                when
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullValue() {
        new LogRecord(
                typeMock,
                null,
                when
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullWhen() {
        new LogRecord(
                typeMock,
                value,
                null
        );
    }
}
