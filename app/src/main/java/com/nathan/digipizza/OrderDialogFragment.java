package com.nathan.digipizza;

import static com.nathan.digipizza.BR.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.nathan.digipizza.databinding.OrderDialogLayoutBinding;
import com.nathan.digipizza.orders.ItemListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDialogFragment extends AppCompatDialogFragment implements IItemsSummaryDialogs   {


    OrderDialogLayoutBinding mOrderDialogLayoutBinding;
    MainViewModel mMainViewModel;
//   IItemsDialog mItemsDialog;
    UUID pizzaId;
    UUID newCustomerId;
    OrderSummaryDialogFragment mOrderSummaryDialog;
    ItemListFragment mItemListDialog;
    Order mOrder;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Retrieve pizzaId from PizzaListFragment
        getParentFragmentManager().setFragmentResultListener("pizzaRequestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                String result = bundle.getString("pizzaIdKey");
                //Convert result to a UUID and then use result to find the correct Pizza, Pasta dish
                //by matching is from the list of Pizza, Pastas (may need to use a different REsultListener
                //for the Pastas)
                //Then use the dish to set a new Item and add it to the MutableLiveData<<List<Item>>
                //object in MainViewModel
                pizzaId = UUID.fromString(result);

            }
        });


        //Retrieve customer Id from PizzaListFragment
        getParentFragmentManager().setFragmentResultListener("customerRequestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("customerIdKey");
                newCustomerId = UUID.fromString(result);
            }
        });


        mOrderDialogLayoutBinding.setIItemsSummaryDialog((IItemsSummaryDialogs) this);

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);


        mOrderDialogLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.order_dialog_layout, container, false);
        View view = mOrderDialogLayoutBinding.getRoot();


        return view;




//        mOrderDialogLayoutBinding = OrderDialogLayoutBinding.inflate(LayoutInflater.from(getContext()));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getActivity().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mOrderDialogLayoutBinding.setVariable(mainViewModel, mMainViewModel);

        //Retrieve the new Customer from ViewModel and set it to the variable in order_dialog_layout
        for (Customer newCustomer : mMainViewModel.getCustomers()) {
            if (newCustomer.getCustomerId().toString().equals(newCustomerId.toString())) {
                mOrderDialogLayoutBinding.setVariable(customer, newCustomer);
            }
        }



        //Place pizza type selected by Customer into an item and add to list of items in the Customer's cart
        List<Pizza> pizzaList = mMainViewModel.getPizzas();
        for (Pizza pizza: pizzaList) {
            if (pizza.getId().equals(pizzaId)) {
                UUID itemId = pizza.getId();
                String itemTitle = pizza.getName();
                String itemDescription = pizza.getDescription();
                String itemPrice = pizza.price;

                Item item = new Item(itemId, itemTitle, itemDescription, itemPrice);
                List<Item> itemList = new ArrayList<>();
                itemList.add(item);
                mMainViewModel.getItems().setValue(itemList);
            }
        }
    }


    @Override
    public void inflateItemsDialog() {
        mItemListDialog = new ItemListFragment();
        mItemListDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        //Return the FragmentManager for interacting with fragments associated with this fragment's activity.
        mItemListDialog.show(getParentFragmentManager(), "ItemListDialog");
    }

    @Override
    public void closeItemsDialog() {
        mItemListDialog.dismiss();
    }


    @Override
    public void inflateOrderSummaryDialog() {

        //Create a new order, add it to our orders list is the main view model
        mOrder = new Order();
        Objects.requireNonNull(mMainViewModel.getOrders().getValue()).add(mOrder);
        UUID orderId = mOrder.getOrderId();

        //Pass the new Order's Id into the Fragment Manager (will be retrieved by OrderSummaryDialogFragment
        Bundle result = new Bundle();
        result.putString("orderId", orderId.toString());
        getParentFragmentManager().setFragmentResult("orderKey", result);

        //Create a new OrderDialog to display
        mOrderSummaryDialog = new OrderSummaryDialogFragment();
        mOrderSummaryDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        mOrderSummaryDialog.show(getParentFragmentManager(), "OrderSummaryDialog");



    }

    @Override
    public void closeOrderSummaryDialog() {

        mOrderSummaryDialog.dismiss();
    }

    @Override
    public void orderSummaryCheckout() {

        //For now, I'll clear the items list so that the next customer can use it. Once I set up
        //the CheckOut functionality I'll delete this line of code and clear the Items list after
        //the order has been completed.
        if (mMainViewModel.getItems().getValue() != null) {
            mMainViewModel.getItems().getValue().clear();
        }

        //Clear out the Customer in the customer list for the next customer (should only be one at a
        //time)
        mMainViewModel.getCustomers().clear();

        Toast.makeText(getContext(), "Under Construction", Toast.LENGTH_SHORT).show();
    }

//
}
