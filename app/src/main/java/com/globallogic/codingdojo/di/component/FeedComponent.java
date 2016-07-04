package com.globallogic.codingdojo.di.component;

import com.globallogic.codingdojo.di.PerActivity;
import com.globallogic.codingdojo.di.module.ActivityModule;
import com.globallogic.codingdojo.di.module.FeedModule;
import com.globallogic.codingdojo.ui.fragments.FeedsFragment;

import dagger.Component;

/**
 * @author natalia
 * @since 0.3
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FeedModule.class})
public interface FeedComponent extends ActivityComponent {
    void inject(FeedsFragment feedsFragment);
}