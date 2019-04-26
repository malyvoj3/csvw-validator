package com.malyvoj3.csvwvalidator.web.view;

import com.vaadin.flow.i18n.I18NProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

@Slf4j
@Component
public class TranslationProvider implements I18NProvider {

    public static final String BUNDLE_PREFIX = "translate";

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

    @Override
    public List<Locale> getProvidedLocales() {
        return locales;
    }

    @Override
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
        if (params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }

    private static ResourceBundle initializeBundle(final Locale locale) {
        return readProperties(locale);
    }

    protected static ResourceBundle readProperties(final Locale locale) {
        final ClassLoader cl = TranslationProvider.class.getClassLoader();

        ResourceBundle propertiesBundle = null;
        try {
            propertiesBundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale,
                    cl);
        } catch (final MissingResourceException e) {
            log.warn("Missing resource", e);
        }
        return propertiesBundle;
    }
}
