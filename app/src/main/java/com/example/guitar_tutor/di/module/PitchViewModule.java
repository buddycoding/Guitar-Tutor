package com.example.guitar_tutor.di.module;

import android.content.Context;
import android.media.AudioManager;

import com.example.guitar_tutor.di.ApplicationContext;
import com.example.guitar_tutor.di.FragmentScope;
import com.example.guitar_tutor.presenter.PitchPresenter;
import com.example.guitar_tutor.tuner.GuitarPitchPlayer;
import com.example.guitar_tutor.tuner.PitchPlayer;
import com.example.guitar_tutor.tuner.config.AndroidAudioConfig;
import com.example.guitar_tutor.tuner.config.AudioConfig;
import com.example.guitar_tutor.tuner.converter.FrequencyConverter;
import com.example.guitar_tutor.tuner.converter.SineWaveFrequencyConverter;
import com.example.guitar_tutor.tuner.player.AndroidAudioPlayer;
import com.example.guitar_tutor.tuner.player.AudioPlayer;
import com.example.guitar_tutor.tuner.volume.AndroidVolumeObserver;
import com.example.guitar_tutor.tuner.volume.VolumeObserver;
import com.example.guitar_tutor.ui.view.PitchView;

import dagger.Module;
import dagger.Provides;

/**
 * A Dagger {@link Module} used for dependency injection in a {@link PitchView} implementation.
 */
@Module
public class PitchViewModule {

    private final PitchView view;

    public PitchViewModule(final PitchView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    AudioManager provideAudioManager(@ApplicationContext final Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Provides
    @FragmentScope
    AudioConfig provideAudioConfig() {
        return new AndroidAudioConfig();
    }

    @Provides
    @FragmentScope
    AudioPlayer provideAudioPlayer(final AudioConfig audioConfig) {
        return new AndroidAudioPlayer(audioConfig);
    }

    @Provides
    @FragmentScope
    FrequencyConverter provideFrequencyConverter(final AudioConfig audioConfig) {
        return new SineWaveFrequencyConverter(audioConfig);
    }

    @Provides
    @FragmentScope
    PitchPlayer providePitchPlayer(final AudioPlayer audioPlayer, final FrequencyConverter frequencyConverter) {
        return new GuitarPitchPlayer(audioPlayer, frequencyConverter);
    }

    @Provides
    @FragmentScope
    VolumeObserver provideVolumeObserver(final AudioManager audioManager) {
        return new AndroidVolumeObserver(audioManager);
    }

    @Provides
    @FragmentScope
    PitchPresenter providePitchPresenter(final PitchPlayer pitchPlayer, final VolumeObserver volumeObserver) {
        return new PitchPresenter(view, pitchPlayer, volumeObserver);
    }
}
