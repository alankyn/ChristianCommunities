package ccomunities.alashka.com.ccommunities_dev.Listener;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import ccomunities.alashka.com.ccommunities_dev.SingInActivity;

/**
 * Created by ALANKIN on 17/12/16.
 */

public class CommentClickListener implements View.OnClickListener {

    Long publicationId;

    public CommentClickListener(Long publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public void onClick(View v) {
        Log.d("PublicationClick", "CLick on Publication ID: "+ publicationId);
        //Intent intent = new Intent(v.getContext(), SingInActivity.class);
        //intent.putExtra("publicationId", publicationId);
        //v.getContext().startActivity(intent);
    }
}
