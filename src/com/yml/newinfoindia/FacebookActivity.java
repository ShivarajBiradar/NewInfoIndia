package com.yml.newinfoindia;

import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FriendPickerFragment;
import com.facebook.widget.LoginButton;
import com.facebook.widget.PlacePickerFragment;
import com.facebook.widget.ProfilePictureView;

public class FacebookActivity extends FragmentActivity {

private final String PENDING_ACTION_BUNDLE_KEY = "com.facebook.samples.hellofacebook:PendingAction";

int intentflag = 1;

private LoginButton loginButton;
private ProfilePictureView profilePictureView;
private TextView greeting;
private PendingAction pendingAction = PendingAction.NONE;
private ViewGroup controlsContainer;
private GraphUser user;
String username;
String userId;
String userlocation;


//FacebooKClass facebook;
FacebookHandler handler;
public static final String PREFS_NAME = "MyPrefsFile";

private enum PendingAction {
NONE, POST_PHOTO, POST_STATUS_UPDATE
}

private UiLifecycleHelper uiHelper;

private Session.StatusCallback callback = new Session.StatusCallback() {
@Override
public void call(Session session, SessionState state,
Exception exception) {
onSessionStateChange(session, state, exception);
}
};

private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
@Override
public void onError(FacebookDialog.PendingCall pendingCall,
Exception error, Bundle data) {
Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
}

@Override
public void onComplete(FacebookDialog.PendingCall pendingCall,
Bundle data) {
Log.d("HelloFacebook", "Success!");
}
};

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
handler=new FacebookHandler(getApplicationContext());



//

uiHelper = new UiLifecycleHelper(this, callback);
uiHelper.onCreate(savedInstanceState);

if (savedInstanceState != null) {
String name = savedInstanceState
.getString(PENDING_ACTION_BUNDLE_KEY);
pendingAction = PendingAction.valueOf(name);
}
//onRestoreInstanceState(savedInstanceState);
setContentView(R.layout.activity_facebook);
Session session = Session.getActiveSession();

Button b1 = (Button) findViewById(R.id.button1);

b1.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
Intent intent = new Intent(FacebookActivity.this,MainActivity.class);
intent.putExtra("username", username);
intent.putExtra("photoId", userId);
intent.putExtra("userlocation", userlocation);
intent.putExtra("flag", 1);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intentflag = 0;
startActivity(intent);
//INSERTING TABLE 

Log.d("inserting", "insrting item");
handler.insertFacebook(new FacebooKClass(username,userlocation));
Log.d("Reading: ", "Reading all FACEBOOK .."); 
List<FacebooKClass> facebook=handler.getAllFacebook();

for (FacebooKClass fb:facebook)
{
	String log="Id:"+fb.getId()+"\tNAME:"+ fb.getName()+"\tLocation :"+ fb.getLocation();
	
	Log.d("facebook", log);
}

}
});

loginButton = (LoginButton) findViewById(R.id.login_button);
loginButton
.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
@Override
public void onUserInfoFetched(GraphUser user) {
FacebookActivity.this.user = user;
// loginButton.setFragment(this);
loginButton.setReadPermissions(Arrays.asList(
"user_birthday", "user_location"));


updateUI();
// It's possible that we were waiting for this.user
// to be populated in order to post a
// status update.
// handlePendingAction();
}
});

profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
greeting = (TextView) findViewById(R.id.greeting);

controlsContainer = (ViewGroup) findViewById(R.id.main_ui_container);

final FragmentManager fm = getSupportFragmentManager();
Fragment fragment = fm.findFragmentById(R.id.fragment_container);
if (fragment != null) {
// If we're being re-created and have a fragment, we need to a) hide
// the main UI controls and
// b) hook up its listeners again.
controlsContainer.setVisibility(View.GONE);
if (fragment instanceof FriendPickerFragment) {
// setFriendPickerListeners((FriendPickerFragment) fragment);
} else if (fragment instanceof PlacePickerFragment) {
// setPlacePickerListeners((PlacePickerFragment) fragment);
}
}

// Listen for changes in the back stack so we know if a fragment got
// popped off because the user
// clicked the back button.
fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
@Override
public void onBackStackChanged() {
if (fm.getBackStackEntryCount() == 0) {
// We need to re-show our UI.
controlsContainer.setVisibility(View.VISIBLE);
}
}
});

// username = user.getName();
// userId = user.getId();
//
// try {
// userlocation = user.getLocation().getInnerJSONObject()
// .get("name").toString();
// } catch (Exception e) {
// e.printStackTrace();
// }
//
//
// Intent intent = new Intent(FacebookActivity.this,
// MainActivity.class);
// intent.putExtra("username", username);
// intent.putExtra("photoId", userId);
// intent.putExtra("userlocation", userlocation);
// intent.putExtra("flag", 1);
// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
// startActivity(intent);
//

}

@Override
protected void onResume() {
super.onResume();
uiHelper.onResume();

// Call the 'activateApp' method to log an app event for use in
// analytics and advertising reporting. Do so in
// the onResume methods of the primary Activities that an app may be
// launched into.
AppEventsLogger.activateApp(this);

updateUI();
}

@Override
protected void onSaveInstanceState(Bundle outState) {
super.onSaveInstanceState(outState);
uiHelper.onSaveInstanceState(outState);

outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
super.onActivityResult(requestCode, resultCode, data);
uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
}

@Override
public void onPause() {
super.onPause();
uiHelper.onPause();

// Call the 'deactivateApp' method to log an app event for use in
// analytics and advertising
// reporting. Do so in the onPause methods of the primary Activities
// that an app may be launched into.
AppEventsLogger.deactivateApp(this);
}


@Override
public void onDestroy() {
super.onDestroy();
uiHelper.onDestroy();
}
/////
////
/////
/*@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		uiHelper.onSaveInstanceState(savedInstanceState);
	}*/

private void onSessionStateChange(Session session, SessionState state,
Exception exception) {
if (pendingAction != PendingAction.NONE
&& (exception instanceof FacebookOperationCanceledException || exception instanceof FacebookAuthorizationException)) {
new AlertDialog.Builder(FacebookActivity.this).setTitle("Canceled")
.setMessage("Permission_not_granted")
.setPositiveButton("Ok", null).show();
pendingAction = PendingAction.NONE;
} else if (state == SessionState.OPENED_TOKEN_UPDATED) {
// handlePendingAction();
}
updateUI();
}

private void updateUI() {
Session session = Session.getActiveSession();
boolean enableButtons = (session != null && session.isOpened());


//logout button
// intentflag = getIntent().getExtras().getInt("intentflag");
if (enableButtons && user != null) {
// /////////////////////////////////////////////////////////////////////////////////////////////////////

username = user.getName();
userId = user.getId();

try {
userlocation = user.getLocation().getInnerJSONObject()
.get("name").toString();
} catch (Exception e) {
e.printStackTrace();
}

if (intentflag != 0) {
Intent intent = new Intent(FacebookActivity.this,
MainActivity.class);
intent.putExtra("username", username);
intent.putExtra("photoId", userId);
intent.putExtra("userlocation", userlocation);
intent.putExtra("flag", 1);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intentflag = 0;
startActivity(intent);
}

// //////////////////////////////////////////////////////////////////////////////////////////
profilePictureView.setProfileId(user.getId());
greeting.setText("Hello " + user.getFirstName());
} else {
profilePictureView.setProfileId(null);
greeting.setText(null);
}
////
///
//session.closeAndClearTokenInformation();

}

@Override
public void onBackPressed() {

super.onBackPressed();
finish();
}

// User has successfully logged in, save this information
// We need an Editor object to make preference changes.

}
