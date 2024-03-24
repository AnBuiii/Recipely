![Untitled design](https://github.com/AnBuiii/Recipely/assets/89350086/53041bfa-6796-40b4-957f-b9c224af3954)
# Recipely 🍗
**Recipely** is just a SIMPLE recipe app. Built with Modern Android developement tool. Intergrated with ML

## About
Inspired from many recipe app on store, this app was made to simplify all the use case of a chef.
- Well design with Material 3 UI
- Offline! All recipes are loaded in local database. You alway can create a new one.
- Ingredient predicted include
  
## Built with 🔨
- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for native UI
- [Corountine](https://kotlinlang.org/docs/coroutines-overview.html) - For asynchronous stuff
- [Material3 Componenet](https://m3.material.io/develop/android/mdc-android) - Modular and customizable Material Design UI components for Android.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite database for data persistence
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- [CameraX](https://developer.android.com/media/camera/camerax) - Jetpack library for in-app camera
- [Media3](https://developer.android.com/media/media3) - Media libraries that enables Android apps to display rich audio and visual experiences
- [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore) - Data presistence library for simple dataset
- [Protobuf](https://protobuf.dev/) - Protocol Buffers are language-neutral, platform-neutral extensible mechanisms for serializing structured data.
- [Tensorflow Lite](https://www.tensorflow.org/lite) - Library for deploys models on mobile device

## Project Structure 🏭
    com.anbui.recipely         # Root Package
    
    .
	├── composeApp             # Project entry point
	|
	├── build-logic            # Handle gradle build logic
	|   ├── convention         # For specific options
	|
	├── config
	|   ├── detekt             # Detekt config
	|
	├── core                   # Core modules
	|   ├── data               # 
	│   |   ├── di             # DI for Data module
	|   |   ├── impl           # Impl single source of data
	|   |   ├── mapper         # Map core model to entity vice versa
	|   |   
	|   ├── database           # Local database module
	|   |   ├── assets         # Default data for application
	|   |   ├── converter      # Room converter
	|   |   ├── dao            # Room dao
	|   |   ├── entities       # Room entities
	|   |   ├── relation       # Room cross-ref relation for better query
	|   |   
	|   ├── datastore-proto    # Define proto entity for datastore module
	|   |   
	|   ├── datastore          # Datastore module
	|   |   ├── di             # DI for datastore module
	|   |   
	|   ├── designsystem       # Design system module
	|   |   ├── components     # Application specific design component
	|   |   ├── theme          # Application specific theme system
	|   |   
	|   ├── model              # Core model module
	|   |   
	|   ├── testing            # Testing module
	|   |   ├── di             # DI for testing module
	|
	├── feature                # Feature module
	|   ├── account            # Account-related feature module
	|   |   ├── account        # Account screen, its viewmdel and components
	|   |   ├── edit_profile   # Edit profile screen,...
	|   |   ├── ...
	|   |   ├── navigation     # navigation system in account feature
	|   |   ├── components     # account feature UI component
	|   ├── ...
	
## Architecture	🏢
This project use a combination of [Multi-module architecture](https://developer.android.com/topic/modularization) and [(Model-View Model-Model](https://developer.android.com/topic/architecture#recommended-app-arch), explain as clean architecture for Android Developement.
A very well-made example by Android can be found [Now in Android](https://github.com/android/nowinandroid)

## Image-classification
I use pre-trained [MobileNet](https://keras.io/api/applications/mobilenet/) and retained with Tensorflow library. Here are some resources:
- [Tensorflow Image retraining](https://www.tensorflow.org/hub/tutorials/tf2_image_retraining)
- My [Google colab](https://colab.research.google.com/drive/13r6H_8RC1Lh5fiO0cNKwRJ75WKGiv9ti?usp=drive_link)
- My [Ingredient Dataset](https://drive.google.com/file/d/19jeOCHd5IpK1dKwNMXXp1HxGqADh0YaA/view?usp=drive_link)

## Further plans
Make it an cross-platform application
- [Kotlinx-Datetime](https://github.com/Kotlin/kotlinx-datetime) - KotlinX multiplatform date/time library
- [Ktor](https://ktor.io/) - Ktor is a framework for building asynchronous server-side and client-side applications

## Lint
[Detekt](https://github.com/detekt/detekt) is Static code analysis for Kotlin

## Contribute 🪖
Any contributions are welcome! Here are some note 
- Open issue to report a bug and make your suggestion.
- Fork this repo and do your changes. Remember to follow project's code style.
- Open PR against `main` branch with nice description and you are good to go 😊.
Recipely is an open source project developed by [me](https://github.com/AnBuiii). My purpose is to demonstrate my knowleadge in Android Development and share with everyone who interested ☺️. Please do
not use for commercial purposes.

## License

This application is released under Apache license 2.0 (see [LICENSE](LICENSE)).
Some of the used libraries are released under different licenses.
