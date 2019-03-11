package com.malyvoj3.csvwvalidator.utils;

import lombok.experimental.UtilityClass;
import org.urllib.Url;
import org.urllib.Urls;

@UtilityClass
public class UriUtils {
    // TODO v diplomce je spatna reference misto rfc3986 je rfc3968.

    /**
     * Validates given string if it is valid http/https URI as is specified in RFC 3986.
     *
     * @param string String for validation.
     * @return true If given string is valid URI with http/https schema as is specified in RFC 3986.
     */
    public boolean isValidUri(String string) {
        boolean isValid = true;
        try {
            Urls.parse(string);
        } catch (Exception ex) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Normalize http/https URI as is specified in RFC 3986.
     *
     * @param uri Http/https URI which should be normalize.
     * @return Normalized URL or null if it is not valid http/https URI.
     */
    public String normalizeUri(String uri) {
        Url normalizedUrl = getNormalizedUrl(uri);
        return normalizedUrl != null ? normalizedUrl.toString() : null;
    }

    /**
     * Normalizes two URIs and compare them if they are equals.
     *
     * @param firstUri  First http/https URI.
     * @param secondUri Second http/https URI.
     * @return true if firstUri is equal to secondUri after their normalization.
     */
    public boolean uriEquals(String firstUri, String secondUri) {
        boolean areEquals = false;
        Url first = getNormalizedUrl(firstUri);
        Url second = getNormalizedUrl(secondUri);
        if (first != null && second != null) {
            areEquals = first.equals(second);
        }
        return areEquals;
    }

    /**
     * Resolves relative URI against base URI.
     *
     * @param baseUri Http/https base URI.
     * @param uri     Http/https relative URI.
     * @return Resolved uri against baseUri as it is specified in RFC 3986.
     */
    public String resolveUri(String baseUri, String uri) {
        Url base = Urls.parse(baseUri);
        Url resolved = base.resolve(uri);
        return resolved.toString();
    }

    private Url getNormalizedUrl(String uri) {
        Url normalizedUrl;
        try {
            normalizedUrl = Urls.parse(uri);
        } catch (Exception ex) {
            normalizedUrl = null;
        }
        return normalizedUrl;
    }
}
