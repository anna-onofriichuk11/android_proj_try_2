<<<<<<< HEAD
package com.example.tryengapp.views.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    var isFirst = true

    private val cDisposible by lazy { CompositeDisposable() }

    private val status = MutableLiveData<BaseViewStatus>()
    fun getStatusBase(): LiveData<BaseViewStatus> = status

    protected fun setStatusBase(newStatus: BaseViewStatus) {
        status.value = newStatus
    }

    protected fun <T : Any> Observable<T>.compositeSubscribe(
        onNext: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ) = composite(
        subscribe(onNext, onError, onComplete)
    )

    protected fun Completable.compositeSubscribe(
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) = composite(
        subscribe(onSuccess, onError)
    )

    private fun composite(disposable: Disposable) {
        cDisposible.add(disposable)
    }

    protected open fun removeListeners() {}

    override fun onCleared() {
        removeListeners()
        cDisposible.dispose()
        super.onCleared()
    }
=======
package com.example.tryengapp.views.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    var isFirst = true

    private val cDisposible by lazy { CompositeDisposable() }

    private val status = MutableLiveData<BaseViewStatus>()
    fun getStatusBase(): LiveData<BaseViewStatus> = status

    protected fun setStatusBase(newStatus: BaseViewStatus) {
        status.value = newStatus
    }

    protected fun <T : Any> Observable<T>.compositeSubscribe(
        onNext: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    ) = composite(
        subscribe(onNext, onError, onComplete)
    )

    protected fun Completable.compositeSubscribe(
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) = composite(
        subscribe(onSuccess, onError)
    )

    private fun composite(disposable: Disposable) {
        cDisposible.add(disposable)
    }

    protected open fun removeListeners() {}

    override fun onCleared() {
        removeListeners()
        cDisposible.dispose()
        super.onCleared()
    }
>>>>>>> 38b66a03b864ca564c30ccc18f51636406be220a
}