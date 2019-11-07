package com.example.currencyconverterhomework.data.repository;


import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.currencyconverterhomework.data.model.CurrenciesData;
import com.example.currencyconverterhomework.data.model.Currency;
import com.example.currencyconverterhomework.data.model.CurrencyData;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class CurrencyRepository {

    private static HttpURLConnection connection;
    private static Strategy strategy = new AnnotationStrategy();
    private static Serializer serializer = new Persister(strategy);
    private static CurrenciesData currencies;

    public void loadDataAsync(@NonNull OnLoadingFinishListener onLoadingFinishListener) {
        LoadCurrency loadingCurrencyAsyncTask = new LoadCurrency(onLoadingFinishListener);
        loadingCurrencyAsyncTask.execute();
    }

    private static class LoadCurrency extends AsyncTask<Void, Void, List<CurrencyData>> {

        private final OnLoadingFinishListener mOnLoadingFinishListener;

        LoadCurrency(@NonNull OnLoadingFinishListener onLoadingFinishListener) {
            mOnLoadingFinishListener = onLoadingFinishListener;
        }

        @Override
        protected List<CurrencyData> doInBackground(Void... voids) {
            return loadFromWeb();
        }

        @Override
        protected void onPostExecute(List<CurrencyData> currencyData) {
            mOnLoadingFinishListener.onFinish(convert(currencyData));
        }
    }

    private static List<CurrencyData> loadFromWeb() {
        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = null;
            inputStream = connection.getInputStream();
            currencies = serializer.read(CurrenciesData.class, inputStream);
        } finally {
            connection.disconnect();
            return new ArrayList<>(currencies.getCurrencies());
        }
    }

    private static List<Currency> convert(@NonNull List<CurrencyData> currencies) {
        List<Currency> result = new ArrayList<>();
        for (CurrencyData currency : currencies) {
            result.add(new Currency(
                    currency.getId(),
                    currency.getCharCode(),
                    currency.getNominal(),
                    currency.getName(),
                    currency.getValue()
            ));
        }
        return result;
    }

    public interface OnLoadingFinishListener {
        void onFinish(List<Currency> currencyModels);
    }

}
