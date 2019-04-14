package com.example.guitar_tutor.di.component;

import android.content.Context;

import com.example.guitar_tutor.GuitarTunerApplication;
import com.example.guitar_tutor.di.ApplicationContext;
import com.example.guitar_tutor.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A Dagger {@link Component} that provides global dependencies.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context getApplicationContext();

    void inject(GuitarTunerApplication application);
}
