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

package com.gregcorp.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// Execute this adapter when an XML file have the attribute imageUrl
@BindingAdapter("imageUrl")
fun bindingImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // Glide require a URI object an the server needs a HTTPS scheme
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        // We use Glide to display the image (Glide need to know the context to access some resources)
        Glide.with(imgView.context).load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation) // We display an animation wile the image is loading
                .error(R.drawable.ic_broken_image) // We display a broken image if there is an error
            ).into(imgView)
    }
}