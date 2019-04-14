package com.example.guitar_tutor.di.component;

import com.example.guitar_tutor.di.FragmentScope;
import com.example.guitar_tutor.di.module.TunerViewModule;
import com.example.guitar_tutor.ui.fragment.CircleGuitarTunerFragment;
import com.example.guitar_tutor.ui.view.TunerView;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in a {@link TunerView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = TunerViewModule.class
)
@FragmentScope
public interface TunerViewComponent {

    void inject(CircleGuitarTunerFragment view);
}
