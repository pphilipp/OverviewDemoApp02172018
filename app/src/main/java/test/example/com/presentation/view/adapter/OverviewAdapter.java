package test.example.com.presentation.view.adapter;

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
import test.example.com.R;
import test.example.com.data.entity.ResPropertyEntity;

public class OverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ResPropertyEntity> properties;

    public OverviewAdapter(Context context, List<ResPropertyEntity> properties) {
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
        ResPropertyEntity resPropertyEntity = properties.get(position);
        PropertiesViewHolder viewHolder = (PropertiesViewHolder) holder;

        viewHolder.resPropertyEntity = resPropertyEntity;

        Picasso.with(this.context)
               .load(resPropertyEntity.thumbnailBig)
               .into(viewHolder.ivPhoto);

        viewHolder.tvPrice.setText(resPropertyEntity.price);
        viewHolder.tvDescription.setText(resPropertyEntity.title);
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public class PropertiesViewHolder extends RecyclerView.ViewHolder{
        View root;
        ResPropertyEntity resPropertyEntity;
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
