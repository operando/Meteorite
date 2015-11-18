# Meteorite

This library provides the ability to upload a screenshot of your app to Slack.

# How to use

1. Add your Slack API token as `meta-data` element to the `application` element

Slack API token will be obtained from the following URL.

https://api.slack.com/web

```xml
// your app's AndroidManifest.xml
<?xml version="1.0" encoding="utf-8"?>
<manifest
    package="com.os.operando.meteorite.sample"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:name=".MyApplication"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme">

        .....

        <meta-data
            android:name="com.os.operando.meteorite.slackToken"
            android:value="your Slack API token"/>
    </application>
</manifest>
```

2. Add `MeteoriteActivity` and `MeteoroidReceiver` to the manifest:

```xml
// your app's AndroidManifest.xml
<activity android:name="com.os.operando.meteorite.MeteoriteActivity"/>

<receiver
    android:name="com.os.operando.meteoroid.MeteoroidReceiver"
    android:enabled="true"
    android:exported="true"/>
```

3. Call `MeteoroidNotification.show()` and `registerActivityLifecycleCallbacks(new MeteoroidActivityLifecycleCallbacks())` at the Application class.

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


# Download

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
