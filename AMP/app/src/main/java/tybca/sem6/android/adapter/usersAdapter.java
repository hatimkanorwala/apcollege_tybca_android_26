package tybca.sem6.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tybca.sem6.android.R;
import tybca.sem6.android.model.usersModel;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.ProductViewHolder> {
    public Context mCtx;
    List<usersModel> usersList;
    public usersAdapter(Context mCtx, List<usersModel> usersList) {
        this.mCtx = mCtx;
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public usersAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.users_layout, null);
        final usersAdapter.ProductViewHolder holder = new usersAdapter.ProductViewHolder(view);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull usersAdapter.ProductViewHolder holder, int position) {
        final usersModel usersModel = usersList.get(position);
        holder._usersLayout_tv_username.setText(usersModel.getUsername());
        holder._usersLayout_tv_email.setText(usersModel.getEmail());
        holder._usersLayout_tv_contact.setText(usersModel.getContact());
        holder._usersLayout_ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, "Clicked on: " + usersModel.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
        holder._usersLayout_btn_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        LinearLayout _usersLayout_ll_main,_usersLayout_ll_data;
        RelativeLayout _usersLayout_rl_header;
        TextView _usersLayout_tv_username,_usersLayout_tv_email,_usersLayout_tv_contact;
        Button _usersLayout_btn_viewMore;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            _usersLayout_ll_main = itemView.findViewById(R.id.usersLayout_ll_main);
            _usersLayout_ll_data = itemView.findViewById(R.id.usersLayout_ll_data);
            _usersLayout_rl_header = itemView.findViewById(R.id.usersLayout_rl_header);
            _usersLayout_tv_username = itemView.findViewById(R.id.usersLayout_tv_username);
            _usersLayout_tv_email = itemView.findViewById(R.id.usersLayout_tv_email);
            _usersLayout_tv_contact = itemView.findViewById(R.id.usersLayout_tv_contact);
            _usersLayout_btn_viewMore = itemView.findViewById(R.id.usersLayout_btn_viewMore);
        }
    }
}
