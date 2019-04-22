package com.example.guitar_tutor.di.component;

import com.example.guitar_tutor.di.ActivityScope;
import com.example.guitar_tutor.di.module.AppInfoViewModule;
import com.example.guitar_tutor.ui.activity.AppInfoActivity;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in an
 * {@link com.example.guitar_tutor.ui.view.AppInfoView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = AppInfoViewModule.class
)
@ActivityScope
public interface AppInfoViewComponent {

    void inject(AppInfoActivity view);
}
