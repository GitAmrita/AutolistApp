package carworld.autolist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by amritachowdhury on 4/24/17.
 */
//http://stackoverflow.com/questions/2480288/programmatically-obtain-the-phone-number-of-the-android-phone
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.username)
    protected TextView username;
    @Bind(R.id.password)
    protected TextView password;
    @Bind(R.id.login)
    protected Button login;
    @Bind(R.id.reset_password)
    protected TextView resetPassword;

    SharedPreferences sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            autoPopulatePhoneNumber();
        } else {
            requestPermissionForAndroid6AndAbove();
        }
        drawComponentView();
    }

    @OnClick(R.id.reset_password)
    protected void resetPassword() {
        password.setText("");
        new ResetPasswordDialog(this);
    }

    @OnClick(R.id.login)
    protected void login() {
        if (isSignUp()) {
            String hashedPassword = Helper.getSHA512SecurePassword(password.getText().toString(),
                    Config.sharedPreference.SALT);
            sharedPreference.edit().putString(Config.sharedPreference.USERNAME,
                    username.getText().toString()).apply();
            sharedPreference.edit().putString(Config.sharedPreference.PASSWORD_HASHED,
                    hashedPassword).apply();
        } else {
            if (isIcorrectCredentials()) {
                return;
            }
        }
        sharedPreference.edit().putBoolean(Config.sharedPreference.IS_LOGGEDIN, true).apply();
        Intent intent=new Intent();
        setResult(Config.intent.CONTACT_DEALER, intent);
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Config.permission.REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    autoPopulatePhoneNumber();
                }
                break;
            default:
                break;
        }
    }

    private void drawComponentView() {
       if (isSignUp()) {
           login.setCompoundDrawablesWithIntrinsicBounds(R.drawable.signup, 0, 0, 0);
           login.setText(getString(R.string.sign_up));
           resetPassword.setVisibility(View.GONE);
       } else {
           login.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login, 0, 0, 0);
           login.setText(getString(R.string.login));
           resetPassword.setVisibility(View.VISIBLE);
       }
    }

    private boolean isSignUp() {
        return TextUtils.isEmpty(sharedPreference.getString(Config.sharedPreference.USERNAME, ""));
    }

    private boolean isIcorrectCredentials() {
        String newHashedPassword = Helper.getSHA512SecurePassword(password.getText().toString(),
                Config.sharedPreference.SALT);
        String storedPassword = sharedPreference.getString(
                Config.sharedPreference.PASSWORD_HASHED, "");
        String newUsername = username.getText().toString();
        String storedUsername = sharedPreference.getString(
                Config.sharedPreference.USERNAME, "");
        if (!newHashedPassword.equals(storedPassword)) {
            Toast.makeText(this, getString(R.string.incorrect_password), Toast.LENGTH_LONG).show();
            return true;
        }
        if (!newUsername.equals(storedUsername)) {
            Toast.makeText(this, getString(R.string.incorrect_username), Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    private void requestPermissionForAndroid6AndAbove() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE}, Config.permission.REQUEST_READ_PHONE_STATE);
        } else {
            autoPopulatePhoneNumber();
        }
    }

    private void autoPopulatePhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = tMgr.getLine1Number();
        String phone = TextUtils.isEmpty(phoneNumber) ? " " : phoneNumber;
        username.setText(phone);
    }
}
