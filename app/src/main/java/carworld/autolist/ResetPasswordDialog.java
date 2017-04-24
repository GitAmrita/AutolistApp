package carworld.autolist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by amritachowdhury on 4/24/17.
 */

public class ResetPasswordDialog extends AlertDialog {

    @Bind(R.id.new_password)
    protected TextView newPassword;
    private LoginActivity activity;
    private AlertDialog resetPasswordDialog;
    SharedPreferences sharedPreference;

    public ResetPasswordDialog(LoginActivity activity) {
        super(activity);
        this.activity = activity;
        sharedPreference = PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        createSaveSearchResultDialog();

    }
    private void createSaveSearchResultDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    new ContextThemeWrapper(activity, R.style.AlertDialog));
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog_reset_password, null);
            ButterKnife.bind(this, dialogView);
            alertDialogBuilder.setView(dialogView);
            resetPasswordDialog = alertDialogBuilder.create();
            resetPasswordDialog.show();
        } catch(Exception ex) {

        }
    }

    @OnClick(R.id.reset)
    protected void resetPressed() {
        resetPasswordDialog.cancel();
        sharedPreference.edit().putString(Config.sharedPreference.PASSWORD_HASHED,
                newPassword.getText().toString()).apply();
        Toast.makeText(activity.getApplicationContext(), activity.getApplicationContext().
                getString(R.string.password_resetted), Toast.LENGTH_SHORT).show();
    }
}
