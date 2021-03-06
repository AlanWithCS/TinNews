package com.laioffer.tinnews.tin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.common.TinBasicFragment;
import com.laioffer.tinnews.retrofit.response.News;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TinGalleryFragment extends TinBasicFragment implements TinNewsCard.OnSwipeListener {
    private SwipePlaceHolderView mSwipeView;

    public static TinGalleryFragment newInstance() {

        Bundle args = new Bundle();

        TinGalleryFragment fragment = new TinGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tin_gallery, container, false);

        mSwipeView = view.findViewById(R.id.swipeView);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tin_news_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tin_news_swipe_out_msg_view));

        view.findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        view.findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.image = "https://i.ytimg.com/vi/BgIJ45HKDpw/maxresdefault.jpg";
            news.title = "Sample news";
            news.description = "This is a sample news";
            TinNewsCard tinNewsCard = new TinNewsCard(news, mSwipeView, this);
            mSwipeView.addView(tinNewsCard);
        }

        return view;
    }

    @Override
    public void onLike(News news) {
        Toast.makeText(getContext(), "I like the news", Toast.LENGTH_LONG).show();
    }
}
