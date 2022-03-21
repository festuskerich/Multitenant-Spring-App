
package com.francium.multitenant.utils;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class ReactorUtils {
    public static <R> Mono<R> errorIfEmpty(Mono<R> mono, Supplier<Throwable> throwableSupplier) {
        return mono.switchIfEmpty(Mono.defer(() -> Mono.error(throwableSupplier.get())));
    }
}