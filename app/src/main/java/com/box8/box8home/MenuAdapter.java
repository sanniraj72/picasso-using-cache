package com.box8.box8home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Callback;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private List<String> menu;
    private LayoutInflater inflater;
    private CallBack callBack;

    /**
     * Constructor
     *
     * @param context context
     * @param menu    menu
     */
    MenuAdapter(Context context, List<String> menu, CallBack callBack) {
        this.menu = menu;
        this.callBack = callBack;
        this.inflater = LayoutInflater.from(context);

    }

    /**
     * Called when RecyclerView needs a new {@link android.support.v7.widget.RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int, java.util.List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
     */
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, null);
        return new MenuHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link android.support.v7.widget.RecyclerView.ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link android.support.v7.widget.RecyclerView.ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int, java.util.List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, final int position) {
        Box8Application.getInstance().getPicasso().load("https://via.placeholder.com/980x540/7e96bc/ffffff/")
                .into(holder.productImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        callBack.onImageLoaded();
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });

        holder.productName.setText(menu.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return menu.size();
    }

    /**
     * ViewHolder for Menu
     */
    class MenuHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView productName;

        MenuHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
        }
    }
}
