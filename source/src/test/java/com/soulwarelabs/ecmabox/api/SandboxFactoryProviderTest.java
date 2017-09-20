package com.soulwarelabs.ecmabox.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(SandboxFactoryProvider.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(SandboxFactoryProvider.class)
public class SandboxFactoryProviderTest {

    private SandboxConfiguration configurationMock;

    @Before
    public void prepareMocks() {
        configurationMock = PowerMockito.mock(SandboxConfiguration.class);
    }

    @Test(expected = NullPointerException.class)
    public void failProvideFactoryWithNullConfiguration() {
        SandboxFactoryProvider.factory(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void provideFactoryWithCustomConfiguration() {
        SandboxFactoryProvider.factory(configurationMock);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void provideFactoryWithDefaultConfiguration() {
        SandboxFactoryProvider.factory();
    }
}
