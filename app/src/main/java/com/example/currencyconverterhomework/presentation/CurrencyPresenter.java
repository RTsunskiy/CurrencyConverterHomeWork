package com.example.currencyconverterhomework.presentation;

import androidx.annotation.NonNull;

import com.example.currencyconverterhomework.data.repository.ConversionRate;
import com.example.currencyconverterhomework.data.repository.CurrencyConverter;
import com.example.currencyconverterhomework.data.repository.CurrencyRepository;
import com.example.currencyconverterhomework.view.ICurrencyView;

import java.lang.ref.WeakReference;

public class CurrencyPresenter {

    private final WeakReference<ICurrencyView> mMainActivityWeakReference;

    private CurrencyRepository mCurrencyRepository;
    private ConversionRate mConversionRate;
    private CurrencyConverter mCurrencyConverter;

    public CurrencyPresenter(@NonNull ICurrencyView mainActivity) {
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
        mCurrencyRepository = new CurrencyRepository();
    }


    public void loadCurrency() {
        CurrencyRepository.OnLoadingFinishListener onLoadingFinishListener = currencyModels -> {
            if (mMainActivityWeakReference.get() != null) {
                mConversionRate = new ConversionRate(currencyModels);
                mCurrencyConverter = new CurrencyConverter(currencyModels);
                mMainActivityWeakReference.get().showData(currencyModels);
            }
        };

        mCurrencyRepository.loadDataAsync(onLoadingFinishListener);
    }

    public String loadConversion(int mSpinnerFrom, int mSpinnerTo) {
        return mConversionRate.getConversion(mSpinnerFrom, mSpinnerTo);
    }

    public String loadConvertedValue(int mSpinnerFrom, int mSpinnerTo, float mAmount) {
        return mCurrencyConverter.getResultOfConversion(mSpinnerFrom, mSpinnerTo, mAmount);
    }

    public void detachView() {
        mMainActivityWeakReference.clear();
    }
}
