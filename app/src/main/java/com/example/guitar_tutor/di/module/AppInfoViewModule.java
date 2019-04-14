package com.example.guitar_tutor.di.module;

import android.content.Context;

import com.example.guitar_tutor.di.ActivityScope;
import com.example.guitar_tutor.di.ApplicationContext;
import com.example.guitar_tutor.presenter.AppInfoPresenter;
import com.example.guitar_tutor.ui.view.AppInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link Module} used for dependency injection in an {@link AppInfoView} implementation.
 */
@Module
public class AppInfoViewModule {

    private final AppInfoView view;

    public AppInfoViewModule(final AppInfoView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    AppInfoPresenter provideAppInfoPresenter(@ApplicationContext Context context) {
        return new AppInfoPresenter(context, view);
    }
}
