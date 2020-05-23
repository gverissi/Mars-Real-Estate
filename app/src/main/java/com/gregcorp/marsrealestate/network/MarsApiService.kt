/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gregcorp.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

/**
 * Use the Moshi Builder to create a Moshi object with the KotlinJsonAdapterFactory.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use Retrofit.Builder to create the Retrofit object.
 *
 * Add an instance of ScalarsConverterFactory and the BASE_URL we provided,
 * then call build() to create the Retrofit object.
 */
private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create()) // Convert Json response into a string.
    .addConverterFactory(MoshiConverterFactory.create()) // Use Moshi to convert Json response into a Kotlin object.
    .baseUrl(BASE_URL)
    .build()

/**
 * Create a MarsApiService interface, and define a getProperties() method to request the JSON response string.
 *
 * Annotate the method with @GET, specifying the endpoint for the JSON real estate response,
 * and create the Retrofit Call object that will start the HTTP request.
 */
interface MarsApiService {
    @GET("realestate")
    fun getProperties():
            Call<List<MarsProperty>>
}

/**
 * Passing in the service API you just defined,
 * create a public object called MarsApi to expose the Retrofit service to the rest of the app:
 */
object MarsApi {
    // Singleton
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}