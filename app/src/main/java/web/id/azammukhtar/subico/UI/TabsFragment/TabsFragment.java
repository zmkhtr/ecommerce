package web.id.azammukhtar.subico.UI.TabsFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.CartFragment.CartFragment;
import web.id.azammukhtar.subico.UI.FavoriteFragment.FavoriteFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomePriaFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeWanitaFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment {

    private Unbinder unbinder;
    private TabAdapter tabAdapter;
    private FragmentManager fm;
    private Fragment homeFragment = new HomeFragment();
    private Fragment homePriaFragment = new HomePriaFragment();
    private Fragment homeWanitaFragment = new HomeWanitaFragment();
    private Fragment active = homeFragment;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public TabsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        fm = getFragmentManager();

        tabAdapter = new TabAdapter(fm);

        tabAdapter.addFragment(homeFragment, "Semua");
        tabAdapter.addFragment(homePriaFragment, "Pria");
        tabAdapter.addFragment(homeWanitaFragment, "Wanita");
//        if (fm != null) {
//            fm.beginTransaction().hide(homeFragment2).commit();
//            fm.beginTransaction().hide(homeFragment1).commit();
//            fm.beginTransaction().show(homeFragment).commit();
//        }
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
       /* tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        fm.beginTransaction().hide(active).show(homeFragment).commit();
                        active = homeFragment;
//                        fm.beginTransaction().hide(tabAdapter.getItem(tab.getPosition())).hide().commit();
//                        tabAdapter.getItem(tab.getPosition());
                        break;
                    case 1:
                        fm.beginTransaction().hide(active).show(homeFragment1).commit();
                        active = homeFragment1;
                        break;
                    case 2:
                        fm.beginTransaction().hide(active).show(homeFragment2).commit();
                        active = homeFragment2;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }



}
