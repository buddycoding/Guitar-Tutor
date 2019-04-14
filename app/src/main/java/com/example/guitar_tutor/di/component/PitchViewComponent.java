package com.example.guitar_tutor.di.component;

import com.example.guitar_tutor.di.FragmentScope;
import com.example.guitar_tutor.di.module.PitchViewModule;
import com.example.guitar_tutor.ui.fragment.PitchPlayerFragment;
import com.example.guitar_tutor.ui.view.PitchView;

import dagger.Component;

/**
 * A Dagger {@link Component} used for dependency injection in a {@link PitchView} implementation.
 */
@SuppressWarnings("WeakerAccess")
@Component(
        dependencies = ApplicationComponent.class,
        modules = PitchViewModule.class
)
@FragmentScope
public interface PitchViewComponent {

    void inject(PitchPlayerFragment view);
}
