package com.pedro.fakenewsdetector.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.controller.FakeNewsController;

import java.util.Objects;

public class FakeNewsSearchFragment extends Fragment {
    private FakeNewsController controller;
    private EditText title;
    private EditText url;
    private RadioGroup language;
    private TextView textFound;
    private TextView content;
    private ImageView fakeNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fake_news_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.input_title);
        url = view.findViewById(R.id.input_url);
        language = view.findViewById(R.id.input_language);
        content = view.findViewById(R.id.txt_content);
        textFound = view.findViewById(R.id.txt_text_found);
        fakeNews = view.findViewById(R.id.img_fake_news_result);
        confSearchButton(view);
        confClearButton(view);
    }

    private void confClearButton(View view) {
        view.findViewById(R.id.btn_clear).setOnClickListener(v -> {
            textFound.setVisibility(View.GONE);
            content.setVisibility(View.GONE);
            fakeNews.setVisibility(View.GONE);
            hideKeyboard();
            title.setText("");
            url.setText("");
        });
    }

    private void confSearchButton(View view) {
        if(getActivity() instanceof FakeNewsActivity) {
            controller = ((FakeNewsActivity) getActivity()).getController();

            view.findViewById(R.id.btn_search).setOnClickListener(v -> {
                String titleText = title.getText().toString();
                String urlText = url.getText().toString();
                String languageText = language.getCheckedRadioButtonId() == R.id.input_pt ? "pt" : "en";

                if (titleText.isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.error_title), Toast.LENGTH_LONG).show();
                    return;
                }

                if (urlText.isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.error_url), Toast.LENGTH_LONG).show();
                    return;
                }

                view.findViewById(R.id.loading).setVisibility(View.VISIBLE);
                textFound.setVisibility(View.GONE);
                content.setVisibility(View.GONE);
                fakeNews.setVisibility(View.GONE);
                hideKeyboard();

                controller.checkFakeNews(titleText, urlText, languageText, fakeNewsModel -> {
                    content.setText(fakeNewsModel.getContent());
                    fakeNews.setImageResource(fakeNewsModel.getScore() > 0.5 ? R.drawable.img_true : R.drawable.img_fake);
                    view.findViewById(R.id.loading).setVisibility(View.GONE);
                    textFound.setVisibility(View.VISIBLE);
                    content.setVisibility(View.VISIBLE);
                    fakeNews.setVisibility(View.VISIBLE);

                    controller.saveFakeNews(fakeNewsModel);
                });
            });
        }
    }

    private void hideKeyboard() {
        View view = Objects.requireNonNull(this.getActivity()).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
