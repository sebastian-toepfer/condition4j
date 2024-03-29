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

import java.util.Objects;
import java.util.function.Predicate;

public interface Fulfilable<T> {
    boolean isFulfilledBy(T value);

    default Verification<T> asVerification(final String message) {
        return new Verification<>(this, message);
    }

    default Predicate<T> asPredicate() {
        return this::isFulfilledBy;
    }

    public static final class Verification<T> {

        private final Fulfilable<T> condition;
        private final String message;

        private Verification(final Fulfilable<T> condition, final String message) {
            this.condition = Objects.requireNonNull(condition);
            this.message = Objects.requireNonNull(message);
        }

        public void check(final T value) {
            if (!condition.isFulfilledBy(value)) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
