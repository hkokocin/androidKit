[![Build Status](https://travis-ci.org/hkokocin/androidKit.svg?branch=master)](https://travis-ci.org/hkokocin/androidKit)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.hkokocin.atheris/androidkit.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.hkokocin.atheris%22%20AND%20a%3A%androidkit%22)

# AndroidKit

AndroidKit is a small kotlin toolkit for android development. It is not intended to replace other libraries like Anko and the like which already are very powerful. But most of the time you only use a small potion of libraries like these. That is why this project focuses on a very specific approach to android app development. More on this approach later. Until then just have a quick look on what androidKit has to offer.

## Installation

```groovy
dependencies{
    compile 'com.github.hkokocin.atheris:AndroidKit:0.3'
}
```

### Snaphot

```groovy
repositories{
    maven { url 'https://oss.sonatype.org/content/repositories/snapshots' }
}

dependencies{
    compile ('com.github.hkokocin.atheris:AndroidKit:0.4-SNAPSHOT') { changing = true }
}
```

## Usage

### Set up your Activities and Fragments

The following works in Activities as well as in Fragments and their support library versions. Note that if you want to use e.g. the support Fragment you have to explicitely import the support library into your project.

#### Retrieve Views
```kotlin
class MainActivity : Activity(){
    private val textView: TextView by viewId(R.id.tv_name)
    private val recyclerView by viewId<RecyclerView>(R.id.rv_items)
}
```

#### Get resources
```kotlin
class MainActivity : Activity() {
    private val name: String by resource(R.string.name)
    private val label by resource<String>(R.string.label)
}
```

Supported types are:
* String
* Array\<String\>
* Charsequence
* Array\<Charsequence\>
* Int
* Array\<Int\>
* Boolean
* Drawable

However there are some special cases that need to be retrieved differently

```kotlin
class MainActivity : Activity() {
    private val dimension by dimensionInPixels(R.dimen.activity_horizontal_margin)
    private val color by colorResource(R.color.primary)
}
```

#### Retrieve extras
This works for Activities only
```kotlin
class MainActivity : Activity() {
    private val id: String? by extra("ID")
    private val id2: String by extra("ID", "defaultId")
    private val id3 by extra<String>("ID", "defaultId")
}
```

Supported are all possible extra types except Parcelables. They are currently not supported since I think you should avoid the overhead that comes with using them. Instead I prefer to pass ids and fetch the complete data from the data layer.

#### Start activities
This can again be used in Activites as well as Fragments
```kotlin
start<CategoryDetailActivity>()
startForResult<CategoryDetailActivity>(REQUEST_CODE)
        
start<CategoryDetailActivity> {
    // you can initialize the intent here
    putExtra("ID", "12345")
}
```

### Context extensions

#### AlertDialogs

```kotlin
context.alertDialog {
    message("message")
    positiveButton("Yes") { doSomething() }
    negativeButton("No")
}
```

#### System services

```kotlin
context.windowManager
context.inputMethodManager
```

### View extensions

#### Snackbars

Create snackbars from any view.

```kotlin
view.snackbar("message")

view.snackbar("message", Snackbar.LENGTH_SHORT)

view.snackbar("message"){
    setAction("dismiss"){ dismiss() }
}
```

### Events


```kotlin
view.onClick{ doSomething() }

view.onLongClick{ doSomething() }

view.onTouch{view, motionEvent -> doSomething() }

view.onAttachedToWindow{ doSomething() }

view.onDetachedToWindow{ doSomething() }

view.onLayoutChanged{ doSomething() }

// ---

editText.textChange{ doSomething(editText.text) }

editText.beforeTextChange{ doSomething(editText.text) }

editText.afterTextChange{ doSomething(editText.text) }

// ---

spinner.itemSelected{ item -> doSomething(item) }
```
