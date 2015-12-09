package com.testing.package;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by @pablopantaleon on 11/7/15.
 *
 * Abstract base list adapter.
 */
public abstract class BaseListAdapter<T, B extends BaseListAdapter.ListBaseViewHolder> extends RecyclerView.Adapter<B>  {

    public List<T> items;
    public Fragment fragment;

    private RecyclerViewListener mRecyclerViewListener;

    public BaseListAdapter(Fragment fragment, List<T> items) {
        this.fragment = fragment;
        this.items = items;

        if (fragment instanceof RecyclerViewListener) {
            this.mRecyclerViewListener = (RecyclerViewListener) fragment;
        }
    }

    @Override
    public B onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(B holder, int position) {
        final T item = items.get(position);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerViewListener != null) {
                    mRecyclerViewListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ListBaseViewHolder extends RecyclerView.ViewHolder {
        View root;

        public ListBaseViewHolder(View view) {
            super(view);
            root = view;
        }
    }

    public interface RecyclerViewListener<T> {
        void onItemClick(T item);
    }

    public abstract @LayoutRes int getLayoutId();

    public abstract B getViewHolder(View view);
}
