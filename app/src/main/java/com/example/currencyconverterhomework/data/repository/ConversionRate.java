package com.example.currencyconverterhomework.data.repository;

import com.example.currencyconverterhomework.data.model.Currency;

import java.util.List;

public class ConversionRate {

    private List<Currency> mListOfCurrency;

    public ConversionRate(List<Currency> listOfCurrency) {
        this.mListOfCurrency = listOfCurrency;
    }

    public String getConversion(int mSpinnerFromPosition, int mSpinnerToPosition) {
        Currency fromCurrency = mListOfCurrency.get(mSpinnerFromPosition);
        Currency toCurrency = mListOfCurrency.get(mSpinnerToPosition);

        return "Курс конверсии: " + fromCurrency.getValue().divide(toCurrency.getValue(), 5).toString() + " "
                + fromCurrency.getCharCode() + "/" + toCurrency.getCharCode();
    }
}
