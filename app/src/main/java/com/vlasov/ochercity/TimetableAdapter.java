package com.vlasov.ochercity;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.iconics.view.IconicsImageView;
import com.vlasov.helpers.AspectRatioImageView;
import com.vlasov.helpers.PrettyTime;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Timetable> timetables;
    Context context;
    private AdapterView.OnItemClickListener onItemClickListener;
    Typeface robotoMedium, robotoRegular;

    TimetableAdapter(List<Timetable> timetables, AdapterView.OnItemClickListener onItemClickListener, Context context) {
        this.timetables = timetables;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        robotoMedium = Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
        robotoRegular = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
    }

    /**
     * Add items to adapter
     *
     * @param timetables
     */
    public void addItems(List<Timetable> timetables) {
        this.timetables.addAll(timetables);
    }

    /**
     * Holds the news screen elements to avoid creating them multiple times
     */
    public class HeadlineTimetableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView title, submission_date;
        AspectRatioImageView image;
        IconicsImageView video;

        HeadlineTimetableViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            image = (AspectRatioImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            video = (IconicsImageView) itemView.findViewById(R.id.video);
            submission_date = (TextView) itemView.findViewById(R.id.submission_date);


            //set image on click listener
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }

    /**
     * Holds the news screen elements to avoid creating them multiple times
     */
    public class TimetableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView title, submission_date;
        AspectRatioImageView image;
        IconicsImageView video;

        TimetableViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            image = (AspectRatioImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            video = (IconicsImageView) itemView.findViewById(R.id.video);
            submission_date = (TextView) itemView.findViewById(R.id.submission_date);


            //set image on click listener
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //passing the clicked position to the parent class
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }


    @Override
    public int getItemCount() {
        return timetables.size();
    }

    @Override
    public int getItemViewType(int position) {
        return timetables.get(position).is_headline;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = null;

        if (viewType == Timetable.HEADLINE) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_headline_1columns, viewGroup, false);

            RecyclerView.ViewHolder rvh = new HeadlineTimetableViewHolder(v);
            return rvh;
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_not_headline_1column, viewGroup, false);
            RecyclerView.ViewHolder rvh = new TimetableViewHolder(v);
            return rvh;
        }

    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        if (timetables.get(i).is_headline == Timetable.HEADLINE) {

            //set news name
            ((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).title.setTypeface(robotoRegular);
            ((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).title.setText(timetables.get(i).name);

            //date
            String DateToStr = PrettyTime.getTimeAgo(timetables.get(i).submission_date, "yyyy-MM-dd HH:mm:ss", context);
            ((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).submission_date.setText(DateToStr);


            //video
            if (timetables.get(i).has_video == 1) {
                ((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).video.setVisibility(View.VISIBLE);
            } else {
                ((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).video.setVisibility(View.GONE);
            }

            //load places image with picasso
            RequestCreator r;
            if (timetables.get(i).imageUrl != null && timetables.get(i).imageUrl.length > 0 && timetables.get(i).imageUrl[0] != null && timetables.get(i).imageUrl[0].length() > 0)
                r = Picasso.with(context).load(timetables.get(i).imageUrl[0]).placeholder(R.drawable.loading);
            else {
                r = Picasso.with(context).load(R.drawable.loading);
            }
            r.into(((TimetableAdapter.HeadlineTimetableViewHolder) viewHolder).image);


        } else {
            //set news name
            ((TimetableAdapter.TimetableViewHolder) viewHolder).title.setTypeface(robotoRegular);
            ((TimetableAdapter.TimetableViewHolder) viewHolder).title.setText(timetables.get(i).name);

            //date
            String DateToStr = PrettyTime.getTimeAgo(timetables.get(i).submission_date, "yyyy-MM-dd HH:mm:ss", context);
            ((TimetableAdapter.TimetableViewHolder) viewHolder).submission_date.setText(DateToStr);

            //video
            if (timetables.get(i).has_video == 1) {
                ((TimetableAdapter.TimetableViewHolder) viewHolder).video.setVisibility(View.VISIBLE);
            } else {
                ((TimetableAdapter.TimetableViewHolder) viewHolder).video.setVisibility(View.GONE);
            }

            //set image as box when in 2 column mode
            ((TimetableAdapter.TimetableViewHolder) viewHolder).image.setBox(true);


            //load places image with picasso
            RequestCreator r;
            if (timetables.get(i).imageUrl != null && timetables.get(i).imageUrl.length > 0 && timetables.get(i).imageUrl[0] != null && timetables.get(i).imageUrl[0].length() > 0)
                r = Picasso.with(context).load(timetables.get(i).imageUrl[0]).placeholder(R.drawable.loading);
            else {
                r = Picasso.with(context).load(R.drawable.loading);
                System.out.println("empty");
            }
            r.fit().centerCrop();
            r.into(((TimetableAdapter.TimetableViewHolder) viewHolder).image);

        }


    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
