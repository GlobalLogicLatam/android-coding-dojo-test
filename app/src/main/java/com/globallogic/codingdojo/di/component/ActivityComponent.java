package com.globallogic.codingdojo.di.component;

import android.app.Activity;

import com.globallogic.codingdojo.di.PerActivity;
import com.globallogic.codingdojo.di.module.ActivityModule;

import dagger.Component;

/**
 * @author natalia
 * @since 0.1
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}