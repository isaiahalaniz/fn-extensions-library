# Introduction #
The extension library includes a quick reply overlay and a quick reply popup.  The benefits of the provided quick reply options are it saves your from writing your own if you haven't already, it streamlines the ui experience if every quick reply looks the same, and using the overlay instead of a popup means the background activity isn't paused.

# Quick Reply Overlay #
```java
public static void replyOverlay(String edittextHint, String previousText,
Bitmap image, final onClickListener imageOnClick, final onClickListener sendOnClick,
final onClickListener extraOnClick, final boolean removeViewOnExtraClick,
final Bitmap extraButton, Context context, boolean lightTheme)```
imageOnClick - Provide a new Extension.onClickListener and override the method public void onClick(), this field is nullable

sendOnClick - Provide a new Extension.onClickListener and override the method public void onClick(String str) with str being the text currently in the reply EditText

**Extra Button**

If you would like an extra button within the overlay (next to the reply button) you can provide the following (if you don't they are nullable):

extraOnClick - Provide a new Extension.onClickListener and override the method public void onClick(), this field is nullable

removeViewOnExtraClick - true if you would like to remove the overlay once the extra button is clicked.  Remember the overlay shows over everything so if you launching a popup, for example, from the extra button it might be a good idea to remove the overlay and then recall this method once your action has finished.

extraButton - Provide a bitmap of the image for the extra button, this field is nullable

# Quick Reply Popup #
In order to use this option you need to create a new class which extends Extension.Reply.  This class also provides a getId() method if you need to determine the id of the popup which called it (which you'll pass in via the method).

```java
public static void replyPopup(long id, String hint, String previousMsg,
Bitmap image, boolean extraBtn, Bitmap extraBtnImage, Context context, Class reply, boolean lightTheme)```

reply - This method takes your extended reply class.

extraBtn - true if you would like to display an extra button next to the send button

extraBtnImage - Provide the image to be used for the extra button