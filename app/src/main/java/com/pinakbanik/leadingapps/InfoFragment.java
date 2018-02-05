package com.pinakbanik.leadingapps;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InfoFragment extends Fragment {



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static final String TAG = InfoFragment.class.getSimpleName();
    public InfoFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {


                View rootView = inflater.inflate(R.layout.fragment_subpage03, container, false);

                return rootView;

            }

           else   if(getArguments().getInt(ARG_SECTION_NUMBER)==2) {


                View rootView = inflater.inflate(R.layout.fragment_subpage04, container, false);

                return rootView;

            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3) {

                View rootView = inflater.inflate(R.layout.fragment_subpage05, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4) {

                View rootView = inflater.inflate(R.layout.fragment_subpage06, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==5) {

                View rootView = inflater.inflate(R.layout.fragment_subpage07, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==6) {

                View rootView = inflater.inflate(R.layout.fragment_subpage08, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==7) {

                View rootView = inflater.inflate(R.layout.fragment_subpage10, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==8) {

                View rootView = inflater.inflate(R.layout.fragment_subpage11, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==9) {

                View rootView = inflater.inflate(R.layout.fragment_subpage12, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==10) {

                View rootView = inflater.inflate(R.layout.fragment_subpage13, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==11) {

                View rootView = inflater.inflate(R.layout.fragment_subpage14, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==12) {

                View rootView = inflater.inflate(R.layout.fragment_subpage15, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==13) {

                View rootView = inflater.inflate(R.layout.fragment_subpage16, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==14) {

                View rootView = inflater.inflate(R.layout.fragment_subpage17, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==15) {

                View rootView = inflater.inflate(R.layout.fragment_subpage18, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==16) {

                View rootView = inflater.inflate(R.layout.fragment_subpage19, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==17) {

                View rootView = inflater.inflate(R.layout.fragment_subpage20, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==18) {

                View rootView = inflater.inflate(R.layout.fragment_subpage21, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==19) {

                View rootView = inflater.inflate(R.layout.fragment_subpage22, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==20) {

                View rootView = inflater.inflate(R.layout.fragment_subpage23, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==21) {

                View rootView = inflater.inflate(R.layout.fragment_subpage24, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==22) {

                View rootView = inflater.inflate(R.layout.fragment_subpage25, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==23) {

                View rootView = inflater.inflate(R.layout.fragment_subpage26, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==24) {

                View rootView = inflater.inflate(R.layout.fragment_subpage27, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==25) {

                View rootView = inflater.inflate(R.layout.fragment_subpage28, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==26) {

                View rootView = inflater.inflate(R.layout.fragment_subpage29, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==27) {

                View rootView = inflater.inflate(R.layout.fragment_subpage30, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==28) {

                View rootView = inflater.inflate(R.layout.fragment_subpage31, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==29) {

                View rootView = inflater.inflate(R.layout.fragment_subpage32, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==30) {

                View rootView = inflater.inflate(R.layout.fragment_subpage33, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==31) {

                View rootView = inflater.inflate(R.layout.fragment_subpage34, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==32) {

                View rootView = inflater.inflate(R.layout.fragment_subpage35, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==33) {

                View rootView = inflater.inflate(R.layout.fragment_subpage36, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==34) {

                View rootView = inflater.inflate(R.layout.fragment_subpage37, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==35) {

                View rootView = inflater.inflate(R.layout.fragment_subpage38, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==36) {

                View rootView = inflater.inflate(R.layout.fragment_subpage39, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==37) {

                View rootView = inflater.inflate(R.layout.fragment_subpage40, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==38) {

                View rootView = inflater.inflate(R.layout.fragment_subpage41, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==39) {

                View rootView = inflater.inflate(R.layout.fragment_subpage42, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==40) {

                View rootView = inflater.inflate(R.layout.fragment_subpage43, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==41) {

                View rootView = inflater.inflate(R.layout.fragment_subpage44, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==42) {

                View rootView = inflater.inflate(R.layout.fragment_subpage45, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==43) {

                View rootView = inflater.inflate(R.layout.fragment_subpage46, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==44) {

                View rootView = inflater.inflate(R.layout.fragment_subpage47, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==45) {

                View rootView = inflater.inflate(R.layout.fragment_subpage48, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==46) {

                View rootView = inflater.inflate(R.layout.fragment_subpage49, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==47) {

                View rootView = inflater.inflate(R.layout.fragment_subpage50, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==48) {

                View rootView = inflater.inflate(R.layout.fragment_subpage51, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==49) {

                View rootView = inflater.inflate(R.layout.fragment_subpage52, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==50) {

                View rootView = inflater.inflate(R.layout.fragment_subpage53, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==51) {

                View rootView = inflater.inflate(R.layout.fragment_subpage54, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==52) {

                View rootView = inflater.inflate(R.layout.fragment_subpage55, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==53) {

                View rootView = inflater.inflate(R.layout.fragment_subpage56, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==54) {

                View rootView = inflater.inflate(R.layout.fragment_subpage57, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==55) {

                View rootView = inflater.inflate(R.layout.fragment_subpage58, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==56) {

                View rootView = inflater.inflate(R.layout.fragment_subpage59, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==57) {

                View rootView = inflater.inflate(R.layout.fragment_subpage60, container, false);
                return rootView;
            }

            else if(getArguments().getInt(ARG_SECTION_NUMBER)==58) {

                View rootView = inflater.inflate(R.layout.fragment_subpage61, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==59) {

                View rootView = inflater.inflate(R.layout.fragment_subpage62, container, false);
                return rootView;
            } else if(getArguments().getInt(ARG_SECTION_NUMBER)==60) {

                View rootView = inflater.inflate(R.layout.fragment_subpage63, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==61) {

                View rootView = inflater.inflate(R.layout.fragment_subpage64, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==62) {

                View rootView = inflater.inflate(R.layout.fragment_subpage65, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==63) {

                View rootView = inflater.inflate(R.layout.fragment_subpage66, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==64) {

                View rootView = inflater.inflate(R.layout.fragment_subpage67, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==65) {

                View rootView = inflater.inflate(R.layout.fragment_subpage68, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==66) {

                View rootView = inflater.inflate(R.layout.fragment_subpage69, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==67) {

                View rootView = inflater.inflate(R.layout.fragment_subpage70, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==68) {

                View rootView = inflater.inflate(R.layout.fragment_subpage71, container, false);
                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==69) {

                View rootView = inflater.inflate(R.layout.fragment_subpage72, container, false);
                return rootView;
            }


            else

            {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 69;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 1:

                    return "page 3";

                case 2:
                    return "page 4";
                case 3:

                    return "page 5";
                case 4:
                    return "page 6";
                case 5:
                    return "page 7";
                case 6:
                    return "page 8";

                case 7:
                    return "page 10";
                case 8:
                    return "page 11";
                case 9:
                    return "page 12";
                case 10:
                    return "page 13";
                case 11:
                    return "page 14";
                case 12:
                    return "page 15";
                case 13:
                    return "page 16";
                case 14:
                    return "page 17";
                case 15:
                    return "page 18";
                case 16:
                    return "page 19";
                case 17:
                    return "page 20";
                case 18:
                    return "page 21";
                case 19:
                    return "page 22";
                case 20:
                    return "page 23";
                case 21:
                    return "page 24";
                case 22:
                    return "page 25";
                case 23:
                    return "page 26";
                case 24:
                    return "page 27";
                case 25:
                    return "page 28";
                case 26:
                    return "page 29";
                case 27:
                    return "page 30";
                case 28:
                    return "page 31";
                case 29:
                    return "page 32";
                case 30:
                    return "page 33";
                case 31:
                    return "page 34";
                case 32:
                    return "page 35";
                case 33:
                    return "page 36";
                case 34:
                    return "page 37";
                case 35:
                    return "page 38";
                case 36:
                    return "page 39";
                case 37:
                    return "page 40";
                case 38:
                    return "page 41";
                case 39:
                    return "page 42";
                case 40:
                    return "page 43";
                case 41:
                    return "page 44";
                case 42:
                    return "page 45";
                case 43:
                    return "page 46";
                case 44:
                    return "page 47";
                case 45:
                    return "page 48";
                case 46:
                    return "page 49";
                case 47:
                    return "page 50";
                case 48:
                    return "page 51";
                case 49:
                    return "page 52";
                case 50:
                    return "page 53";
                case 51:
                    return "page 54";
                case 52:
                    return "page 55";
                case 53:
                    return "page 56";
                case 54:
                    return "page 57";
                case 55:
                    return "page 58";
                case 56:
                    return "page 59";
                case 57:
                    return "page 60";
                case 58:
                    return "page 61";
                case 59:
                    return "page 62";
                case 60:
                    return "page 63";
                case 61:
                    return "page 64";
                case 62:
                    return "page 65";
                case 63:
                    return "page 66";
                case 64:
                    return "page 67";
                case 65:
                    return "page 68";
                case 66:
                    return "page 69";
                case 67:
                    return "page 70";
                case 68:
                    return "page 71";
                case 69:
                    return "page 72";



            }
            return null;
        }
    }
}
