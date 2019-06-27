package com.mikhailovskii.exercise7.ui.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;

public class PageFragment extends Fragment implements PageContract.PageView {

    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private PagePresenter presenter = new PagePresenter();

    private TextView mTvTitle;
    private TextView mTvDescription;
    private ImageView mIvContent;

    private int page;

    public static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        page = getArguments() != null ? getArguments().getInt(ARGUMENT_PAGE_NUMBER) : 0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);

        mTvTitle = view.findViewById(R.id.tv_page_title);
        mTvDescription = view.findViewById(R.id.tv_description);
        mIvContent = view.findViewById(R.id.iv_content);

        mTvTitle.setText(String.valueOf(page));

        presenter.loadImage(page);

        return view;
    }


    @Override
    public void onImageLoaded(String url, String id) {
        Glide.with(this)
                .load(url)
                .into(mIvContent);
        presenter.loadDescription(id);
    }

    @Override
    public void onImageLoadingFailed() {

    }

    @Override
    public void onDescriptionLoaded(String description) {
        mTvTitle.setText(description);
    }

    @Override
    public void onDescriptionLoginFailed() {

    }

    @Override
    public void showEmptyState(boolean value) {

    }

    @Override
    public void showLoadingIndicator(boolean value) {

    }
}
