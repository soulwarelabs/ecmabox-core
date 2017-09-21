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
package com.soulwarelabs.ecmabox.api.dependency;

import java.util.Objects;

import com.soulwarelabs.ecmabox.utility.Strings;
import com.soulwarelabs.ecmabox.utility.Urls;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Key;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * ECMA dependency descriptor.
 *
 * @see DependencyOrigin
 * @see DependencyResolver
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Key
public final class Dependency {

    /**
     * Creates a new CDN-based dependency descriptor.
     *
     * @param resource dependency identifier.
     * @return new dependency descriptor
     *
     * @see Dependency
     */
    public static Dependency cdn(final String resource) {
        return of(resource, DependencyOrigin.CDN);
    }

    /**
     * Creates a new NPM-based dependency descriptor.
     *
     * @param resource dependency identifier.
     * @return new dependency descriptor
     *
     * @see Dependency
     */
    public static Dependency npm(final String resource) {
        return of(resource, DependencyOrigin.NPM);
    }

    /**
     * Creates a new dependency descriptor.
     *
     * @param resource dependency identifier.
     * @param origin dependency origin type.
     * @return new dependency descriptor
     *
     * @see Dependency
     * @see DependencyOrigin
     */
    public static Dependency of(final String resource,
                                final DependencyOrigin origin) {
        Objects.requireNonNull(origin, "Dependency origin cannot be null");
        Objects.requireNonNull(resource, "Dependency resource name cannot be null");
        if (origin == DependencyOrigin.CDN && !Urls.validUrl(resource)) {
            throw new IllegalArgumentException("CDN-based dependency resource must be a valid URL specification");
        }
        return new Dependency(origin, resource);
    }

    private final DependencyOrigin origin;
    private final String resource;

    private Dependency(final DependencyOrigin origin,
                       final String resource) {
        this.origin = origin;
        this.resource = resource;
    }

    /**
     * Gets the origin of this dependency
     *
     * @return dependency origin.
     *
     * @see DependencyOrigin
     */
    public DependencyOrigin getOrigin() {
        return origin;
    }

    /**
     * Gets the identifier for this dependency.
     *
     * @return dependency identifier.
     */
    public String getResource() {
        return resource;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Dependency that = (Dependency) object;
        return origin == that.origin && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, resource);
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
