package com.example.guitar_tutor.presenter;

import com.example.guitar_tutor.tuner.Tuner;
import com.example.guitar_tutor.tuner.note.FrequencyFinder;
import com.example.guitar_tutor.tuner.note.NoteName;
import com.example.guitar_tutor.ui.view.TunerView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class TunerPresenter implements Presenter {

    private final TunerView view;
    private final Tuner tuner;
    private final FrequencyFinder frequencyFinder;

    private Disposable disposable;

    public TunerPresenter(final TunerView view, final Tuner tuner, final FrequencyFinder frequencyFinder) {
        this.view = view;
        this.tuner = tuner;
        this.frequencyFinder = frequencyFinder;
    }

    @Override
    public void detachView() {
        // No Operation
    }

    public void startListeningForNotes() {
        disposable = tuner.startListening()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(note -> view.onShowNote(note.getName(), note.getFrequency(), note.getPercentOffset()),
                        error -> Timber.e(error, "Error Starting to Listen to Notes."));
    }

    public void stopListeningForNotes() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void notePressed(final String noteName, final float x, final float y) {
        view.onPlayNote(noteName, frequencyFinder.getFrequency(NoteName.forName(noteName)), x, y);
    }
}
