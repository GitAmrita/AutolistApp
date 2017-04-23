package carworld.autolist;

import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carworld.autolist.Listener.OnSelectPriceRangeListener;

/**
 * Created by amritachowdhury on 4/22/17.
 */
// http://www.compiletimerror.com/2013/09/android-seekbar-example.html
public class PriceDialog extends AlertDialog {

    private MainActivity activity;
    private AlertDialog priceDialog;
    @Bind(R.id.price1_seek_bar)
    protected SeekBar price1SeekBar;
    @Bind(R.id.price1)
    protected TextView price1Label;
    @Bind(R.id.price2_seek_bar)
    protected SeekBar price2SeekBar;
    @Bind(R.id.price2)
    protected TextView price2Label;

    private OnSelectPriceRangeListener listener;

    protected PriceDialog(MainActivity activity, final OnSelectPriceRangeListener listener) {
        super(activity);
        this.activity = activity;
        this.listener = listener;
        createPriceDialog();
    }

    private void createPriceDialog() {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    new ContextThemeWrapper(activity, R.style.AlertDialog));
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog_price_range, null);
            ButterKnife.bind(this, dialogView);
            alertDialogBuilder.setView(dialogView);
            setListeners();
            priceDialog = alertDialogBuilder.create();
            priceDialog.show();
        } catch(Exception ex) {

        }
    }

    private void setListeners() {

        price1SeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress = 0;
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progressValue, boolean fromUser) {
                        progress = progressValue;
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        price1Label.setText(activity.getString(R.string.price_seek_bar_label)
                                + progress + activity.getString(R.string.price_seek_bar_multiplier));
                    }
                });

        price2SeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress = 0;
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progressValue, boolean fromUser) {
                        progress = progressValue;
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        price2Label.setText(activity.getString(R.string.price_seek_bar_label)
                                + progress + activity.getString(R.string.price_seek_bar_multiplier));
                    }
                });
    }

    @OnClick(R.id.ok)
    protected void okPressed() {
        priceDialog.cancel();
        int min = Config.priceRange.MULTIPLIER;
        int max = Config.priceRange.MULTIPLIER;
        int price1 = price1SeekBar.getProgress();
        int price2 = price2SeekBar.getProgress();
        if (price1 <= price2) {
            min *= price1 ;
            max *= price2;
        } else {
            min *= price2;
            max *= price1;
        }
        listener.getPriceRange(min, max);
    }
}
