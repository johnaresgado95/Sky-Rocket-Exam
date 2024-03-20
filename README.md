# SKY ROCKET EXAM

## About
This mini project has simple functionalities, but it applies the best code practices using Clean Architecture and the MVVM pattern. The target users for this are those who are at home and want to go shopping. With this app, you can easily find new devices or products that are on sale or newly released.

## Resources
- [API Documentation](https://dummyjson.com/docs/products#google_vignette)
- [Image and Banner place holder](https://storyset.com)
- [Chat GPT for contents and placeholders](https://chat.openai.com/)

## Feature
* App Onboarding
* Login Account
* View List of Products
* View Single Product
* Search Product
* Refresh List of Products

## Api Endpoints
* auth/login
* products
* products?limit=10
* products/{id}
* products/search?q=

## Tech Stack
* Java Native
* ViewModel
* MVVM
* Clean Architecture
* Github

## Library
* implementation "androidx.coordinatorlayout:coordinatorlayout:1.2.0" - For UI Toolbar scroll collapse
* implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0' - To Refresh layout for RecyclerView
* implementation 'com.squareup.retrofit2:retrofit:2.9.0' - Use for HTTP GET and POST Method 
* implementation 'com.squareup.retrofit2:converter-gson:2.9.0' - For parsing HTTP json
* implementation 'com.github.bumptech.glide:glide:4.13.2' - For Image Loading
* annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0' - Annotation processor for Glide library to generate efficient code.
* implementation 'com.facebook.shimmer:shimmer:0.5.0' - Add Facebook Shimmer library for UI loading animations in Android.
