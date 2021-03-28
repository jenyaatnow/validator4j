package com.validator4j.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
final class ValidationErrorImpl implements ValidationError {

    @NonNull private final String path;
    @NonNull private final String message;

}
