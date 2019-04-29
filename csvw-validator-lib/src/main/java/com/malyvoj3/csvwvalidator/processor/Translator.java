package com.malyvoj3.csvwvalidator.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceResourceBundle;

import java.text.MessageFormat;
import java.util.*;

@Slf4j
public class Translator {

    public static final String BUNDLE_PREFIX = "translate_lib";

    public static final Locale LOCALE_CZ = new Locale("cs", "CZ");
    public static final Locale LOCALE_EN = new Locale("en", "GB");

    private List<Locale> locales = Collections
            .unmodifiableList(Arrays.asList(LOCALE_CZ, LOCALE_EN));

    private static Map<Locale, ResourceBundle> bundleCache;

    static {
        bundleCache = new HashMap<>(2);
        bundleCache.put(LOCALE_EN, initializeBundle(LOCALE_EN));
        bundleCache.put(LOCALE_CZ, initializeBundle(LOCALE_CZ));
    }

    public List<Locale> getProvidedLocales() {
        return locales;
    }

    public String getTranslation(String key, Locale locale, Object... params) {
        if (key == null) {
            log.warn("Got lang request for key with null value!");
            return "";
        }

        ResourceBundle bundle = bundleCache.get(locale);
        if (bundle == null) {
            // Use EN as default.
            bundle = bundleCache.get(LOCALE_EN);
        }

        String value;
        try {
            value = bundle.getString(key);
        } catch (final MissingResourceException e) {
            log.warn("Missing resource", e);
            return "!" + locale.getLanguage() + ": " + key;
        }
        if (params != null && params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }

    private static ResourceBundle initializeBundle(final Locale locale) {
        return readProperties(locale);
    }

    protected static ResourceBundle readProperties(final Locale locale) {
        final ClassLoader cl = Translator.class.getClassLoader();

        ResourceBundle propertiesBundle = null;
        try {
            propertiesBundle = MessageSourceResourceBundle.getBundle(BUNDLE_PREFIX, locale,
                    cl);
        } catch (final MissingResourceException e) {
            log.warn("Missing resource", e);
        }
        return propertiesBundle;
    }

}
