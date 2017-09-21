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
package com.soulwarelabs.ecmabox.core.dependency.broker.local;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(ClasspathResourceScriptBroker.class)
@RunWith(PowerMockRunner.class)
public class ClasspathResourceScriptBrokerTest {

    @Mock
    private String resourceMock;

    @Test(expected = UnsupportedOperationException.class)
    public void receiveScript() throws Exception {
        final ClasspathResourceScriptBroker broker = new ClasspathResourceScriptBroker(null);
        broker.receive(resourceMock);
    }
}
