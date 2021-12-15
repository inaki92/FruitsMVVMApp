package com.inaki.fruitmvvmapp.rest

import com.inaki.fruitmvvmapp.model.Fruits
import com.inaki.fruitmvvmapp.model.FruitsItem
import com.inaki.fruitmvvmapp.rest.NetworkApi.Companion.SEARCH_FRUIT
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    /**
     * This is the RxJava implementation for the network call
     */
//    @GET(ALL_FRUITS)
//    fun retrieveAllFruits(): Single<Fruits>
//
//    @GET(SEARCH_FRUIT)
//    fun searchFruit(@Path ("fruitId") fruitName: String): Single<FruitsItem>

    /**
     * This is the Kotlin Coroutines implementation for a network call
     * Getting all the fruits from server
     */
    @GET(ALL_FRUITS)
    suspend fun retrieveAllFruits(): Response<Fruits>

    /**
     * This is the coroutines implementation to get specific fruit from network
     */
    @GET(SEARCH_FRUIT)
    suspend fun searchFruit(@Path ("fruitId") fruitName: String): Response<FruitsItem>


    companion object {
        const val BASE_URL = "https://www.fruityvice.com/"

        private const val ALL_FRUITS = "api/fruit/all"
        private const val SEARCH_FRUIT = "api/fruit/{fruitId}"
    }
}