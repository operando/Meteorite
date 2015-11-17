# Meteorite

This library provides the ability to upload a screenshot of your app to Slack.

# How to use

Call `MeteoroidNotification.show()` and `registerActivityLifecycleCallbacks(new MeteoroidActivityLifecycleCallbacks())` at the Application class.

```java

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MeteoroidNotification.show(this, MeteoriteActivity.createComponentName(this));
        registerActivityLifecycleCallbacks(new MeteoroidActivityLifecycleCallbacks());
    }
}
```


## Download

Gradle:
```groovy
allprojects {
    repositories {
        jcenter()
        maven {
            url "https://dl.bintray.com/operandoos/maven/"
        }
    }
}

compile 'com.os.operando.meteorite:meteorite:1.0.0'
```
