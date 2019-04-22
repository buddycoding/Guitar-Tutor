package com.example.guitar_tutor;

import android.app.Application;

import com.example.guitar_tutor.di.component.ApplicationComponent;
import com.example.guitar_tutor.di.component.DaggerApplicationComponent;
import com.example.guitar_tutor.di.module.ApplicationModule;

import timber.log.Timber;

public class GuitarTunerApplication extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Setup Logging
        if (BuildConfig.DEBUG) {
            // Uses the default Timber Debug Tree to output logs to LogCat
            Timber.plant(new Timber.DebugTree());
        }

        // Setup the Dagger Application Component
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }
}
