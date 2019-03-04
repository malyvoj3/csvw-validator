package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import com.malyvoj3.csvwvalidator.domain.metadata.Context;
import com.malyvoj3.csvwvalidator.domain.metadata.Normalizable;

import lombok.Data;

@Data
public abstract class Property<T> implements Normalizable {

  protected T value;

  public Property(T value) {
    this.value = value;
  }

  @Override
  public void normalize(Context context) {
  }
}
