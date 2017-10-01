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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.htmlunit.corejs.javascript.NativeArray;
import net.sourceforge.htmlunit.corejs.javascript.NativeFunction;
import net.sourceforge.htmlunit.corejs.javascript.NativeObject;
import net.sourceforge.htmlunit.corejs.javascript.Undefined;

import com.soulwarelabs.ecmabox.api.content.Content;
import com.soulwarelabs.ecmabox.api.content.ContentType;
import com.soulwarelabs.ecmabox.api.content.function.FunctionDescriptor;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.content.parser.ContentParser;
import com.soulwarelabs.ecmabox.core.content.parser.ContentParserException;

/**
 * HtmlUnit-specific script execution result parser.
 *
 * @see Content
 * @see ContentParser
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
public final class HtmlUnitContentParser implements ContentParser {

    /**
     * Gets an instance of this parser.
     *
     * @return parser instance.
     */
    public static HtmlUnitContentParser getInstance() {
        return INSTANCE;
    }

    private static HtmlUnitContentParser INSTANCE = new HtmlUnitContentParser();

    @Override
    public Content parse(final @Nullable Object raw) {
        final ContentType type = type(raw);
        switch (type) {
            case BOOLEAN:
            case NUMBER:
            case STRING:
                return Content.of(raw);
            case UNDEFINED:
                return Content.undefined();
            case ARRAY:
                return parseRawAsNativeArray(raw);
            case FUNCTION:
                return parseRawAsNativeFunction(raw);
            case OBJECT:
            default:
                return parseRawAsNativeObject(raw);
        }
    }

    private Content parseRawAsNativeArray(final Object raw) {
        final NativeArray rawArray = (NativeArray) raw;
        final List<Content> value = new ArrayList<>();
        for (final Object rawElement : rawArray) {
            value.add(parse(rawElement));
        }
        return Content.of(value);
    }

    private Content parseRawAsNativeFunction(final Object raw) {
        final String expression = raw.toString().replace("\n", "").trim();
        final FunctionDescriptor value = new FunctionDescriptor(expression);
        return Content.of(value);
    }

    private Content parseRawAsNativeObject(final @Nullable Object raw) {
        if (raw == null) {
            return Content.nullObject();
        }
        final Map<String, Content> value = new HashMap<>();
        final NativeObject rawObject = (NativeObject) raw;
        for (final Object rawKey : rawObject.keySet()) {
            if (rawKey instanceof String) {
                final Object rawValue = rawObject.get(rawKey);
                final String key = (String) rawKey;
                final Content parsedValue = parse(rawValue);
                value.put(key, parsedValue);
            }
        }
        return Content.of(value);
    }

    private ContentType type(final @Nullable Object raw) {
        if (raw == null) {
            return ContentType.OBJECT;
        }
        if (raw instanceof NativeArray) {
            return ContentType.ARRAY;
        }
        if (raw instanceof Boolean) {
            return ContentType.BOOLEAN;
        }
        if (raw instanceof NativeFunction) {
            return ContentType.FUNCTION;
        }
        if (raw instanceof Number) {
            return ContentType.NUMBER;
        }
        if (raw instanceof String) {
            return ContentType.STRING;
        }
        if (raw instanceof Undefined) {
            return ContentType.UNDEFINED;
        }
        if (raw instanceof NativeObject) {
            return ContentType.OBJECT;
        }
        throw new ContentParserException("Unknown raw object type", raw);
    }

    private HtmlUnitContentParser() {

    }
}
