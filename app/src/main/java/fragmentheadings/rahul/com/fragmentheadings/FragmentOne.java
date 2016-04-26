package fragmentheadings.rahul.com.fragmentheadings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by rsahukar on 4/26/2016.
 */
public class FragmentOne extends Fragment {

    String[] str = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","Monday","Tuesday"};
    private RecyclerView rv;
    private int fragmentCount;

    public FragmentOne(){}

    public FragmentOne(int fragmentCount){
        this.fragmentCount = fragmentCount;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentone, container, false);

        rv = (RecyclerView) view.findViewById(R.id.recycle);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getActivity());
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
        private Context mContext;

        public MyRecyclerAdapter(Context context) {
            this.mContext = context;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected TextView textView;

            public CustomViewHolder(View view) {
                super(view);
                this.textView = (TextView) view.findViewById(R.id.textview);
                this.textView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v){
                if(v instanceof TextView){
                    ((MainActivity)getActivity()).addFragment(((TextView)v).getText().toString());
                }
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
            customViewHolder.textView.setText(fragmentCount + "  " +str[i]);
        }

        @Override
        public int getItemCount() {
            return str.length;
        }

    }
}
