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
package com.soulwarelabs.ecmabox.core.cache;

import java.util.Optional;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(BaseCache.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({CacheLayout.class})
public class BaseCacheTest {

    @Mock
    private Object keyMock;

    @Mock
    private CacheLayout layoutMock;

    @Mock
    private Function resolverMock;

    @Mock
    private Object valueMock;

    @Test
    public void createNewValidInstance() throws Exception {
        final BaseCache cache = createNewInstance(layoutMock);
        Assert.assertSame(layoutMock, cache.getLayout());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullLayout() throws Exception {
        createNewInstance(null);
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("unchecked")
    public void failToGetValueWithNullKey() throws Exception {
        final BaseCache cache = createNewInstance(layoutMock);
        cache.getOrResolve(null, resolverMock);
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("unchecked")
    public void failToGetValueWithNullResolver() throws Exception {
        final BaseCache cache = createNewInstance(layoutMock);
        cache.getOrResolve(keyMock, null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getValue() throws Exception {
        final BaseCache cache = createNewInstance(layoutMock);
        final Optional value = cache.getOrResolve(keyMock, resolverMock);
        Assert.assertSame(valueMock, value.orElse(null));
    }

    private BaseCache createNewInstance(final CacheLayout layout) {
        return new BaseCache(layout) {
            @Override
            protected Object getOrResolveSpecific(Object key, Function resolver) {
                return valueMock;
            }
        };
    }
}
