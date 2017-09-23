package com.soulwarelabs.ecmabox.core.content.parser;

import com.soulwarelabs.ecmabox.api.content.Content;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * API of a script execution content parser.
 *
 * @see Content
 *
 * @author Ilia Gubarev.
 */
@Private
@ImmutableByContract
public interface ContentParser {

    /**
     * Parses raw script execution content.
     *
     * @param raw raw execution content to be parsed (optional).
     * @return parsed content.
     *
     * @see Content
     */
    Content parse(@Nullable Object raw);
}
