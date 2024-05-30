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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import jakarta.json.Json;
import jakarta.json.JsonValue;
import org.junit.jupiter.api.Test;

class AllJsonPropertiesFulfillTest {

    @Test
    void should_be_filfilled_if_all_properties_matches_the_condition() {
        assertThat(
            new AllJsonPropertiesFulfill(new JsonValueOfType(JsonValue.ValueType.ARRAY)).isFulfilledBy(
                Json.createObjectBuilder()
                    .add("a", JsonValue.EMPTY_JSON_ARRAY)
                    .add("b", JsonValue.EMPTY_JSON_ARRAY)
                    .add("c", JsonValue.EMPTY_JSON_ARRAY)
                    .build()
            ),
            is(true)
        );
    }

    @Test
    void should_not_be_filfilled_if_one_properties_does_not_matches_the_condition() {
        assertThat(
            new AllJsonPropertiesFulfill(new JsonValueOfType(JsonValue.ValueType.ARRAY)).isFulfilledBy(
                Json.createObjectBuilder()
                    .add("a", JsonValue.EMPTY_JSON_ARRAY)
                    .add("b", JsonValue.EMPTY_JSON_OBJECT)
                    .add("c", JsonValue.EMPTY_JSON_ARRAY)
                    .build()
            ),
            is(false)
        );
    }
}
