package com.malyvoj3.csvwvalidator.utils;

import lombok.experimental.UtilityClass;
import org.dmfs.rfc3986.Uri;
import org.dmfs.rfc3986.encoding.Precoded;
import org.dmfs.rfc3986.uris.LazyUri;
import org.dmfs.rfc3986.uris.Normalized;
import org.dmfs.rfc3986.uris.Resolved;
import org.dmfs.rfc3986.uris.Text;

@UtilityClass
public class UriUtils {
    // TODO v diplomce je spatna reference misto rfc3986 je rfc3968.

    // Precoded - uz je to zakodovane, pripadne zmenit

    public String resolveUri(String baseUri, String uri) {
        Uri resolved = new Resolved(
                new LazyUri(new Precoded(baseUri)),
                new LazyUri(new Precoded(uri)));
        return new Text(resolved).toString();
    }

    public String normalizeUri(String uri) {
        Uri normalized = new Normalized(new LazyUri(new Precoded(uri)));
        return new Text(normalized).toString();
    }


    // Budou potreba?
    public String resolveAndNormalizeUri(String baseUri, String uri) {
        Uri resolved = new Resolved(
                new LazyUri(new Precoded(baseUri)),
                new LazyUri(new Precoded(uri)));
        return new Text(new Normalized(resolved)).toString();
    }


    public String normalizeAndResolveUri(String baseUri, String uri) {
        Uri resolved = new Resolved(
                new Normalized(new LazyUri(new Precoded(baseUri))),
                new Normalized(new LazyUri(new Precoded(uri))));
        return new Text(resolved).toString();
    }

}
