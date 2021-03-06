package com.nathan.digipizza;

import static com.nathan.digipizza.BR.mainViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nathan.digipizza.databinding.FragmentPastaLayoutBinding;
import com.nathan.digipizza.databinding.FragmentPastaListBinding;

import java.util.List;

public class PastaListFragment extends Fragment implements LifecycleObserver {


    private MainViewModel mMainViewModel;
    private PastaAdapter mPastaAdapter;
    private FragmentPastaListBinding mPastaRecyclerBinding;

    private String[] pastaNames = {"Classic Italian Bake", "Classic Italian Pasta", "Classic Lasagna", "Classic Spaghetti", "Italian Shrimp Pasta", "Zesty Italian Pasta"};

    private String[] pastaDescriptions = {"classicitalianbake_description", "classicitalianpasta_description", "classiclasagna_description", "classicspaghetti_description", "italianshrimppasta_desciption", "zestyitalianpasta_description"};

    private int[] pastaImages = {R.drawable.classicitalianbake, R.drawable.classicitalianpasta, R.drawable.classiclasagna, R.drawable.classicspaghetti, R.drawable.italianshrimppasta, R.drawable.zestyitalianpasta};


    private String[] pastaPrices = {"$9.99", "$9.99", "$8.99", "$14.99", "$11.99", "12.99"};

    private String[] pastaOrderText = {"Order", "Order", "Order", "Order", "Order", "Order"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_pasta_list, container, false);

//        binding = FragmentPastaLayoutBinding.inflate(inflater, container, false);
//        binding.setLifecycleOwner(this);
//        View view = binding.getRoot();

        //pasta_recycler_view is the id of the RecyclerView in fragment_pasta_list
//        mPastaRecyclerView = (RecyclerView) view.findViewById(R.id.pasta_recycler_view);
//        mPastaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPastaRecyclerBinding = DataBindingUtil.inflate(inflater,  R.layout.fragment_pasta_list, container, false);
        mPastaRecyclerBinding.pastaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View view = mPastaRecyclerBinding.getRoot();

//        updateUI();

//        return super.onCreateView(inflater, container, savedInstanceState)
        return view;
    }


    //the onAttach() & addBinding() methods are used to link the binding Java objects in this
    //fragment to the data variable in the layout file
    //this code replaces what would normally by placed in onActivityCreate() which is deprecated
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        requireActivity().getLifecycle().addObserver(this);
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mPastaRecyclerBinding.setVariable(mainViewModel, mMainViewModel);
        updateUI();
    }


    private class PastaHolder extends RecyclerView.ViewHolder implements IOrderDialog {


        //The ViewHolder constructor must take in an View argument type
        //To do this however, we will inflate our fragment_pizza_layout.xml file and pass this
        //into the super constructor. Thus, the base ViewHolder class will hold on to the
        //fragment_pizza_layout view heirarchy. If you need that view heirarchy, you can find it
        //it in ViewHolder's itemView field

//        public TextView pastaName;
//        public ImageView pastaImage;
//        public TextView pastaDescription;
//        public TextView pastaPrice;
//        public Button pastaOrder;

        private FragmentPastaLayoutBinding mPastaViewBinding;

        public PastaHolder(@NonNull FragmentPastaLayoutBinding binding) {
            super(binding.getRoot());

            mPastaViewBinding = binding;

            mPastaViewBinding.setMainViewModel(mMainViewModel);

            mPastaViewBinding.setIOrderDialog((IOrderDialog)this);

//            pastaName = itemView.findViewById(R.id.pasta_name);
//            pastaImage = itemView.findViewById(R.id.pasta_image);
//            pastaDescription = itemView.findViewById(R.id.pasta_description);
//            pastaPrice = itemView.findViewById(R.id.pasta_price);
//            pastaOrder = itemView.findViewById(R.id.pasta_order_button);

        }

        public List<Pasta> getPastaViewsList () {
            return mPastaViewBinding.getMainViewModel().getPastas();
        }

        @Override
        public void inflateOrderDialog() {
            OrderDialogFragment dialog = new OrderDialogFragment();
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
            dialog.show(getParentFragmentManager(), "pastaOrderDialog");
        }
    }

    private class PastaAdapter extends RecyclerView.Adapter<PastaHolder> {

        private List<Pasta> mPastas;

        public PastaAdapter (List<Pasta> pastas) {
            mPastas = pastas;
        }

        //We're doing the same thing with onCreateViewHolder here as we did with PizzaHolder
        //constructor. The method requires a

        @NonNull
        @Override
        public PastaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //LayoutInflater.from(context) obtains the layout inflater from the given Context
            //Note: I'm still not sure which Activity will be the parent of this fragment--MainActivity or PizzaListActivity
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_pasta_layout, parent, false);
//            return new PastaHolder(view);

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            FragmentPastaLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pasta_layout, parent, false);
            return new PastaHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull PastaHolder holder, int position) {

            Pasta pasta = holder.getPastaViewsList().get(position);

            holder.mPastaViewBinding.setPastaName(pasta.name);
            holder.mPastaViewBinding.setPastaImage(pasta.pastaImage);
            holder.mPastaViewBinding.setPastaDescription(pasta.description);
            holder.mPastaViewBinding.setPastaPrice(pasta.price);
            holder.mPastaViewBinding.setPastaOrderText(pasta.orderText);


//            Pasta pasta = mPastas.get(position);
//            holder.pastaName.setText(pasta.getName());
//            holder.pastaImage.setImageResource(pasta.getPastaImage());
//            holder.pastaDescription.setText(pasta.getDescription());
//            holder.pastaPrice.setText(pasta.getPrice());
//            holder.pastaOrder.setText(pasta.getOrderText());

        }

        @Override
        public int getItemCount() {
            return mPastas.size();
        }
    }

    private void updateUI() {



        List<Pasta> pastas = mMainViewModel.getPastas();
        prepareTheList(pastas);

        mPastaAdapter = new PastaAdapter(pastas);
        mPastaRecyclerBinding.pastaRecyclerView.setAdapter(mPastaAdapter);

    }

    private void prepareTheList (List<Pasta> pastas ) {
        int count = 0;

        for (String name : pastaNames) {

            Pasta pasta = new Pasta(name, pastaImages[count] , pastaDescriptions[count], pastaPrices[count], pastaOrderText[count]);
            pastas.add(pasta);
            count++;
        }
    }
}
