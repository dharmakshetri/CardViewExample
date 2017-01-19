package co.meettheteam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;
/**
 * Created by dharma on 11/16/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<User> itemList;
    private Context context;
    public RecyclerViewAdapter(Context context, List<User> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.tvUserFLName.setText(itemList.get(position).getFirstName()+" " +itemList.get(position).getLastName());
        holder.tvUserTitle.setText(itemList.get(position).getTitle());
        Glide.with(context)
                .load(itemList.get(position).getAvatar())
                .into(holder.imgAvatar);


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}