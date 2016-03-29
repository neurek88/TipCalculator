package edu.unl.cse.tipcalculator;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.text.NumberFormat;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.bankr_000.tipcalculator.R;


public class MainActivity extends Activity {
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 30.0;
    private double customPercent = 0.18;
    private TextView percentCustomTextView;
    private TextView tip15TextView;
    private TextView total15TextView;
    private TextView tipCustomTextView;
    private TextView totalCustomTextView;
    private TextView pretaxTotalTextView;
    private TextView taxPercentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15textView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);
        pretaxTotalTextView = (TextView) findViewById(R.id.pretaxTextView);
        taxPercentTextView = (TextView) findViewById(R.id.taxPercentTextView);
        updateStandard();
        updateCustom();
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.cusomTipSeekBar);
        SeekBar taxSeekBar = (SeekBar) findViewById(R.id.taxSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }
    private void updateSalesTax()

    {


    }

    private void updatePreTax()

    {


    }
    private void updateStandard()
    {
        double fifteenPercentTip = billAmount * 0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
    }
    private void updateCustom()
    {
        percentCustomTextView.setText(percentFormat.format(customPercent));
        double customTip = billAmount * customPercent;
        double customTotal = billAmount + customTip;
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
    }
    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener()
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            customPercent = progress / 100.0;
            updateCustom();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };
    private TextWatcher amountEditTextWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                billAmount = Double.parseDouble(s.toString()) / 100.0;
            }
            catch (NumberFormatException e)
            {
                billAmount = 100.0;
            }
            updateStandard();
            updateCustom();

        }
        @Override
        public void afterTextChanged(Editable s)
        {

        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
