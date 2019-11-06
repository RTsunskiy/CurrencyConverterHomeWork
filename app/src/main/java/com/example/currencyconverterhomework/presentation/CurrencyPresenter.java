package com.example.currencyconverterhomework.presentation;

import androidx.annotation.NonNull;

import com.example.currencyconverterhomework.data.repository.CurrencyRepository;
import com.example.currencyconverterhomework.view.ICurrencyView;

import java.lang.ref.WeakReference;

public class CurrencyPresenter {

    private final WeakReference<ICurrencyView> mMainActivityWeakReference;

    private CurrencyRepository mCurrencyRepository;

    public CurrencyPresenter(@NonNull ICurrencyView mainActivity) {
        mMainActivityWeakReference = new WeakReference<>(mainActivity);
    }


    public void loadCurrency() {

        mCurrencyRepository = new CurrencyRepository();

        CurrencyRepository.OnLoadingFinishListener onLoadingFinishListener = currencyModels -> {
            if (mMainActivityWeakReference.get() != null) {
                mMainActivityWeakReference.get().showData(currencyModels);
            }
        };

        mCurrencyRepository.loadDataAsync(onLoadingFinishListener);
    }

    public void detachView() {
        mMainActivityWeakReference.clear();
    }
}
