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
package io.github.sebastiantoepfer.common.condition4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FulfilableTest {

    @Test
    void should_throw_exception_if_not_fulfilled() {
        assertEquals(
            "is invalid",
            assertThrowsExactly(
                IllegalArgumentException.class,
                () -> unfulfillable().asVerification("is invalid").check("")
            )
                .getMessage()
        );
    }

    @Test
    void should_be_usable_as_predicate() {
        assertFalse(unfulfillable().asPredicate().test("test"));
        assertTrue(fulfillable().asPredicate().test("test"));
    }

    private static Fulfilable<Object> unfulfillable() {
        return new Fulfilable<>() {
            @Override
            public boolean isFulfilledBy(final Object value) {
                return false;
            }
        };
    }

    private Fulfilable<Object> fulfillable() {
        return new Fulfilable<>() {
            @Override
            public boolean isFulfilledBy(final Object value) {
                return true;
            }
        };
    }
}
