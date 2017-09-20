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

import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Result.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        Result.class,
        ResultContent.class,
        Invoice.class,
        Record.class,
        ResultType.class
})
public class ResultTest {

    private ResultContent contentMock;
    private Invoice invoiceMock;
    private List<Record> recordMocks;
    private ResultType typeMock;

    @Before
    public void prepareMocks() {
        contentMock = PowerMockito.mock(ResultContent.class);
        invoiceMock = PowerMockito.mock(Invoice.class);
        recordMocks = Collections.singletonList(PowerMockito.mock(Record.class));
        typeMock = PowerMockito.mock(ResultType.class);
    }

    @Test
    public void copyRecordsOnGet() {
        final Result result = createInstanceWithMocks();
        Assert.assertFalse(recordMocks == result.getRecords());
    }

    @Test
    public void createNewValidInstance() {
        final Result result = createInstanceWithMocks();
        Assert.assertEquals(contentMock, result.getContent());
        Assert.assertEquals(invoiceMock, result.getInvoice());
        Assert.assertEquals(recordMocks, result.getRecords());
        Assert.assertEquals(typeMock, result.getType());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullContent() {
        new Result(
                null,
                invoiceMock,
                recordMocks,
                typeMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullInvoice() {
        new Result(
                contentMock,
                null,
                recordMocks,
                typeMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullRecordList() {
        new Result(
                contentMock,
                invoiceMock,
                null,
                typeMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() {
        new Result(
                contentMock,
                invoiceMock,
                recordMocks,
                null
        );
    }

    private Result createInstanceWithMocks() {
        return new Result(
                contentMock,
                invoiceMock,
                recordMocks,
                typeMock
        );
    }
}
