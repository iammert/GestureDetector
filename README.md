# GestureDetector
Custom touch gestures for Android.

PS. Please don't hesitate the open pull request for desired gestures. So we can expand the gesture list.


## LongTouchGestureDetector
This is the combination of `onTouch` and `onLongPress`.
In android there is not way to get all continuing touch event after a long press. Framework's LongPressListener only emits the long press event. 
`LongTouchGestureDetector` combines `onTouch` and `onLongPress` event. And also buffers the `ACTION_DOWN` event and emits it first.


```kotlin
val longTouchGestureDetector = LongTouchGestureDetector(object : LongTouchGestureDetector.LongTouchListener {
    override fun onLongTouchEvent(event: MotionEvent) {
        // gets every touch events after a long press
    }
})

view.setOnTouchListener { _, event -> longTouchGestureDetector.onTouchEvent(event) }
```

## Setup
```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
dependencies {
    implementation 'com.github.iammert:GestureDetector:-SNAPSHOT'
}
```

License
--------


    Copyright 2018 Mert Şimşek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





