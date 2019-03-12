package com.malyvoj3.csvwvalidator.utils;

import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LanguageUtils {

  /**
   * Validates given String, if it is valid BCP47 language tag.
   * @param string String representing language tag.
   * @return true if given string is valid language tag.
   */
  public boolean isLanguageTag(String string) {
    return getLanguageCode(string) != null;
  }

  /**
   * Validates given strings, if they are equals language tags as it's said in Csv on the Web standard.
   * @param first First language tag.
   * @param second Second language tag.
   * @return true If given strings are valid BCP47 language tags and if they are equals after truncation defined in BCP47.
   */
  public boolean equalsLanguageTags(String first, String second) {
    String firstLang = getLanguageCode(first);
    String secondLang = getLanguageCode(second);
    return (CsvwKeywords.NATURAL_LANGUAGE_CODE.equals(firstLang) && secondLang != null)
        ||  (firstLang != null && (CsvwKeywords.NATURAL_LANGUAGE_CODE.equals(secondLang) || firstLang.equals(secondLang)));
  }


  /**
   * Get BCP47 language code from String.
   * @param languageTag {@link String}, which should contains BCP47 language tag.
   * @return Language code of parsed language tag or 'und' if givenn language tag string is 'und'.
   */
  private String getLanguageCode(String languageTag) {
    if (CsvwKeywords.NATURAL_LANGUAGE_CODE.equals(languageTag)) {
      return CsvwKeywords.NATURAL_LANGUAGE_CODE;
    }
    return Optional.ofNullable(languageTag)
        .map(Locale::forLanguageTag)
        .map(Locale::getLanguage)
        .filter(StringUtils::isNotEmpty)
        .orElse(null);
  }

}
