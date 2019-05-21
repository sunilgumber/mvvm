package com.ameba.demo.swipe.viewModel;

import android.content.Context;
import android.util.Log;

import com.ameba.demo.swipe.model.data.RetrofitHelper;
import com.ameba.demo.swipe.model.entity.Movie;
import com.ameba.demo.swipe.view.listener.CompletedListener;

import rx.Subscriber;

/**
 * Created by sunil on 5/21/19.
 */

public class TopViewModel {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private boolean isGPS;
    private final Context context;
    private Subscriber<Movie> subscriber;

    private CompletedListener completedListener;


    public TopViewModel(Context context, CompletedListener completedListener) {
        this.context=context;
        this.completedListener = completedListener;
        // getSplashData();
    }

    //unused
    public void CallEventsDataAPI(){
        subscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                Log.d("[MainViewModel]", "onCompleted");
                completedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {

            }
        };
        RetrofitHelper.getInstance().getMovies(subscriber, 0, 20);
    }
}
