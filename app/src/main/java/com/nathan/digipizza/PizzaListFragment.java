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
 import androidx.lifecycle.ViewModelProvider;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.nathan.digipizza.databinding.FragmentPizzaLayoutBinding;
 import com.nathan.digipizza.databinding.FragmentPizzaListBinding;

 import java.util.List;
 import java.util.Objects;
 import java.util.UUID;


 public class PizzaListFragment extends Fragment {


    private MainViewModel mMainViewModel;
    private PizzaHolder mPizzaHolder;
    private PizzaAdapter mPizzaAdapter;
    private FragmentPizzaListBinding mPizzaRecyclerBinding;
    private FragmentPizzaLayoutBinding mFragmentPizzaLayoutBinding;


    

    private String[] pizzaNames = {"Cheese", "Hamburger", "Meatlovers", "Pepperoni", "Supreme", "Thin_crust"};

    private String[] pizzaDescriptions = {"cheese_description", "hamburger_description", "meatlovers_description", "pepperoni_description", "supreme_desciption", "thin_crust_description"};

    private int[] images = {R.drawable.cheese, R.drawable.hamburger, R.drawable.meatlovers, R.drawable.pepperoni, R.drawable.supreme, R.drawable.thin_crust};


    private String[] pizzaPrices = {"$12.99", "$14.99", "$15.99", "$14.99", "$17.99", "regular price minus $2.00"};

    private String[] pizzaOrderText = {"Order", "Order", "Order", "Order", "Order", "Order"};



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_pizza_list, container, false);

//        pizzaViewBinding = FragmentPizzaLayoutBinding.inflate(inflater, container, false);
//        pizzaViewBinding.setLifecycleOwner(this);
//        View view = pizzaViewBinding.getRoot();

        mPizzaRecyclerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza_list, container, false);
//        mPizzaRecyclerBinding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_pizza_list);

        //Important Note: Our RecyclerView instance is accessed by use of pizzaRecyclerView, which is an object
        //generated by the pizza_recycler_view android Id of your fragment_pizza_list layout that contains our
        //RecyclerView
        //Important Note Two: The RecyclerView is accessed in Java through the binding class
        mPizzaRecyclerBinding.pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mPizzaRecyclerBindi
//        ng.setLifecycleOwner(this);
        View view = mPizzaRecyclerBinding.getRoot();


        //pizza_recycler_view is the id of the RecyclerView in fragment_pizza_list
//        mPizzaRecyclerView = (RecyclerView) view.findViewById(R.id.pizza_recycler_view);
//        mPizzaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        updateUI();

        return view;

//        return super.onCreateView(inflater, container, savedInstanceState);
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

//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)

//     private void addBindingLink() {
//        requireActivity().getLifecycle().removeObserver(this);


     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);

         mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
         mPizzaRecyclerBinding.setVariable(mainViewModel,mMainViewModel);


         updateUI();

     }



     private class PizzaHolder extends RecyclerView.ViewHolder implements IOrderDialog  {

        //The ViewHolder constructor must take in an View argument type
        //To do this however, we will inflate our fragment_pizza_layout.xml file and pass this
        //into the super constructor. Thus, the base ViewHolder class will hold on to the
        //fragment_pizza_layout view heirarchy. If you need that view heirarchy, you can find it
        //it in ViewHolder's itemView field

//        public TextView pizzaName;
//        public ImageView pizzaImage;
//        public TextView pizzaDescription;
//        public TextView pizzaPrice;
//        public TextView pizzaOrder;

        private FragmentPizzaLayoutBinding mPizzaViewBinding;
        private UUID newCustomerId;

        public PizzaHolder(@NonNull FragmentPizzaLayoutBinding binding) {
            super(binding.getRoot());

            //Right here is where you should set PizzaHolder class as observer for your MutableLiveData
            //of List<Items>

            mPizzaViewBinding = binding;
            mPizzaViewBinding.setMainViewModel(mMainViewModel);



            //Linking view binding to iOrderDialog variable in fragment_pizza_layout
            //With this linkage, can reference your methods in IOrderDialog in our fragment_pizza_layout xml
            mPizzaViewBinding.setIOrderDialog((IOrderDialog) this);

        }

        public void bind (Pizza pizza) {

            mPizzaViewBinding.setPizzaName(pizza.name);
            mPizzaViewBinding.setPizzaImage(pizza.pizzaImage);
            mPizzaViewBinding.setPizzaDescription(pizza.description);
            mPizzaViewBinding.setPizzaPrice(pizza.price);
            mPizzaViewBinding.setPizzaOrderText(pizza.orderText);

            String pizzaId = pizza.getId().toString();

            Bundle result = new Bundle();
            result.putString("pizzaIdKey", pizzaId);
            getParentFragmentManager().setFragmentResult("pizzaRequestKey", result);
        }

        public List<Pizza> getPizzaViewsList () {
           return mPizzaViewBinding.getMainViewModel().getPizzas();
        }


         public void inflateOrderDialog() {
             OrderDialogFragment dialog = new OrderDialogFragment();

             //Need to create a new Item and add it to Item list in mMainViewModel
             Pizza pizza = mPizzaAdapter.mPizza;
             Item item = new Item(pizza.id, pizza.name, pizza.description, pizza.price);
             Objects.requireNonNull(mMainViewModel.getItems().getValue()).add(item);


            //Create a new customer & send its ID to Order_Dialog_Fragment
             Customer customer = new Customer();
             mMainViewModel.getCustomers().add(customer);
             newCustomerId = customer.getCustomerId();
             Bundle result = new Bundle();
             result.putString("customerRequestKey", newCustomerId.toString());
             getParentFragmentManager().setFragmentResult("customerIdKey", result);


             dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);

             //Return the FragmentManager for interacting with fragments associated with this fragment's activity.
             dialog.show(getParentFragmentManager(), "pizzaOrderDialog");

         }


     }

    private class PizzaAdapter extends RecyclerView.Adapter<PizzaHolder>  {

        private Pizza mPizza;
        private List<Pizza> mPizzas;

        private FragmentPizzaLayoutBinding mFragmentPizzaLayoutBinding;
////
        public PizzaAdapter (List<Pizza> pizzas) {
            mPizzas = pizzas;

            mFragmentPizzaLayoutBinding.setMainViewModel(mMainViewModel);

        }

        //We're doing the same thing with onCreateViewHolder here as we did with PizzaHolder
        //constructor. The method requires a

        @NonNull
        @Override
        public PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //LayoutInflater.from(context) obtains the layout inflater from the given Context
            //Note: I'm still not sure which Activity will be the parent of this fragment--MainActivity or PizzaListActivity
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            FragmentPizzaLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pizza_layout, parent, false);
            return new PizzaHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull PizzaHolder holder, int position) {

            //holder.getPizzaViewsList() links PizzaHolder with the list returned by getPizzas()
            //in the view model. The correct pizza is selected by position and the views in the
            //ViewGroup held by PizzaHolder are set to the pizza at that position.
            mPizza = holder.getPizzaViewsList().get(position);
            holder.bind(mPizza);

//            holder.mPizzaViewBinding.setPizzaName(mPizza.name);
//            holder.mPizzaViewBinding.setPizzaImage(mPizza.pizzaImage);
//            holder.mPizzaViewBinding.setPizzaDescription(mPizza.description);
//            holder.mPizzaViewBinding.setPizzaPrice(mPizza.price);
//            holder.mPizzaViewBinding.setPizzaOrderText(mPizza.orderText);

//            pizza.setName(pizza.getName());
//            pizza.setPizzaImage(pizza.getPizzaImage());
//            pizza.setDescription(pizza.getDescription());
//            pizza.setPrice(pizza.getPrice());
//            pizza.setOrderText(pizza.getOrderText());

        }

        @Override
        public int getItemCount() {
            return mPizzas.size();
        }

//        public void getPizzaId() {
//            String pizzaId = mPizza.getId().toString();
//
//            Bundle result = new Bundle();
//            result.putString("bundleKey", pizzaId);
//            getParentFragmentManager().setFragmentResult("requestKey", result);
//        }


    }



    private void updateUI() {

        //Retrieve your singleton

        List<Pizza> pizzas = mMainViewModel.getPizzas();
        //This line is loading pizzas list. With Items, you'll be getting them from a Dialog so this
        //line of code isn't necessary
        prepareTheList(pizzas);

        mPizzaAdapter = new PizzaAdapter(pizzas);

        //Again, our RecyclerView for our pizzas is accessed through the binding class
        mPizzaRecyclerBinding.pizzaRecyclerView.setAdapter(mPizzaAdapter);

    }

    private void prepareTheList (List<Pizza> pizzas ) {
        int count = 0;

        for (String name : pizzaNames) {

            Pizza pizza = new Pizza(name, images[count] , pizzaDescriptions[count], pizzaPrices[count], pizzaOrderText[count]);
            pizzas.add(pizza);
            count++;
        }
    }




    //This method can be referenced in fragment_pizza_layout file because we have linked
//    public void displayOrderDialog() {
//        OrderDialog dialog = new OrderDialog();
//        FragmentManager fm = getParentFragmentManager();
//        dialog.show(fm, "PizzaListFragment");
//    }

//     private void showDialog () {
//        Dialog dialog = new Dialog(getActivity());
//        dialog.setContentView(R.layout.order_dialog_layout);
//     }
}
