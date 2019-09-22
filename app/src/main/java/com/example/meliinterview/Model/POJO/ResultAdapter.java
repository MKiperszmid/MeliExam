package com.example.meliinterview.Model.POJO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meliinterview.Controller.GlideController;
import com.example.meliinterview.R;

public class ResultAdapter extends RecyclerView.Adapter {
    private SearchList searchList;
    private ProductNotifier notifier;

    public ResultAdapter(SearchList searchList, ProductNotifier notifier){
        this.searchList = searchList;
        this.notifier = notifier;
    }

    public void addList(SearchList searchList){
        this.searchList.addResults(searchList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
        SearchResult searchResult = searchList.getResults().get(position);
        resultViewHolder.bind(searchResult);
    }

    @Override
    public int getItemCount() {
        if(searchList == null || searchList.getResults() == null)
            return -1;
        return searchList.getResults().size();
    }

    private class ResultViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView price;
        private TextView title;

        private ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.si_iv_imagen);
            price = itemView.findViewById(R.id.si_tv_precio);
            title = itemView.findViewById(R.id.si_tv_titulo);
        }

        private void bind(final SearchResult searchResult) {
            GlideController.loadImageFade(itemView, searchResult.getThumbnail(), image);
            price.setText(searchResult.getPriceValue());
            title.setText(searchResult.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifier.listen(searchResult.getId());
                }
            });
        }
    }
}
