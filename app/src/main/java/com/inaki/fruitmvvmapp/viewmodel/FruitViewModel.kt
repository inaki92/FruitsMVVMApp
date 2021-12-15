package com.inaki.fruitmvvmapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inaki.fruitmvvmapp.rest.NetworkApi
import com.inaki.fruitmvvmapp.utils.UIState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import javax.inject.Inject

class FruitViewModel(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(ioDispatcher)
) : ViewModel() {

    /**
     * This fruitApi will be injected by Dagger
     */
    @Inject lateinit var fruitApi: NetworkApi

    private val disposable by lazy {
        CompositeDisposable()
    }

    /**
     * This is the mutable live data to be set in the RxJava call
     */
    private var _allFruits: MutableLiveData<UIState> = MutableLiveData(UIState.Loading())

    /**
     * This is the immutable livedata to be observed in the UI
     *
     * UIState will propagate the loading, success or error, depending on the response
     */
    val allFruits: LiveData<UIState> get() = _allFruits

    private var _searchFruitLivaData: MutableLiveData<UIState> = MutableLiveData(UIState.Loading())
    val searchFruit: LiveData<UIState> get() = _searchFruitLivaData

    /**
     * This method will help to fetch the data from server and get all fruits
     * This is the RxJava implementation
     */
//    fun getAllFruits() {
//        val allFruitsDisposable = fruitApi.retrieveAllFruits()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { _allFruits.postValue(UIState.Success(it)) },
//                { _allFruits.postValue(UIState.Error(it)) }
//            )
//
//        disposable.add(allFruitsDisposable)
//    }

    /**
     * Coroutine implementation to get all fruits from server
     */
    fun getAllFruits() {
        coroutineScope.launch {
            // right here I am performing the task in the worker thread
            try {
                // here I am retrieving all fruits network call
                val response = fruitApi.retrieveAllFruits()

                // switching to the main thread
                // Not using withContext in example, because I am taking advantage of using live data
//                withContext(Dispatchers.Main) {
//                    // right here whatever we do inside the withContext will happen in the main thread
//                    response.isSuccessful
//                    response.body()
//                    response.errorBody()
//                }

                // here I am checking for the success response
                if (response.isSuccessful) {
                    // here I will check for the non-nullable value of the body
                    response.body()?.let { fruits ->
                        // right here I will get the body value as non-nullable

                        // postValue will update the value in the worker thread
                        // and will be observed in the main thread by the live data
                        _allFruits.postValue(UIState.Success(fruits))
                        // if the body is null, we use the Elvis operator and then we post the error to the live data
                    } ?: _allFruits.postValue(
                        UIState.Error(
                            IllegalStateException("Body response is null")
                        )
                    )
                } else {
                    _allFruits.postValue(UIState.Error(
                        Throwable(response.errorBody()?.string())
                    ))
                }
            } catch (e: Exception) {
                _allFruits.postValue(UIState.Error(e))
            }
        }
    }

    /**
     * This will search for an specific fruit from service
     */
//    fun searchSpecificFruit(fruitName: String) {
//        val searchFruit = fruitApi.searchFruit(fruitName)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { _searchFruitLivaData.postValue(UIState.Success(it)) },
//                { _searchFruitLivaData.postValue(UIState.Error(it)) }
//            )
//    }
}