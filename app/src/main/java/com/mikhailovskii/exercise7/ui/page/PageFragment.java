package com.mikhailovskii.exercise7.ui.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.ui.adapter.PreviewAdapter;

import java.util.List;

public class PageFragment extends Fragment implements PageContract.PageView {

    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    private PagePresenter presenter = new PagePresenter();

    private TextView mTvTitle;
    private TextView mTvDescription;
    private ImageView mIvContent;
    private ImageButton mBtnClose;
    private RecyclerView mListPreview;
    private ViewFlipper mViewFlipper;
    private Button mBtnViewCollection;


    private PreviewAdapter mAdapter;

    private int page;
    private String mId;

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

        mViewFlipper = view.findViewById(R.id.view_flipper);
        mTvTitle = view.findViewById(R.id.tv_page_title);
        mTvDescription = view.findViewById(R.id.tv_description);
        mIvContent = view.findViewById(R.id.iv_content);
        mBtnClose = view.findViewById(R.id.btn_close);
        mBtnViewCollection = view.findViewById(R.id.btn_view_collection);
        mListPreview = view.findViewById(R.id.list_preview);

        mAdapter = new PreviewAdapter();

        mListPreview.setAdapter(mAdapter);

        mIvContent.setOnClickListener(v -> {
            mViewFlipper.showNext();
            presenter.loadPreviewPhotos(mId);
        });

        mBtnViewCollection.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://unsplash.com/collections/" + mId))));

        mBtnClose.setOnClickListener(v -> mViewFlipper.showPrevious());

        mTvTitle.setText(String.valueOf(page));

        mListPreview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        presenter.loadCollection(page);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onImageLoaded(String url, String title, String description, String id) {
        Glide.with(this)
                .load(url)
                .into(mIvContent);
        mTvTitle.setText(title);
        mTvDescription.setText(description);
        mId = id;
    }

    @Override
    public void onImageLoadingFailed() {
        Toast.makeText(getContext(), getString(R.string.image_loading_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreviewLoaded(List<Photo> photos) {
        mAdapter.updateData(photos);
        Toast.makeText(getContext(), getString(R.string.preview_loaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPreviewLoadingFailed() {
        Toast.makeText(getContext(), getString(R.string.preview_loading_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyState(boolean value) {

    }

    @Override
    public void showLoadingIndicator(boolean value) {

    }
}
