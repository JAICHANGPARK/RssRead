package org.rssread.jaichangpark.rssread;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JAICHANGPARK on 5/20/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<FeedItem>feedItems;
    Context context;
    public MyAdapter(Context context, ArrayList<FeedItem>feedItems){

        this.feedItems=feedItems;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custum_row_news_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);      //myviewholer 오브젝드 생성 holder란 이름을 준다
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final FeedItem current=feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        Picasso.with(context).load(current.getThumbnailUrl()).into(holder.Thumnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewsDetails.class);
                intent.putExtra("Link",current.getLink());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView Thumnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
