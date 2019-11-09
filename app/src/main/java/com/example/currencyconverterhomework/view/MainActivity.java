package com.example.currencyconverterhomework.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverterhomework.R;
import com.example.currencyconverterhomework.data.model.Currency;
import com.example.currencyconverterhomework.presentation.CurrencyPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ICurrencyView {

    private CurrencyPresenter mMainPresenter = new CurrencyPresenter(this);


    private List<com.example.currencyconverterhomework.data.model.Currency> currency;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private EditText mFromAmount;
    private TextView mConvertedText;
    private TextView mConversionRate;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mMainPresenter.loadCurrency();
    }

    public void showData(@NonNull List<Currency> modelList) {
        currency = modelList;
        adapter = new CurrencyAdapter(currency);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerTo.setAdapter(adapter);
    }

    private void initViews() {
        mSpinnerFrom = findViewById(R.id.spinnerFrom);
        mSpinnerFrom.setAdapter(adapter);
        mSpinnerTo = findViewById(R.id.spinnerTo);
        findViewById(R.id.convert).setOnClickListener(v -> {
            mConvertedText.setText(mMainPresenter.loadConvertedValue(mSpinnerFrom.getSelectedItemPosition(),
                    mSpinnerTo.getSelectedItemPosition(),
                    Float.valueOf(mFromAmount.getText().toString())));
        });

        mFromAmount = findViewById(R.id.fromAmount);
        mConvertedText = findViewById(R.id.convertedText);
        mConversionRate = findViewById(R.id.conversionRate);
        mSpinnerFrom.setOnItemSelectedListener(new OnCurrencySelectedListener());
        mSpinnerTo.setOnItemSelectedListener(new OnCurrencySelectedListener());
    }

    private class OnCurrencySelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           mConversionRate.setText(mMainPresenter.loadConversion(mSpinnerFrom.getSelectedItemPosition(),
                   mSpinnerTo.getSelectedItemPosition()));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
