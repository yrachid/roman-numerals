package com.zenvia.roman;

import java.util.Optional;

public interface Either<L, R> {
    Optional<R> right();

    Optional<L> left();
}
