package co.meettheteam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static co.meettheteam.UserResponse.users;

/**
 * Created by dharma on 11/16/16.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView tvUserFLName;
    public TextView tvUserTitle;
    public ImageView imgAvatar;
    Context mcontext;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        tvUserFLName = (TextView) itemView.findViewById(R.id.textviewName);
        tvUserTitle = (TextView) itemView.findViewById(R.id.textviewTitle);
        imgAvatar = (ImageView) itemView.findViewById(R.id.imageviewAvatar);


    }

    @Override
    public void onClick(final View view) {
        //Utils.showToast((Activity) view.getContext(), users.get(getPosition()).getBio().toString(), users.get(getPosition()).getAvatar(),imageLoader );

        final Dialog dialog = new Dialog((Activity) view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.customtoast);

        TextView tvHeader = (TextView) dialog.findViewById(R.id.tvHeader);
        tvHeader.setText(users.get(getPosition()).getFirstName() + " " + users.get(getPosition()).getLastName());

        ImageView imageViewAvatar = (ImageView) dialog.findViewById(R.id.custom_toast_image);
        Glide.with((Activity) view.getContext())
                .load(users.get(getPosition()).getAvatar().toString())
                .into(imageViewAvatar);


        TextView text = (TextView) dialog.findViewById(R.id.custom_toast_message);
        text.setText(users.get(getPosition()).getBio().toString());

        final Button dialogButtonFav = (Button) dialog.findViewById(R.id.btn_fav);
        dialogButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                // favourite can be implement
            }
        });

        // cancel button
        Button dialogCancle = (Button) dialog.findViewById(R.id.btn_cancel);
        dialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // suggest button
        Button dialogSuggest = (Button) dialog.findViewById(R.id.btn_share);
        dialogSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = users.get(getPosition()).getFirstName().toString() + users.get(getPosition()).getLastName().toString();
                String title = users.get(getPosition()).getTitle().toString();
                String bio = UserResponse.users.get(getPosition()).getBio().toString();
                shareMessage((Activity) view.getContext(), name, title, bio);
            }
        });

        dialog.show();


    }

    // suggest functions declare
    public void shareMessage(Activity activity, String name, String title, String bio) {

        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Do you wanna share love with me");
        share.putExtra(Intent.EXTRA_TEXT, name + " \n" + title + " \n\n" + bio);

        activity.startActivity(Intent.createChooser(share, "Share Messages!"));
    }

}