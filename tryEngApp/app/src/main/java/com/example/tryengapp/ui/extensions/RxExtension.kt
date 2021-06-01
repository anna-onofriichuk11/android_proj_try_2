package com.example.tryengapp.ui.extensions


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.withDefaults(): Observable<T> {
    return compose {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e("Translate", "Error Observable withDefaults(): ${error.localizedMessage}")
            }
    }
}

fun Completable.withDefaults(): Completable {
    return compose {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e("Translate", "Error Completable withDefaults(): ${error.localizedMessage}")
            }
    }
}