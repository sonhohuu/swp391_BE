package com.FPTU.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Locale;

/**
 * Request common class.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Request {

  private String locale;

  /**
   * get locale object by string locale.
   *
   * @return Locale
   */
  public Locale getLocale() {
    if (locale != null) {
      return new Locale.Builder().setLanguage(locale).build();
    } else {
      return null;
    }
  }

}