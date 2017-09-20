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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.api.dependecy.Dependency;
import com.soulwarelabs.ecmabox.api.dependecy.DependencyResolver;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Sandbox layout configuration.
 *
 * @see BrowserLayout
 * @see Dependency
 * @see DependencyResolver
 * @see EnvironmentType
 * @see LogLayout
 * @see ServerLayout
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class Layout {

    private final BrowserLayout browserLayout;
    private final DependencyResolver customDependencyResolver;
    private final List<Dependency> dependencies;
    private final EnvironmentType environmentType;
    private final LogLayout logLayout;
    private final ServerLayout serverLayout;

    /**
     * Creates a new sandbox layout configuration.
     *
     * @param browserLayout browser layout configuration.
     * @param customDependencyResolver custom dependency resolver to be used.
     * @param dependencies list of dependencies required by this layout.
     * @param environmentType sandbox environment type.
     * @param logLayout logging layout configuration.
     * @param serverLayout server layout configuration.
     *
     * @see BrowserLayout
     * @see Dependency
     * @see DependencyResolver
     * @see EnvironmentType
     * @see LogLayout
     * @see ServerLayout
     */
    public Layout(final BrowserLayout browserLayout,
                  final DependencyResolver customDependencyResolver,
                  final List<Dependency> dependencies,
                  final EnvironmentType environmentType,
                  final LogLayout logLayout,
                  final ServerLayout serverLayout) {
        this.browserLayout = browserLayout;
        this.customDependencyResolver = customDependencyResolver;
        Objects.requireNonNull(dependencies, "Required dependencies cannot be null");
        this.dependencies = new ArrayList<>(dependencies);
        Objects.requireNonNull(environmentType, "Environment type cannot be null");
        this.environmentType = environmentType;
        Objects.requireNonNull(logLayout, "Logging layout cannot be null");
        this.logLayout = logLayout;
        this.serverLayout = serverLayout;
        verifyEnvironmentLayouts();
    }

    /**
     * Gets a browser layout configuration.
     *
     * @return browser layout configuration.
     *
     * @see BrowserLayout
     */
    public BrowserLayout getBrowserLayout() {
        return browserLayout;
    }

    /**
     * Gets a custom dependency resolver to be used.
     *
     * @return custom dependency resolver
     *
     * @see DependencyResolver
     */
    public DependencyResolver getCustomDependencyResolver() {
        return customDependencyResolver;
    }

    /**
     * Gets a list of dependencies required by this layout.
     *
     * @return required dependencies.
     *
     * @see Dependency
     */
    public List<Dependency> getDependencies() {
        return new ArrayList<>(dependencies);
    }

    /**
     * Gets the type of sandbox environment.
     *
     * @return sandbox environment type.
     *
     * @see EnvironmentType
     */
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Gets a logging layout configuration.
     *
     * @return logging layout configuration.
     *
     * @see LogLayout
     */
    public LogLayout getLogLayout() {
        return logLayout;
    }

    /**
     * Gets a server layout configuration.
     *
     * @return server layout configuration.
     *
     * @see ServerLayout
     */
    public ServerLayout getServerLayout() {
        return serverLayout;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private void verifyEnvironmentLayouts() {
        if (environmentType == EnvironmentType.BROWSER) {
            Objects.requireNonNull(browserLayout, "Browser layout cannot be null");
        }
        if (environmentType == EnvironmentType.SERVER) {
            Objects.requireNonNull(serverLayout, "Server layout cannot be null");
        }
    }
}
