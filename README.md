# Weather Forecast App

## Overview
The Weather Forecast App provides weather information for the user's current location. Users can view current weather conditions, capture and save images with weather data, and access a history of saved images.

---

## Features
1. **Location Detection**  
   Automatically detects the user's location using GPS or network services.

2. **Weather Display**  
   Shows current weather conditions: temperature, humidity, wind speed, and a status icon (e.g., sunny, rainy).

3. **Image Overlay**  
   Allows users to overlay weather data onto images captured via the device's camera.

4. **Unit Conversion**  
   Users can switch between Celsius and Fahrenheit.

5. **Error Handling**  
   Handles location detection and API failures gracefully with appropriate error messages.

6. **Data Storage**  
   Saves captured images with weather data to user storage using the Android **File API**.

   6. **Unit Tests**
using Junit and mockk



---

## Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Coroutines** for asynchronous programming
- **MVVM Architecture** for clean code and separation of concerns
- **Modularized Project** structure
- **Retrofit** for API calls
- **File API** for saving and accessing captured images and associated metadata.


