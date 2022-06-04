package com.yh.kakaotranslator.base

import android.app.Application
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    private val _viewStateLiveData = SingleLiveEvent<ViewState>()
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData

    @MainThread
    protected fun viewStateChange(viewState: ViewState) {
        viewModelScope.launch {
            _viewStateLiveData.value = viewState
        }
    }
}

interface ViewState



class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}