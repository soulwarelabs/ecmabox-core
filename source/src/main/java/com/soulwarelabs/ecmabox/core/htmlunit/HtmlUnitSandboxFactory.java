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
package com.soulwarelabs.ecmabox.core.htmlunit;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.SandboxFactory;
import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolver;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.layout.BrowserLayout;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.convention.Factory;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Factory for HtmlUnit-specific sandboxes.
 *
 * @see HtmlUnitSandbox
 * @see SandboxFactory
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
@Factory(HtmlUnitSandbox.class)
public final class HtmlUnitSandboxFactory implements SandboxFactory {

    private final DependencyResolver defaultResolver;

    /**
     * Creates a new instance of sandbox factory.
     *
     * @param defaultResolver default dependency resolver.
     *
     * @see DependencyResolver
     */
    public HtmlUnitSandboxFactory(final DependencyResolver defaultResolver) {
        Objects.requireNonNull(defaultResolver, "Default dependency resolver cannot be null");
        this.defaultResolver = defaultResolver;
    }

    @Override
    public Sandbox create(final Layout layout) {
        Objects.requireNonNull(layout, "Sandbox layout cannot be null");
        try {
            final WebClient client = new WebClient();
            final HtmlUnitRecordStorage storage = new HtmlUnitRecordStorage(layout.getLogLayout());
            client.getWebConsole().setLogger(storage);
            final StringWebResponse response = response(layout.getBrowserLayout());
            final WebWindow window = client.getCurrentWindow();
            final HtmlPage page = HTMLParser.parseHtml(response, window);
            final Sandbox sandbox = new HtmlUnitSandbox(client, page, storage);
            final DependencyResolver resolver = resolver(layout);
            for (final Dependency dependency : layout.getDependencies()) {
                final Invoice dependencyInvoice = resolver.resolve(dependency);
                sandbox.execute(dependencyInvoice);
            }
            return sandbox;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DependencyResolver resolver(final Layout layout) {
        final DependencyResolver customResolver = layout.getCustomDependencyResolver();
        return customResolver != null ? customResolver : defaultResolver;
    }

    private StringWebResponse response(final BrowserLayout browserLayout) {
        final URL url = browserLayout.getUrl();
        final String html = browserLayout.getHtml();
        return new StringWebResponse(html, url);
    }
}
