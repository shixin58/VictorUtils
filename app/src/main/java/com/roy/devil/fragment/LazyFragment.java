package com.roy.devil.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.max.baselib.BaseFragment;
import com.roy.devil.R;
import com.roy.devil.widget.LazyFragmentPagerAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>Created by shixin on 2018/6/4.
 */
public class LazyFragment extends BaseFragment implements LazyFragmentPagerAdapter.Deferrable {

    private static final String TAG_BLANK = "tag_blank";

    private int mPosition;

    public static LazyFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        LazyFragment fragment = new LazyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LazyFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPosition = getArguments().getInt("position");
        Log.i("lifecycle", "onAttach "+mPosition);
    }

    @Override
    public void onDetach() {
        Log.i("lifecycle", "onDetach "+mPosition);
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lazy, container, false);
        ButterKnife.bind(this, view);
        Log.i("lifecycle", "onCreateView "+mPosition);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle", "onActivityCreated "+mPosition);
        initData();
        initView();
    }

    @Override
    public void onDestroyView() {
        Log.i("lifecycle", "onDestroyView "+mPosition);
        super.onDestroyView();
    }

    private void initData() {
    }

    private void initView() {
        TextView tvPosition = getView().findViewById(R.id.tv_page);
        tvPosition.setText("No."+mPosition);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("Max", getArguments().getInt("position")+" setUserVisibleHint-"+isVisibleToUser);
    }

    @OnClick(R.id.tv_page) void onPositionClick() {
        Fragment fragment = getChildFragmentManager().findFragmentByTag(TAG_BLANK+"_"+mPosition);
        if(fragment != null && !fragment.isRemoving()) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commitAllowingStateLoss();
            return;
        }

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        BlankFragment blankFragment = BlankFragment.newInstance();
        transaction.add(R.id.fragment_container, blankFragment, TAG_BLANK+"_"+mPosition);
        transaction.commitAllowingStateLoss();
    }
}
