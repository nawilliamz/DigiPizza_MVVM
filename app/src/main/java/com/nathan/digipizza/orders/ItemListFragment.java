package com.nathan.digipizza.orders;


import static com.nathan.digipizza.BR.mainViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nathan.digipizza.Item;
import com.nathan.digipizza.MainViewModel;
import com.nathan.digipizza.R;
import com.nathan.digipizza.databinding.FragmentItemLayoutBinding;
import com.nathan.digipizza.databinding.ItemsListLayoutBinding;

import java.util.List;
import java.util.Objects;

public class ItemListFragment extends AppCompatDialogFragment {

    private MainViewModel mMainViewModel;

    private ItemsListLayoutBinding mItemRecyclerBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mItemRecyclerBinding = DataBindingUtil.inflate(inflater, R.layout.items_list_layout, container, false);
        mItemRecyclerBinding.itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View view = mItemRecyclerBinding.getRoot();

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //With access to the ModelView, code can now be added to the Fragment to begin working with
        //the code in the particular ModelView that has been accessed
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mItemRecyclerBinding.setVariable(mainViewModel, mMainViewModel);


    }


    private class ItemHolder extends RecyclerView.ViewHolder {

        private FragmentItemLayoutBinding mItemViewBinding;


        public ItemHolder(@NonNull FragmentItemLayoutBinding binding) {
            super(binding.getRoot());

            mItemViewBinding = binding;
            //Important Note: your layout ID for for your recyclerview must be in the following format
            //in order for setMainViewModel() to even appear as an option: items_recycler_view
            //When I used items_Recyclerview, it did not work!!!
            mItemViewBinding.setMainViewModel(mMainViewModel);

        }

        public MutableLiveData<List<Item>> getItemsViewsList() {
                return mItemViewBinding.getMainViewModel().getItems();
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private final List<Item> mItems;

        public ItemAdapter (List<Item> items) {
            mItems = items;
        }


        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            FragmentItemLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_layout, parent, false);
            return new ItemHolder(binding);


        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

            //***still need to do validation to ensure there's exactly one List<Item> in our list of
            //MutableLiveData (***You will also need to ensure no previous List<Item>s are already
            //contained in the "items" List in MainViewModel every time a new Cart is created and a
            //new, unique/active List<Item> is added to "items").


            Item item = Objects.requireNonNull(holder.getItemsViewsList().getValue()).get(position);


            holder.mItemViewBinding.setItemName(item.title);
            holder.mItemViewBinding.setItemPrice(item.price);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

}
