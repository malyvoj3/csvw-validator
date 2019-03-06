package com.malyvoj3.csvwvalidator.validation;

public enum Severity {

    /**
     * Just warning.
     */
    WARNING,

    /**
     * Error problem.
     */
    ERROR,

    /**
     * Error problem and processing must be stopped.
     */
    FATAL
}
