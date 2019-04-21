package com.malyvoj3.csvwvalidator.domain;

public enum Severity {

    /**
     * Error which is warning in STRICT mode of validating. Otherwise it is not error.
     */
    STRICT_WARNING,

    /**
     * Just warning.
     */
    WARNING,

    /**
     * Error problem.
     */
    ERROR,

    /**
     * Error problem and processor must be stopped.
     */
    FATAL
}
