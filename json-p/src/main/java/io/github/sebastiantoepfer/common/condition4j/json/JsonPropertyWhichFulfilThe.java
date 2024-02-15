/*
 * The MIT License
 *
 * Copyright 2024 sebastian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sebastiantoepfer.common.condition4j.json;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.sebastiantoepfer.common.condition4j.Fulfilable;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonPointer;
import jakarta.json.JsonValue;
import java.util.Objects;

public final class JsonPropertyWhichFulfilThe implements Fulfilable<JsonObject> {

    private final Fulfilable<JsonValue> propertyCondition;
    private final JsonPointer propertyPath;

    public JsonPropertyWhichFulfilThe(final String propertyName, final Fulfilable<JsonValue> propertyCondition) {
        this(Json.createPointer("/".concat(propertyName)), propertyCondition);
    }

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public JsonPropertyWhichFulfilThe(final JsonPointer propertyPath, final Fulfilable<JsonValue> propertyCondition) {
        this.propertyPath = Objects.requireNonNull(propertyPath);
        this.propertyCondition = Objects.requireNonNull(propertyCondition);
    }

    @Override
    public boolean isFulfilledBy(final JsonObject value) {
        return propertyCondition.isFulfilledBy(retrieveValueFrom(value));
    }

    private JsonValue retrieveValueFrom(final JsonObject value) {
        JsonValue result;
        try {
            if (propertyPath.containsValue(value)) {
                result = propertyPath.getValue(value);
            } else {
                result = JsonValue.NULL;
            }
        } catch (JsonException e) {
            result = JsonValue.NULL;
        }
        return result;
    }
}
