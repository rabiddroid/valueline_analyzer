package com.cosmicapps.valueline.document.field;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleValueTest {

  @Test
  void get_double() {

    Assertions.assertThat(new DoubleValue("2.0").get())
        .isEqualTo(2.0);
  }


  @Test
  void get_neg_double() {

    Assertions.assertThat(new DoubleValue("d3.5").get())
        .isEqualTo(-3.5);
  }

  @Test
  void get_NMF() {

    Assertions.assertThat(new DoubleValue("NMF").get())
        .isNull();
  }

  @Test
  void get_blank() {

    Assertions.assertThat(new DoubleValue(" ").get())
        .isNull();
  }

  @Test
  void get_null() {

    Assertions.assertThat(new DoubleValue(null).get())
        .isNull();
  }


  @Test
  void get_not_a_number() {

    Assertions.assertThat(new DoubleValue(" -- ").get())
        .isNull();
  }
}