package codist.me.mreader;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private ArrayList<String> mDataset;
    private static Context sContext;

    public NewsListAdapter(Context context, ArrayList<String> myDataset) {
        mDataset = myDataset;
        sContext = context;
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view by inflating the row item xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        // Set the view to the ViewHolder
        ViewHolder holder = new ViewHolder(v);
        holder.mNameTextView.setOnClickListener(NewsListAdapter.this);
        holder.mNameTextView.setOnLongClickListener(NewsListAdapter.this);

        holder.mNameTextView.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.mNumberRowTextView.setText(String.valueOf(position) + ". ");

        // Get the element from your dataset at this position and set the text for the specified element
        viewHolder.mNameTextView.setText(mDataset.get(position));

        // set the color to red if row is even, or to green if row is odd.
        if (position % 2 == 0) {
            viewHolder.mNumberRowTextView.setTextColor(Color.RED);
        } else {
            viewHolder.mNumberRowTextView.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            Toast.makeText(sContext, holder.mNameTextView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            mDataset.remove(holder.getPosition());

            // Call this method to refresh the list and display the "updated" list
            notifyDataSetChanged();

            Toast.makeText(sContext, "Item " + holder.mNameTextView.getText()
                    + " has been removed from list", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNumberRowTextView;
        public TextView mNameTextView;

        public ViewHolder(View v) {
            super(v);

            mNumberRowTextView = (TextView) v.findViewById(R.id.rowNumberTextView);
            mNameTextView = (TextView) v.findViewById(R.id.nameTextView);
        }
    }
}
