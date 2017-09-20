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

import java.net.URL;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Browser layout configuration.
 *
 * @see BrowserType
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class BrowserLayout {

    private final String html;
    private final BrowserType type;
    private final URL url;

    /**
     * Create a new browser layout configuration.
     *
     * @param html initial browser page layout.
     * @param type ECMA-aware browser type.
     * @param url initial browser URL.
     *
     * @see BrowserType
     */
    public BrowserLayout(final String html,
                         final BrowserType type,
                         final URL url) {
        Objects.requireNonNull(html, "Browser layout HTML cannot be null");
        this.html = html;
        Objects.requireNonNull(type, "Browser type cannot be null");
        this.type = type;
        Objects.requireNonNull(url, "Browser layout url cannot be null");
        this.url = url;
    }

    /**
     * Gets the initial browser html layout.
     *
     * @return initial browser html layout.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Gets the type of ECMA-aware browser.
     *
     * @return browser type.
     *
     * @see BrowserType
     */
    public BrowserType getType() {
        return type;
    }

    /**
     * Gets the initial browser URL.
     *
     * @return initial browser URL.
     */
    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
