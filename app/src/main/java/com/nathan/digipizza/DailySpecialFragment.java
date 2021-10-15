package com.nathan.digipizza;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.nathan.digipizza.databinding.SaladsDailySpecialsLayoutBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DailySpecialFragment extends Fragment {

    private String[] saladNames = {"Summer Salad", "Ceasar Salad", "Seafood Taco Salad", "Autumn Salad"};

    private String[] saladDescriptions ={"Fresh mixed greens topped with strawberries, mandarin oranges, blueberries, and sweetened pecans. Served with poppyseed dressing.",
    "Crispy romaine lettuce with ceasar dressing, parmesan cheese, avocado, and croutons.",
    "Shredded lettuce, tomato, black olives, cheese and scallions topped with shrimp and crab, all in a tortilla bowl served with ranch dressing and ranchero sause.",
    "Romaine lettuce with crumbled blue cheese granny smith apple wedges and sugared walnuts served with raspberry vinaigrette."};


    private MainViewModel mMainViewModel;
    private SaladsDailySpecialsLayoutBinding mSaladsDailySpecialsLayoutBinding;

    private ArrayAdapter<Salad> mSaladListAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.salads_daily_specials_layout, container, false);
        mSaladsDailySpecialsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.salads_daily_specials_layout, container, false);




        View v = mSaladsDailySpecialsLayoutBinding.getRoot();
        return v;


    }

    //You'll prob need to move your salad list code into onViewCreated since the bound instance of
    //mainViewModel is not available until then

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        prepareTheList(mMainViewModel.getSalads());

        mSaladListAdapter = new ArrayAdapter<Salad>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mMainViewModel.getSalads()
        );

        mSaladsDailySpecialsLayoutBinding.saladsList.setAdapter(mSaladListAdapter);


        //CODE TO SET THE DAILY SPECIAL VIEW
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);

        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());

        switch (weekDay) {
            case "Monday":
                if (savedInstanceState != null) {
                    MondaySpecialLayoutFragment mondayLayout = new MondaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, mondayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showMondayDailySpecialLayout();
                }
                break;
            case "Tuesday":
                if (savedInstanceState != null) {
                    TuesdaySpecialLayoutFragment tuesdayLayout = new TuesdaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, tuesdayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showTuesdayDailySpecialLayout();
                }
                break;
            case "Wednesday":
                if (savedInstanceState != null) {
                    WednesdaySpecialLayoutFragment wednesdayLayout = new WednesdaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, wednesdayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showWednesdayDailySpecialLayout();
                }
                break;
            case "Thursday":
                if (savedInstanceState != null) {
                    ThursdaySpecialLayoutFragment thursdayLayout = new ThursdaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, thursdayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showThursdayDailySpecialLayout();
                }
                break;
            case "Friday":
                if (savedInstanceState != null) {
                    FridaySpecialLayoutFragment fridayLayout = new FridaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, fridayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showFridayDailySpecialLayout();
                }
                break;
            case "Saturday":
                if (savedInstanceState != null) {
                    SaturdaySpecialLayoutFragment saturdayLayout = new SaturdaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, saturdayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showSaturdayDailySpecialLayout();
                }
                break;
            case  "Sunday":
                if (savedInstanceState != null) {
                    SundaySpecialLayoutFragment sundayLayout = new SundaySpecialLayoutFragment();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.daily_specials_container, sundayLayout);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    showSundayDailySpecialLayout();
                }
                break;
            default:
                Log.d("DailySpecialFragment", "No daily special available today");
        }
    }



        //Set Monday layout fragment as fragment contained in the Frame Layout
//        if (savedInstanceState == null) {
//            MondaySpecialLayoutFragment mondayLayout = new MondaySpecialLayoutFragment();
//            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//            ft.add(R.id.daily_specials_container, mondayLayout);
//            ft.addToBackStack(null);
//            ft.commit();
//
//            }


    private void prepareTheList (List<Salad> salads ) {

        int count = 0;

        for (String name : saladNames) {

            Salad salad = new Salad(name, saladDescriptions[count]);
            salads.add(salad);
            count++;
        }
    }

    private void showMondayDailySpecialLayout () {

        MondaySpecialLayoutFragment mondayLayout = new MondaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, mondayLayout);
        ft.addToBackStack(null);
        ft.commit();

        }

    private void showTuesdayDailySpecialLayout () {

        TuesdaySpecialLayoutFragment tuesdayLayout = new TuesdaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, tuesdayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void showWednesdayDailySpecialLayout () {

        WednesdaySpecialLayoutFragment wednesdayLayout = new WednesdaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, wednesdayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void showThursdayDailySpecialLayout () {

        ThursdaySpecialLayoutFragment thursdayLayout = new ThursdaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, thursdayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void showFridayDailySpecialLayout () {

        FridaySpecialLayoutFragment fridayLayout = new FridaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, fridayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void showSaturdayDailySpecialLayout () {

        SaturdaySpecialLayoutFragment saturdayLayout = new SaturdaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, saturdayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

    private void showSundayDailySpecialLayout () {

        SundaySpecialLayoutFragment sundayLayout = new SundaySpecialLayoutFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.daily_specials_container, sundayLayout);
        ft.addToBackStack(null);
        ft.commit();

    }

}
