package com.example.currencyconverterhomework.common;

import com.example.currencyconverterhomework.data.model.Currency;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class CurrencyConverter {

    private List<Currency> mListOfCurrency;

    public CurrencyConverter(List<Currency> listOfCurrency) {
        this.mListOfCurrency = listOfCurrency;
    }

    public String getResultOfConversion(int mSpinnerFromPosition, int mSpinnerToPosition, float mAmount) {
        Currency fromCurrency = mListOfCurrency.get(mSpinnerFromPosition);
        Currency toCurrency = mListOfCurrency.get(mSpinnerToPosition);

        BigDecimal result = BigDecimal.valueOf(mAmount)
                .multiply(fromCurrency.getValue())
                .multiply(new BigDecimal(toCurrency.getNominal()))
                .divide(toCurrency.getValue(), 2, RoundingMode.HALF_UP)
                .divide(new BigDecimal(fromCurrency.getNominal()), 2, RoundingMode.HALF_UP);

        return "Вы получите: " + result + " "
                + toCurrency.getCharCode();
    }
}
