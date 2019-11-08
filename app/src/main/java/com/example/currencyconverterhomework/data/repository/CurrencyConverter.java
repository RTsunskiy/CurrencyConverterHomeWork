package com.example.currencyconverterhomework.data.repository;

import com.example.currencyconverterhomework.data.model.Currency;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyConverter {

    private List<Currency> mListOfCurrency;

    public CurrencyConverter(List<Currency> listOfCurrency) {
        this.mListOfCurrency = listOfCurrency;
    }

    public String getResultOfConversion(int mSpinnerFromPosition, int mSpinnerToPosition, float mAmount) {
        Currency fromCurrency = mListOfCurrency.get(mSpinnerFromPosition);
        Currency toCurrency = mListOfCurrency.get(mSpinnerToPosition);

        return "Вы получите: " + toCurrency.getValue().divide(fromCurrency.getValue(), 5).multiply(BigDecimal.valueOf(mAmount)) + " "
                + toCurrency.getCharCode();
    }
}
