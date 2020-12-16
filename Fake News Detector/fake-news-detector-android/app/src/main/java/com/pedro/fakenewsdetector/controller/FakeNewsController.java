package com.pedro.fakenewsdetector.controller;

import android.util.Log;
import android.widget.Toast;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.api.service.FakeNewsService;
import com.pedro.fakenewsdetector.api.service.ParseUrlService;
import com.pedro.fakenewsdetector.model.FakeNewsModel;
import com.pedro.fakenewsdetector.model.SystemModel;
import com.pedro.fakenewsdetector.model.dao.FakeNewsDAO;
import com.pedro.fakenewsdetector.model.dao.SystemDAO;
import com.pedro.fakenewsdetector.view.FakeNewsActivity;

import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;

public class FakeNewsController {
    public interface OnSearchFakeNews {
        void run(FakeNewsModel model);
    }

    private FakeNewsActivity view;
    private ParseUrlService parseUrlService;
    private FakeNewsService fakeNewsService;
    private FakeNewsDAO fakeNewsDAO;
    private SystemDAO systemDAO;

    public FakeNewsController(FakeNewsActivity view) {
        this.view = view;
    }

    public void init() {
        parseUrlService = new ParseUrlService();
        fakeNewsService = new FakeNewsService();
        fakeNewsDAO = new FakeNewsDAO(view);
        systemDAO = new SystemDAO(view);
    }

    public void checkFakeNews(String title, String url, String language, OnSearchFakeNews callback) {
        parseUrlService.parse(url, data1 -> {
            if(data1.getContent() == null) {
                Toast.makeText(view, R.string.error_parse_url, Toast.LENGTH_LONG).show();
                return;
            }
            FakeNewsModel model = new FakeNewsModel(url, title, language, data1.getContent(), new Date(), 0.0);
            if(language.equals("pt")) {
                fakeNewsService.checkPortuguese(data1.getContent(), data2 -> {
                    model.setScore(data2.getPrediction());
                    Log.d("fake_news_detector", fakeNewsDAO.list().toString());
                    callback.run(model);
                }, this::onApiError);
            } else {
                fakeNewsService.checkEnglish(data1.getContent(), data2 -> {
                    model.setScore(data2.getPrediction());
                    Log.d("fake_news_detector", fakeNewsDAO.list().toString());
                    callback.run(model);
                }, this::onApiError);
            }
        }, this::onApiError);
    }

    private void onApiError(int code, ResponseBody body, String errorBody) {
        Toast.makeText(view, R.string.error_predict, Toast.LENGTH_LONG).show();
        Log.e("fake_news_detector", String.format("Error: code(%d), body(%s), errorBody(%s)", code, body, errorBody));
    }

    public boolean shownTutorial() {
        return systemDAO.get().isTutorialSeen();
    }

    public void setTutorialAsShown() {
        SystemModel obj = systemDAO.get();
        obj.setTutorialSeen(true);
        systemDAO.update(obj);
    }

    public List<FakeNewsModel> getAllFakeNews() {
        return fakeNewsDAO.list();
    }

    public void saveFakeNews(FakeNewsModel fakeNewsModel) {
        fakeNewsDAO.create(fakeNewsModel);
    }
}
