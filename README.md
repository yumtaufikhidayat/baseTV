# baseTV
baseTV is an Android TV show catalog app that let you get update of latest TV show.

This app built with these technologies:
### Hardware
- CPU : Intel© Core™ i3-6006U CPU @ 2.0GHz
- Memory : 2 x 4 GB RAM
- Graphics : Intel HD Graphics 520

### Software
#### Operating System
- OS Name : Linux Mint (based on Ubuntu 20.04 LTS)
- Version : 20 LTS
- Platform : 64 bit

#### Programming Language
- Language Name : Java
- Version : 11.0.8

#### Components
- MVVM Architecture
- Retrofit
- Lifecycle Extensions
- Room persistent database
- RxJava
- Material Design
- Data Binding

#### API (Application Interface)
- Source : https://www.episodate.com/api

#### IDE (Integrated Development Environment)
- IDE Name : Android Studio
- Version : 4.1

#### Java Build Tools
- Java Build Tools : Gradle
- Android Gradle Plugin Version : 4.1.0
- Android Gradle : 6.5

#### SDK Version and SDK Tools
- Target SDK Version : 30
- Min SDK Version : 23

#### AndroidX
- Migrate to AndroidX : Yes

#### Dependencies
##### By Gradle
        - implementation 'androidx.appcompat:appcompat:1.2.0'
        - implementation 'com.google.android.material:material:1.2.1'
        - implementation 'androidx.constraintlayout:constraintlayout:2.0.2'
        - testImplementation 'junit:junit:4.13.1'
        - androidTestImplementation 'androidx.test.ext:junit:1.1.2'
        - androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

##### By Third Parties
- Material design
        - implementation 'com.google.android.material:material:1.2.1'

- Network
        - implementation 'com.squareup.retrofit2:retrofit:2.5.0'
        - implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
        - implementation 'com.squareup.okhttp3:okhttp:3.10.0'
        - implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'

- Picasso
        - implementation 'com.squareup.picasso:picasso:2.71828'

- Lifecycle extensions
        - implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

- Room & RxJava
        - implementation 'androidx.room:room-runtime:2.2.5'
        - annotationProcessor 'androidx.room:room-compiler:2.2.5'
        - implementation 'androidx.room:room-rxjava2:2.2.5'

- RxJava
        - implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'

- Scalable size units
        - implementation 'com.intuit.sdp:sdp-android:1.0.6'
        - implementation 'com.intuit.ssp:ssp-android:1.0.6'

- Rounded image view
        - implementation 'com.makeramen:roundedimageview:2.3.0'
