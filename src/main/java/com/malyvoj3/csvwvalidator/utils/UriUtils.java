package com.malyvoj3.csvwvalidator.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.urllib.Url;
import org.urllib.Urls;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class UriUtils {
    // TODO v diplomce je spatna reference misto rfc3986 je rfc3968.

    private static final Map<String, String> vocabularyPrefixes = new HashMap<>(50);

    static {
        // Maybe make this map dynamically?
        vocabularyPrefixes.put("as", "https://www.w3.org/ns/activitystreams#");
        vocabularyPrefixes.put("cc", "http://creativecommons.org/ns#");
        vocabularyPrefixes.put("csvw", "http://www.w3.org/ns/csvw#");
        vocabularyPrefixes.put("ctag", "http://commontag.org/ns#");
        vocabularyPrefixes.put("dc", "http://purl.org/dc/terms/");
        vocabularyPrefixes.put("dc11", "http://purl.org/dc/elements/1.1/");
        vocabularyPrefixes.put("dcat", "http://www.w3.org/ns/dcat#");
        vocabularyPrefixes.put("dcterms", "http://purl.org/dc/terms/");
        vocabularyPrefixes.put("dctypes", "http://purl.org/dc/dcmitype/");
        vocabularyPrefixes.put("dqv", "http://www.w3.org/ns/dqv#");
        vocabularyPrefixes.put("duv", "https://www.w3.org/TR/vocab-duv#");
        vocabularyPrefixes.put("foaf", "http://xmlns.com/foaf/0.1/");
        vocabularyPrefixes.put("gr", "http://purl.org/goodrelations/v1#");
        vocabularyPrefixes.put("grddl", "http://www.w3.org/2003/g/data-view#");
        vocabularyPrefixes.put("ical", "http://www.w3.org/2002/12/cal/icaltzd#");
        vocabularyPrefixes.put("ldp", "http://www.w3.org/ns/ldp#");
        vocabularyPrefixes.put("ma", "http://www.w3.org/ns/ma-ont#");
        vocabularyPrefixes.put("oa", "http://www.w3.org/ns/oa#");
        vocabularyPrefixes.put("og", "http://ogp.me/ns#");
        vocabularyPrefixes.put("org", "http://www.w3.org/ns/org#");
        vocabularyPrefixes.put("owl", "http://www.w3.org/2002/07/owl#");
        vocabularyPrefixes.put("prov", "http://www.w3.org/ns/prov#");
        vocabularyPrefixes.put("qb", "http://purl.org/linked-data/cube#");
        vocabularyPrefixes.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        vocabularyPrefixes.put("rdfa", "http://www.w3.org/ns/rdfa#");
        vocabularyPrefixes.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        vocabularyPrefixes.put("rev", "http://purl.org/stuff/rev#");
        vocabularyPrefixes.put("rif", "http://www.w3.org/2007/rif#");
        vocabularyPrefixes.put("rr", "http://www.w3.org/ns/r2rml#");
        vocabularyPrefixes.put("schema", "http://schema.org/");
        vocabularyPrefixes.put("sd", "http://www.w3.org/ns/sparql-service-description#");
        vocabularyPrefixes.put("sioc", "http://rdfs.org/sioc/ns#");
        vocabularyPrefixes.put("skos", "http://www.w3.org/2004/02/skos/core#");
        vocabularyPrefixes.put("skosxl", "http://www.w3.org/2008/05/skos-xl#");
        vocabularyPrefixes.put("v", "http://rdf.data-vocabulary.org/#");
        vocabularyPrefixes.put("vcard", "http://www.w3.org/2006/vcard/ns#");
        vocabularyPrefixes.put("void", "http://rdfs.org/ns/void#");
        vocabularyPrefixes.put("wdr", "http://www.w3.org/2007/05/powder#");
        vocabularyPrefixes.put("wrds", "http://www.w3.org/2007/05/powder-s#");
        vocabularyPrefixes.put("xhv", "http://www.w3.org/1999/xhtml/vocab#");
        vocabularyPrefixes.put("xsd", "http://www.w3.org/2001/XMLSchema#");
    }

    public boolean isCommonProperty(String string) {
        return resolveCommonProperty(string) != null;
    }

    public String resolveCommonProperty(String string) {
        String resultUri = null;
        if (StringUtils.isNotEmpty(string)) {
            int index = string.indexOf(':');
            if (index > 1) {
                String prefix = string.substring(0, index);
                String suffix = string.substring(index + 1);
                // If is absolute URL return it.
                if (suffix.startsWith("//")) {
                    resultUri = normalizeUri(string);
                } else {
                    String prefixUrl = vocabularyPrefixes.get(prefix);
                    if (prefixUrl != null) {
                        resultUri = prefixUrl + suffix;
                    }
                }
            }
        }
        return resultUri;
    }

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
