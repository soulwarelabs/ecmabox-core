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
package com.soulwarelabs.ecmabox.api.layout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.api.log.RecordLevel;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(LogLayout.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({LogLayout.class, RecordLevel.class})
public class LogLayoutTest {

    private Boolean enabledMock;
    private RecordLevel levelMock;

    @Before
    public void prepareMocks() {
        enabledMock = PowerMockito.mock(Boolean.class);
        levelMock = PowerMockito.mock(RecordLevel.class);
    }

    @Test
    public void createNewValidInstance() throws Exception {
        final LogLayout layout = new LogLayout(
                enabledMock,
                levelMock
        );
        Assert.assertEquals(enabledMock, layout.isEnabled());
        Assert.assertEquals(levelMock, layout.getLevel());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullLevel() throws Exception {
        new LogLayout(
                enabledMock,
                null
        );
    }
}
