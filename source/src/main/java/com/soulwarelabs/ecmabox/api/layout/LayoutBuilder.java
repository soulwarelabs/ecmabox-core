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

import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolver;
import com.soulwarelabs.ecmabox.api.log.RecordLevel;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.utility.Strings;
import com.soulwarelabs.ecmabox.utility.Urls;
import com.soulwarelabs.ecmabox.convention.Builder;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Sandbox layout configuration builder.
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
@Builder(Layout.class)
public final class LayoutBuilder {

    /**
     * Default browser layout html.
     */
    public static final String DEFAULT_BROWSER_LAYOUT_HTML = "<html><body></body></html>";

    /**
     * Default browser layout URL.
     */
    public static final String DEFAULT_BROWSER_LAYOUT_URL = "http://ecmabox.onlythenaive.com";

    /**
     * Default sandbox environment type.
     */
    public static final EnvironmentType DEFAULT_ENVIRONMENT_TYPE = EnvironmentType.BROWSER;

    /**
     * Default sandbox logging flag.
     */
    public static final boolean DEFAULT_LOG_LAYOUT_ENABLED = true;

    /**
     * Default sandbox logging level threshold.
     */
    public static final RecordLevel DEFAULT_LOG_LAYOUT_LEVEL = RecordLevel.INFO;

    private static final BrowserLayout DEFAULT_BROWSER_LAYOUT;
    private static final LogLayout DEFAULT_LOG_LAYOUT;
    private static final ServerLayout DEFAULT_SERVER_LAYOUT;

    static {
        DEFAULT_BROWSER_LAYOUT = new BrowserLayout(
                DEFAULT_BROWSER_LAYOUT_HTML,
                BrowserType.DEFAULT,
                Urls.parse(DEFAULT_BROWSER_LAYOUT_URL)
        );
        DEFAULT_LOG_LAYOUT = new LogLayout(DEFAULT_LOG_LAYOUT_ENABLED, DEFAULT_LOG_LAYOUT_LEVEL);
        DEFAULT_SERVER_LAYOUT = new ServerLayout(ServerType.DEFAULT);
    }

    private List<Dependency> dependencies;
    private EnvironmentType environmentType;
    private LogLayout logLayout;

    @Nullable
    private BrowserLayout browserLayout;

    @Nullable
    private DependencyResolver customDependencyResolver;

    @Nullable
    private ServerLayout serverLayout;

    /**
     * Create a new layout builder.
     */
    public LayoutBuilder() {
        browserLayout = DEFAULT_BROWSER_LAYOUT;
        dependencies = new ArrayList<>();
        environmentType = DEFAULT_ENVIRONMENT_TYPE;
        logLayout = DEFAULT_LOG_LAYOUT;
        serverLayout = DEFAULT_SERVER_LAYOUT;
    }

    /**
     * Create a new layout builder.
     *
     * @param layout layout to be copied.
     *
     * @see Layout
     */
    public LayoutBuilder(final Layout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");
        this.browserLayout = layout.getBrowserLayout();
        this.customDependencyResolver = layout.getCustomDependencyResolver();
        this.dependencies = new ArrayList<>(layout.getDependencies());
        this.environmentType = layout.getEnvironmentType();
        this.logLayout = layout.getLogLayout();
        this.serverLayout = layout.getServerLayout();
    }

    /**
     * Create a new layout builder.
     *
     * @param builder layout builder to be copied.
     */
    public LayoutBuilder(final LayoutBuilder builder) {
        Objects.requireNonNull(builder, "Layout builder cannot be null");
        this.browserLayout = builder.browserLayout;
        this.customDependencyResolver = builder.customDependencyResolver;
        this.dependencies = new ArrayList<>(builder.dependencies);
        this.environmentType = builder.environmentType;
        this.logLayout = builder.logLayout;
        this.serverLayout = builder.serverLayout;
    }

    /**
     * Sets a browser layout configuration
     *
     * @param browserLayout browser layout configuration (optional).
     * @return this layout builder.
     *
     * @see BrowserLayout
     */
    public LayoutBuilder browserLayout(final @Nullable BrowserLayout browserLayout) {
        this.browserLayout = browserLayout;
        return this;
    }

    /**
     * Sets a custom dependency resolver.
     *
     * @param customDependencyResolver custom dependency resolver (optional).
     * @return this layout builder.
     *
     * @see DependencyResolver
     */
    public LayoutBuilder customDependencyResolver(final @Nullable DependencyResolver customDependencyResolver) {
        this.customDependencyResolver = customDependencyResolver;
        return this;
    }

    /**
     * Adds a new dependency required for sandbox.
     *
     * @param dependency required dependency.
     * @return this layout builder.
     *
     * @see Dependency
     */
    public LayoutBuilder dependency(final Dependency dependency) {
        Objects.requireNonNull(dependency, "Dependency cannot be null");
        this.dependencies.add(dependency);
        return this;
    }

    /**
     * Sets the type of sandbox environment
     *
     * @param environmentType sandbox environment type.
     * @return this layout builder.
     *
     * @see EnvironmentType
     */
    public LayoutBuilder environmentType(final EnvironmentType environmentType) {
        this.environmentType = environmentType;
        return this;
    }

    /**
     * Sets a logging layout configuration.
     *
     * @param logLayout logging layout.
     * @return this layout builder.
     *
     * @see LogLayout
     */
    public LayoutBuilder logLayout(final LogLayout logLayout) {
        this.logLayout = logLayout;
        return this;
    }

    /**
     * Sets a server layout configuration.
     *
     * @param serverLayout server layout (optional).
     * @return this layout builder.
     *
     * @see ServerLayout
     */
    public LayoutBuilder serverLayout(final @Nullable ServerLayout serverLayout) {
        this.serverLayout = serverLayout;
        return this;
    }

    /**
     * Creates a new sandbox layout configuration.
     *
     * @return new sandbox layout.
     *
     * @see Layout
     */
    public Layout build() {
        return new Layout(
                browserLayout,
                customDependencyResolver,
                dependencies,
                environmentType,
                logLayout,
                serverLayout
        );
    }

    /**
     * Create a new copy of this builder.
     *
     * @return copy of this builder.
     */
    public LayoutBuilder copy() {
        return new LayoutBuilder(this);
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
