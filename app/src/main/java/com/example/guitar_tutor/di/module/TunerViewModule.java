package com.example.guitar_tutor.di.module;

import com.example.guitar_tutor.di.FragmentScope;
import com.example.guitar_tutor.presenter.TunerPresenter;
import com.example.guitar_tutor.tuner.config.AndroidAudioConfig;
import com.example.guitar_tutor.tuner.GuitarTuner;
import com.example.guitar_tutor.tuner.config.AudioConfig;
import com.example.guitar_tutor.tuner.Tuner;
import com.example.guitar_tutor.tuner.converter.ArrayConverter;
import com.example.guitar_tutor.tuner.converter.PCMArrayConverter;
import com.example.guitar_tutor.tuner.detection.PitchDetector;
import com.example.guitar_tutor.tuner.detection.YINPitchDetector;
import com.example.guitar_tutor.tuner.note.ArrayNoteFinder;
import com.example.guitar_tutor.tuner.note.FrequencyFinder;
import com.example.guitar_tutor.tuner.note.MapFrequencyFinder;
import com.example.guitar_tutor.tuner.note.NoteFinder;
import com.example.guitar_tutor.tuner.recorder.AndroidAudioRecorder;
import com.example.guitar_tutor.tuner.recorder.AudioRecorder;
import com.example.guitar_tutor.ui.view.TunerView;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link Module} used for dependency injection in a {@link TunerView} implementation.
 */
@Module
public class TunerViewModule {

    private final TunerView view;

    public TunerViewModule(TunerView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    AudioConfig provideAudioConfig() {
        return new AndroidAudioConfig();
    }

    @Provides
    @FragmentScope
    ArrayConverter provideArrayConverter() {
        return new PCMArrayConverter();
    }

    @Provides
    @FragmentScope
    AudioRecorder provideAudioRecorder(AudioConfig audioConfig, ArrayConverter converter) {
        return new AndroidAudioRecorder(audioConfig, converter);
    }

    @Provides
    @FragmentScope
    PitchDetector providePitchDetector(AudioConfig audioConfig) {
        return new YINPitchDetector(audioConfig);
    }

    @Provides
    @FragmentScope
    NoteFinder provideNoteFinder() {
        return new ArrayNoteFinder();
    }

    @Provides
    @FragmentScope
    Tuner provideTuner(AudioRecorder audioRecorder, PitchDetector detector, NoteFinder finder) {
        return new GuitarTuner(audioRecorder, detector, finder);
    }

    @Provides
    @FragmentScope
    FrequencyFinder provideFrequencyFinder() {
        return new MapFrequencyFinder();
    }

    @Provides
    @FragmentScope
    TunerPresenter provideTunerPresenter(Tuner tuner, FrequencyFinder frequencyFinder) {
        return new TunerPresenter(view, tuner, frequencyFinder);
    }
}
