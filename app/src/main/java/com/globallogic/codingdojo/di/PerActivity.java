package com.globallogic.codingdojo.di;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Custom scoping annotation to permit objects whose lifetime should conform to the
 * life of the activity to be memorized in the correct component.
 *
 * @author Conde
 * @version 0.1
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
