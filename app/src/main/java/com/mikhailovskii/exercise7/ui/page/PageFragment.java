package com.mikhailovskii.exercise7.ui.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhailovskii.exercise7.R;
import com.mikhailovskii.exercise7.ui.main.MainPresenter;

public class PageFragment extends Fragment {

    public static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private MainPresenter mPresenter = new MainPresenter(this);

    private TextView mTvTitle;
    private TextView mTvDescription;
    private ImageView mIvContent;

    private static int pageNumber;

    public static PageFragment newInstance(int page) {
        pageNumber = page;
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);

        mTvTitle = view.findViewById(R.id.tv_page_title);
        mTvDescription = view.findViewById(R.id.tv_description);
        mIvContent = view.findViewById(R.id.iv_content);

        return view;
    }

    public void onImageLoaded(String url){

    }

}
