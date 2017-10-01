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
package com.soulwarelabs.ecmabox.autotest.blackbox.special;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.layout.BrowserLayout;
import com.soulwarelabs.ecmabox.api.layout.BrowserType;
import com.soulwarelabs.ecmabox.api.layout.EnvironmentType;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.api.layout.LayoutBuilder;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.autotest.blackbox.BlackboxTestTemplate;
import com.soulwarelabs.ecmabox.autotest.convention.BlackboxTest;
import com.soulwarelabs.ecmabox.utility.Urls;

@BlackboxTest
@RunWith(JUnit4.class)
public class DocumentModelModificationTest extends BlackboxTestTemplate {

    @Before
    public void prepare() throws Exception {

    }

    @Test
    public void modifyDocumentModel() throws Exception {
        final Sandbox sandbox = sandbox(customLayout());
        execute(sandbox, "$('<li></li>').text('second item').appendTo($('ul'));");
        final Result result = execute(sandbox, "$('li').length");
        assertValue(result, 2.0);
    }

    private Layout customLayout() {
        return new LayoutBuilder()
                .environmentType(EnvironmentType.BROWSER)
                .browserLayout(customBrowserLayout())
                .dependency(jquery())
                .build();
    }

    private Dependency jquery() {
        return Dependency.cdn("https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.slim.js");
    }

    private BrowserLayout customBrowserLayout() {
        final String html = "<body><ul><li>1</li></ul></body>";
        final BrowserType type = BrowserType.DEFAULT;
        final URL url = Urls.parse("http://ecmabox.onlythenaive.com");
        return new BrowserLayout(html, type, url);
    }
}
