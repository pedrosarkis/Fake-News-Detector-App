package com.pedro.fakenewsdetector.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.fakenewsdetector.R;
import com.pedro.fakenewsdetector.model.FakeNewsModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FakeNewsHistoryAdapter extends RecyclerView.Adapter<FakeNewsHistoryAdapter.FakeNewsHistoryViewHolder> {
    static class FakeNewsHistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView date;
        private final ImageView fakeNews;
        private final ImageView share;

        public FakeNewsHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            date = itemView.findViewById(R.id.txt_date);
            fakeNews = itemView.findViewById(R.id.img_fake);
            share = itemView.findViewById(R.id.img_share);
        }

        public void bind(FakeNewsModel fakeNewsModel) {
            boolean isFakeNews = fakeNewsModel.getScore() <= 0.5;
            title.setText(fakeNewsModel.getTitle());
            String dateText = new SimpleDateFormat("dd/MM/yyyy, [] hh:mm", new Locale("pt")).format(fakeNewsModel.getCreatedAt());
            date.setText(dateText.replace("[]", "às"));
            fakeNews.setImageResource(isFakeNews ? R.drawable.img_fake : R.drawable.img_true);

            share.setOnClickListener(v -> {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = fakeNewsModel.getUrl();
                String shareSub = isFakeNews ? v.getContext().getString(R.string.fake_new) : v.getContext().getString(R.string.true_new);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                itemView.getContext().startActivity(Intent.createChooser(sharingIntent, "Compartilhar usando"));
            });
        }
    }

    private List<FakeNewsModel> items = new ArrayList<>();

    @NonNull
    @Override
    public FakeNewsHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_fake_news_history, parent, false);
        return new FakeNewsHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeNewsHistoryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void refresh(List<FakeNewsModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
