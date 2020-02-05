package com.aaif_seriex.flo.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aaif_seriex.flo.R;
import com.aaif_seriex.flo.model.Post;
import com.aaif_seriex.flo.util.ImageLoader;
import com.aaif_seriex.flo.util.SendCodeCallback;
import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.ArrayList;

public class CommunityDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SendCodeCallback.ResponseCallback{
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private static final String TAG = "CommunityDataAdapter";
    public static ArrayList<Post> post;
    private Context myContext;
    private ImageLoader imgLoader;
    private static ItemClickListener itemClickListener;
    private static ImageView imgAprove,imgRemove,imgEdit,img_facebook,img_google,img_whatsapp;

    public TextView txt_no_of_post;
    public static TextView txt_view;
    int no_post = 0;
    SendCodeCallback sc;
    public static String views ;


    public CommunityDataAdapter(ArrayList<Post> post, Context myContext, @NonNull ItemClickListener listener) {
        this.post = post;
        this.myContext = myContext;
        imgLoader = new ImageLoader(this.myContext);
        this.itemClickListener = listener;
        sc = new SendCodeCallback(this.myContext,this::onResponseReceived);
    }

    @Override
    public void onResponseReceived(int status, String result) throws JSONException {

    }

    public interface ItemClickListener {
        void onItemClicked(View view, int i, Post m, String action);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /*View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row,null);

        return new ViewHolder(view);*/
       // Log.d(TAG, "onCreateViewHolder: View Type " + i);
        RecyclerView.ViewHolder vh;


        if(i==VIEW_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.community_row, viewGroup, false);

            vh = new InfoViewHolder(v);
        }else {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.loadmore_progress, viewGroup, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }
    private int ij;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ij=i;
        int  myviews = 0;
        if(viewHolder instanceof InfoViewHolder){
           // views = "";
           // sc.am_getviews(videos.get(i).getId(),myContext);
            //((InfoViewHolder)viewHolder).mTextView.setText(mDataset.get(position));
            Log.d(TAG, "onBindViewHolder: views ---- "+post.get(i).getId());
            Log.d(TAG, "onBindViewHolder: ----imitial viesw  "+ post.get(i).getNo_watched());
            ((InfoViewHolder)viewHolder).title.setText(post.get(i).getTitle());
            ((InfoViewHolder)viewHolder).description.setText(post.get(i).getDescription());

            //((InfoViewHolder)viewHolder).description.setText(videos.get(i).getDescription());
            //imgLoader.DisplayImage(videos.get(i).getImg(), viewHolder.img);
            Glide.with(this.myContext).load(post.get(i).getImg()).into(((InfoViewHolder)viewHolder).img);

        }else{
            ((ProgressViewHolder)viewHolder).progressBar.setIndeterminate(true);
        }
       // Log.d(TAG, "onBindViewHolder: " + i);
    }

    @Override
    public int getItemCount() {
        try{
            Log.d(TAG, "getItemCount::;;  "+post.size());
            no_post = post.size();
            return post.size();
        }catch (Exception ex){return -1;}
    }


    @Override
    public int getItemViewType(int position) {
        return post.get(position)!=null? VIEW_ITEM: VIEW_PROG;
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder {
       // private final TextView txt_no_of_post;
        private TextView title,description;
        private ImageView img,imgplay;
        private RelativeLayout rPlay;

        public InfoViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);
            img         = (ImageView) view.findViewById(R.id.img);
            imgRemove   = (ImageView) view.findViewById(R.id.remove_row);
            imgEdit     = (ImageView) view.findViewById(R.id.edit_row);
            imgAprove   = (ImageView) view.findViewById(R.id.pending_row);
            img_facebook = (ImageView) view.findViewById(R.id.img_faceshare);
            img_whatsapp = (ImageView) view.findViewById(R.id.img_whatshare);
            img_google   = (ImageView) view.findViewById(R.id.img_googleshare);
            rPlay        = (RelativeLayout)view.findViewById(R.id.relativeL_play);

            //txt_no_of_post         = (TextView)view.findViewById(R.id.txt_no_of_post);
            txt_view     = (TextView)view.findViewById(R.id.txt_no_views);


            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"edit");
                }
            });

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"");
                }
            });

            rPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"");
                }
            });

            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"remove");
                }
            });



            img_facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"facebook");
                }
            });
            img_google.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"google");
                }
            });
            img_whatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(view,getAdapterPosition(),post.get(getAdapterPosition()),"whatsapp");
                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        }
    }

}