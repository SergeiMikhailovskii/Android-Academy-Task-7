package com.mikhailovskii.exercise7.ui.preview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.ui.adapter.PreviewAdapter;

import java.util.List;

public class PreviewFragment extends Fragment implements PreviewContract.PreviewView {

    public static final String EXTRA_ID = "EXTRA_ID";

    private PreviewPresenter mPresenter = new PreviewPresenter();

    private TextView mBtnClose;

    private RecyclerView mListPreview;
    private PreviewAdapter mAdapter;

    private String mId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mId = getArguments().getString(EXTRA_ID);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        mPresenter.attachView(this);

        mBtnClose = view.findViewById(R.id.btn_close);
        mListPreview = view.findViewById(R.id.list_preview);
        mListPreview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new PreviewAdapter();
        mListPreview.setAdapter(mAdapter);

        mPresenter.loadPreviewList(mId);

        return view;
    }

    @Override
    public void onPreviewListLoaded(List<Photo> photos) {
        mAdapter.setData(photos);
    }

    @Override
    public void onPreviewListLoadingFailed() {
        Toast.makeText(getContext(), "Preview list loading failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyState(boolean value) {

    }

    @Override
    public void showLoadingIndicator(boolean value) {

    }

}
