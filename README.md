<img src = https://github.com/balsikandar/Robin/blob/master/assets/robin-img.png>

# Robin

[![Android Arsenal](https://img.shields.io/badge/Android%20Dev%20Digest-191-brightgreen.svg?style=flat)](https://www.androiddevdigest.com/digest-191/)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Robin-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6980)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[ ![Download](https://api.bintray.com/packages/balsikandarnsit/maven/Robin/images/download.svg) ](https://bintray.com/balsikandarnsit/maven/Robin/_latestVersion)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/balsikandar/CrashReporter/blob/master/LICENSE)

## Robin is a library to log Bundle data passed between Activities and fragments.
 
### It's hard to reproduce a bug that's why we should log data that can reduce human efforts. 

One of my friend said this and it's totally true. That's why i always try to log essential data.

#### Here is an [article](https://medium.com/mindorks/debugging-in-android-with-robin-logging-essential-data-de3ecac9919f) related to this project.

## Bundle
Definition: A mapping from String values to various Parcelable types. Bundle is generally used for passing data between various activities of android. 

I have spent good amount of time to debug these at times, depending on code quality i had to work on. Sometimes even tester need to see what **id** or **values** we're passing to open a given page and then i had to add logs for them.
So i thought let's do something about this.


## Robin In Action APIs
- Log Bundle data passed between Activity and Fragments.
- A Callback method to send screen views events to Google Analytics or other clients
- A Callback method for Activity/Fragment lifecycle which can be used to retrace user activities before crashes
- An API to transform Intent or Bundle data as HashMap (key-value)

##### This library doesn't requires any permission or root access

## To use this in your app

Use below dependency to add it in your app

```
compile 'com.balsikandar.android:robin:0.1.2'
```

If you only want to use it in your debug app then use debugCompile dependency

```
debugCompile 'com.balsikandar.android:robin:0.1.2'
```

### Robin in action

Initialise this library in your Application class using

```
Robin.start(this)
```

If you only want to use bundle data logging and disable other callbacks then initialise Robin as

```
Robin.start(this, false)
```

### The other moves of Robin

#### 1- Tracking screen views

```
AnalyticsClient.sendView(AppConstants.ScreenViewLabel);
```
To simplify that, you can implement **ScreenViewCallback** interface in your application class which provides below callback method

```
    @Override
    public void onScreenShown(String className, String customScreenView) {
        
    }
```

**className**: is actual Activity/Fragment name which you can use to log screenName on your analytics client. 

**customScreenView**:  but a lot of times we need to log custom screenNames. To do that you can declare a global variable named **screenView** in your Activity/Fragment classes and it'll be returned as `customScreenView` in **onScreenShown** callback.

Define a global variable **screenView** in your Activity/Fragment with label value
 
 ```
 private String screenView = "RobinSampleHomePage";
 ```

#### 2- Retrace user actions before a Crash

At times we get stacktrace log where we have no idea of where it's originated and it's not even possible to figure out in which class crash occurred. 

Whatever you log with `Crashlytics.log(msg)` gets associated with the crashes and is viewable in crashlytics dashboard.
These log can really help in narrowing down origin of a crash and Activity/Fragment lifecycle callback methods can come in handy.

To do this, implement **LifeCycleCallbacks** interface in your application class which provides below callback method

```
    @Override
    public void breadCrumps(String name, String callback) {
        
    }
```
 
 **name**: is Activity/Fragment class name,
 **callback**: is lifecycle callback method name

#### 3- Transform Bundle data to HashMap

Use Below APIs to get bundle data as HashMap key-value pairs
 
 ```
    Robin.getDataAsMap(intent)
     
    Robin.getDataAsMap(bundle)
 ```
 
 you can also call below APIs explicitly to log bundle or intent data
 
 ```
    Robin.logData(intent, "tagName");
    
    Robin.logData(bundle, "tagName");
 ```
 
 **tagName**- provide this field to filter log in LogCat and if you don't provide this tag **Robin/** will be used.
 
 
### Contributing to this Repo
If you have a feature request or suggestion raise a [Ticket](https://github.com/balsikandar/Robin/issues) here. You can also contribute just create a pull request and dive in.
 
### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

### That's it for now
For any query or questions you can access me at below mediums.

### TODO
Add application enter foreground and background callbacks
Add logic to calculate the size of bundle in transaction.

### Contact - Let's connect and share knowledge
- [Twitter](https://twitter.com/balsikandar)
- [Github](https://github.com/balsikandar)
- [Medium](https://medium.com/@balsikandar.nsit)
- [Facebook](https://www.facebook.com/balsikandar)
 
 ## License
 ```
 MIT License

Copyright (c) 2018 BAL SIKANDAR

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
