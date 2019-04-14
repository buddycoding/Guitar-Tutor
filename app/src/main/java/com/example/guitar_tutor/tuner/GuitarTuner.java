package com.example.guitar_tutor.tuner;

import com.example.guitar_tutor.tuner.detection.PitchDetector;
import com.example.guitar_tutor.tuner.note.MutableNote;
import com.example.guitar_tutor.tuner.note.Note;
import com.example.guitar_tutor.tuner.note.NoteFinder;
import com.example.guitar_tutor.tuner.recorder.AudioRecorder;

import io.reactivex.Observable;

/**
 * An implementation of the {@link Tuner} interface.
 */
public class GuitarTuner implements Tuner {

    private final MutableNote note = new MutableNote();

    private final Observable<Note> observable;

    public GuitarTuner(final AudioRecorder audioRecorder, final PitchDetector detector, final NoteFinder finder) {

        // Initialize the Observable to be for listening to notes
        observable = Observable.create(emitter -> {
            try {
                audioRecorder.startRecording();

                while (!emitter.isDisposed()) {
                    double frequency = detector.detect(audioRecorder.readNext());

                    finder.setFrequency(frequency);

                    // Since the note object has mutable fields and we return the same instance,
                    // lock the object while updating its data
                    synchronized (note) {
                        note.setFrequency(frequency);
                        note.setName(finder.getNoteName());
                        note.setPercentOffset(finder.getPercentageDifference());
                    }

                    emitter.onNext(note);
                }

                audioRecorder.stopRecording();

                emitter.onComplete();
            } catch (Exception exception) {
                emitter.tryOnError(exception);
            }
        });
    }

    @Override
    public Observable<Note> startListening() {
        return observable.share();
    }
}
