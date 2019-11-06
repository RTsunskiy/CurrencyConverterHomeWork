package com.example.currencyconverterhomework.view;

import androidx.annotation.NonNull;

import com.example.currencyconverterhomework.data.model.Currency;
import com.example.currencyconverterhomework.data.model.CurrencyData;

import java.util.List;

public interface ICurrencyView {
    void showData(@NonNull List<Currency> modelList);
}
