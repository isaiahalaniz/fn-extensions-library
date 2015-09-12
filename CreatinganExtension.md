# Permissions #

Any app with an FN extension will require the permission:
```xml
<uses-permission android:name="robj.floating.notifications.EXTENSION" />```

# Registering an FN #
To register an extension you need to either have a service or a receiver within your manifest with the Intent Filter:
```xml
<action android:name="robj.floating.notifications.isExtension" />```

If you use a service then that service will be started by FN every time the selected extensions are loaded.  That service or receiver also require the following metadata tags:
```xml
<meta-data android:name="label" android:value="Extension name" />```
```xml
<meta-data android:name="desc" android:value="Brief description" />```
```xml
<meta-data android:name="id" android:value="unique id" />```

If you extension has a settings activity you can specify the name of the activity with this metadata tag - make sure the activity has exported set to true for it to be launched by FN:
```xml
<meta-data android:name="settingsActivity" android:value=".SettingsActivityName" />```

# Getting Extension Status #
If you intend to use the provided method ```java
Extension.isEnabled(context, unique_id)``` to determine whether or not your extension has been enabled by the user within Floating Notifications you need to include the following in your manifest:
```xml
<receiver android:name="robj.floating.notifications.EnabledReceiver">
<intent-filter>
<action android:name="robj.floating.notifications.extension.ENABLED">

Unknown end tag for &lt;/action&gt;



<action android:name="robj.floating.notifications.extension.DISABLED">

Unknown end tag for &lt;/action&gt;




Unknown end tag for &lt;/intent-filter&gt;




Unknown end tag for &lt;/receiver&gt;


```

# Adding, Updating, Removing or Hiding your FN #
The library contains the core functionality to allow your extension to interact with FN and provides 3 main methods to do this.  Make sure the library is included in your Android project.

```java
public static void addOrUpdate(final Bitmap image, final String message, final long id,
final Bitmap actionOne, final Bitmap actionTwo, final Bitmap actionThree,
final boolean persistent, final boolean stack, final boolean hideCounter, Context context)```

Image - The image the floating notification will show - **this is required to be 200px x 200px in size**.

Message - The text the FN will show, if you use the stack option then you can provide the latest message and FN will stack them for you, if you don't use that option you will only see the text you provide here.

ID - Each FN you create has a unique id, this is used to identify which to remove, hide or update.

Actions - These images are for the action buttons the FN can show and are nullable if they are not required.

Peristant - If the FN is dismissible or not.  The same as pinning an app within FN.

Stack - Whether or not you want FN to stack the messages so for example if you update your FN 3 times and stack is enabled it will show as:

```xml
Message 1
====

Message 2
====

Message 3```

If you don't enable stack it will only show the latest message.


```java
public static void remove(long id, Context context)```

This method is used to remove an FN based on the id provided.


```java
public static void hideAll(long id, Context context)```

This method is used to hideAll FN's based on the id provided.  This is useful if, for example, the action chosen if quick reply - you can hide the FN while the user replies and then remove it once the reply is sent.

# Controlling FN actions #

There are four actions you can be notified for from your FN Extension, tapping the text or tapping any of the 3 possible action buttons.  When any of these actions are taken a broadcast is sent to your app from FN notifying you which action was taken from which fn (specifying the id).  You can then act on these actions.

The broadcast action you need to listen for is:

```xml
<action android:name="robj.floating.notifications.extension" />```
You can then get a long extra from the intent which is the id using:
```xml
intent.getLongExtra(Extension.ID, -1)```
And the action button pressed by using:
```xml
intent.getIntExtra(Extension.ACTION, -1)```

The possible action results are 0 (text tapped), or 1 - 3 (action buttons tapped in order as they appear).


# And that's everything you need to create an extension :-) #

There is a sample extension project uploaded here if needed.