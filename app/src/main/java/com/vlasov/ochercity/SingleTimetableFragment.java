package com.vlasov.ochercity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.mikepenz.iconics.view.IconicsTextView;
import com.vlasov.helpers.OnSwipeTouchListener;
import com.vlasov.helpers.PrettyTime;
import com.squareup.picasso.Picasso;

import static com.vlasov.ochercity.R.id.descriptionView;

public class SingleTimetableFragment extends Fragment {

    TextView titleView, dateView;
    WebView textView;
    Timetable timetable;
    PullToZoomScrollViewEx scrollView;
    ImageView propertyImage;
    IconicsTextView photosView;

    int ItemId;
    boolean isFeatureImage = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewGroup fragmentContentView;
        ViewGroup contentView;

        //is feature image enabled
        isFeatureImage = Preference.getCached(getContext(), "showfeatureimage").equals("1");

        if (isFeatureImage) {
            //Feature image is enabled so show image on top
            fragmentContentView = (ViewGroup) inflater.inflate(R.layout.fragment_single_news_feature_img, container, false);

            //set zoom, content and header view
            scrollView = (PullToZoomScrollViewEx) fragmentContentView.findViewById(R.id.scroll_view);
            View headView = LayoutInflater.from(getContext()).inflate(R.layout.article_head_view, null, false);
            View zoomView = LayoutInflater.from(getContext()).inflate(R.layout.article_zoom_view, null, false);
            contentView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.article_content_view, null, false);
            scrollView.setHeaderView(headView);
            scrollView.setZoomView(zoomView);
            scrollView.setScrollContentView(contentView);

            //set aspect ratio of header image
            DisplayMetrics localDisplayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
            int mScreenWidth = localDisplayMetrics.widthPixels;
            LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
            scrollView.setHeaderLayoutParams(localObject);

            //get article image element
            propertyImage = (ImageView) zoomView.findViewById(R.id.image);

            //handle image swipe/clicks
            propertyImage.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {

                @Override
                public void onClick() {
                    openFullScreenImage();
                }

                @Override
                public void onSwipeLeft() {

                }

                @Override
                public void onSwipeRight() {
                }
            });

            //photos counter
            photosView = (IconicsTextView) headView.findViewById(R.id.photos);
        } else {
            //feature image not enabled. So just show the content view.
            fragmentContentView = contentView = (ViewGroup) inflater.inflate(R.layout.article_content_view, container, false);
        }


        //get article prices elements
        titleView = (TextView) contentView.findViewById(R.id.title);
        dateView = (TextView) contentView.findViewById(R.id.dateView);

        //set description view
        textView = (WebView) contentView.findViewById(descriptionView);
        textView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
            }
        });
        //enable javascript
        textView.getSettings().setJavaScriptEnabled(true);
        textView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("http")) {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });

        //get item
        ItemId = getArguments().getInt(SingleTimetableActivity.ITEM_KEY);

        //comment button
        final Button commentsBtn = (Button) contentView.findViewById(R.id.commentsBtn);
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // launching Facebook comments activity
                Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.putExtra("url", "http://" + getResources().getString(R.string.deep_link) + "/" + ItemId);
                startActivity(intent);
            }
        });

        //load article
        Timetable.loadSingle(getActivity(), ItemId, new Timetable.onDownloadedListener() {
            @Override
            public void onDownloaded(Timetable timetableLocal) {
                if (getActivity() != null) {
                    timetableLocal.viewed(getContext());
                    timetable = timetableLocal;

                    //set title and text
                    titleView.setText(timetable.name);
                    textView.getSettings().setBuiltInZoomControls(true);
                    textView.loadData(Functions.HTMLTemplate(timetable.text), "text/html; charset=utf-8", "utf-8");

                    //author
                    String author = "";
                    if (Preference.getCached(getContext(), "showauthorname").equals("1")) {
                        if (timetable.authorName.length() > 0)
                            author = " - " + timetable.authorName;
                    }

                    //date
                    String DateToStr = PrettyTime.getTimeAgo(timetable.submission_date, "yyyy-MM-dd HH:mm:ss", getActivity());
                    dateView.setText(DateToStr + author);

                    if (timetable.allowComments == 1)
                        commentsBtn.setVisibility(View.VISIBLE);
                    else
                        commentsBtn.setVisibility(View.GONE);



                    //set image if in feature mode
                    if (isFeatureImage) {
                        //set image
                        if (timetable.imageUrl != null) {
                            Picasso.with(getContext())
                                    .load(timetable.imageUrl[0])
                                    .fit()
                                    .placeholder(R.drawable.loading)
                                    .into(propertyImage);
                        }

                        // photos count
                        if (timetable.imageUrl != null)
                            photosView.setText(String.format(getResources().getString(R.string.photos_count), "" + timetable.imageUrl.length));
                    }
                }
            }
        });
        return fragmentContentView;
    }


    /**
     * Open images in full screen mode
     */
    public void openFullScreenImage() {
        Bundle b = new Bundle();
        b.putStringArray("imageUrl", timetable.imageUrl);
        b.putInt("slideshow_seconds", Configurations.SLIDESHOW_TIME_SECONDS);
        Intent i = new Intent(getContext(), FullScreenImage.class);
        i.putExtras(b);
        startActivity(i);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_news_menu, menu);

//        //set share icon from FontAwsome
//        menu.findItem(R.id.share).setIcon(
//                new IconicsDrawable(getContext())
//                        .icon(FontAwesome.Icon.faw_share_alt)
//                        .color(ContextCompat.getColor(getContext(), R.color.md_white_1000))
//                        .sizeDp(18));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle share
        switch (item.getItemId()) {
//            case R.id.share:
//                NewsArticle.shareById(getActivity(), ItemId);
//                return true;
//
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPause() {
        textView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        textView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        textView.destroy();
    }
}
