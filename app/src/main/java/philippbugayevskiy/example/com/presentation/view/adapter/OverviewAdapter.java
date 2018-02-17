package philippbugayevskiy.example.com.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import philippbugayevskiy.example.com.R;
import philippbugayevskiy.example.com.data.entity.ReEntity;

public class OverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ReEntity> properties;

    public OverviewAdapter(Context context, List<ReEntity> properties) {
        this.context = context;
        this.properties = properties;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PropertiesViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_property, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ReEntity reEntity = properties.get(position);
        PropertiesViewHolder viewHolder = (PropertiesViewHolder) holder;

        viewHolder.reEntity = reEntity;

        Picasso.with(this.context)
               .load(reEntity.thumbnailBig)
               .into(viewHolder.ivPhoto);

        viewHolder.tvPrice.setText(reEntity.price);
        viewHolder.tvDescription.setText(reEntity.title);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class PropertiesViewHolder extends RecyclerView.ViewHolder{
        View root;
        ReEntity reEntity;
        @BindView(R.id.iv_photo) ImageView ivPhoto;
        @BindView(R.id.tv_price) TextView tvPrice;
        @BindView(R.id.tv_description) TextView tvDescription;

        PropertiesViewHolder(View root) {
            super(root);

            ButterKnife.bind(this, root);

            this.root = root;
        }
    }
}
