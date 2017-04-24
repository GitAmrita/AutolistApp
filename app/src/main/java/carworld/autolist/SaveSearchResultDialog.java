package carworld.autolist;

import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by amritachowdhury on 4/23/17.
 */

public class SaveSearchResultDialog extends AlertDialog {
    private MainActivity activity;
    private AlertDialog saveSearchResultDialog;

    public SaveSearchResultDialog(MainActivity activity) {
        super(activity);
        this.activity = activity;
        createSaveSearchResultDialog();

    }
    private void createSaveSearchResultDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    new ContextThemeWrapper(activity, R.style.AlertDialog));
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog_save_search_result, null);
            ButterKnife.bind(this, dialogView);
            alertDialogBuilder.setView(dialogView);
            saveSearchResultDialog = alertDialogBuilder.create();
            saveSearchResultDialog.setCancelable(false);
            saveSearchResultDialog.show();
        } catch(Exception ex) {

        }
    }

    @OnClick(R.id.save)
    protected void savePressed() {
        saveSearchResultDialog.cancel();
        Toast.makeText(activity.getApplicationContext(), activity.getApplicationContext().
                        getString(R.string.saved), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cancel)
    protected void cancelledPressed() {
        saveSearchResultDialog.cancel();
    }
}
