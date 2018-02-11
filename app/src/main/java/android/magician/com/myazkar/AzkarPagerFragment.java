package android.magician.com.myazkar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Magician on 2/9/2018.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class AzkarPagerFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "Page_number";

    public AzkarPagerFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AzkarPagerFragment newInstance(int sectionNumber) {
        AzkarPagerFragment fragment = new AzkarPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_azkar, container, false);

        Bundle args = getArguments();
        if(args==null) throw new AssertionError();
        int pageNumber = args.getInt(ARG_PAGE_NUMBER);
        if(pageNumber==0) throw new AssertionError();

        // display   image
        // int imageResource = getActivity().getResources().getIdentifier(productId, "drawable", getActivity().getPackageName());
        ImageView imageView = rootView.findViewById(R.id.imageView);
//        imageView.setImageResource(setImageByPositon(pageNumber));
        Glide.with(this).load(setImageByPositon(pageNumber)).into(imageView);


        return rootView;
    }

    //define which image show depend on page number
    int setImageByPositon(int pageNumber){
        switch (pageNumber) {
            case 1:
                return R.drawable.azkar_page_1;
            case 2:
                return R.drawable.azkar_page_2;
            case 3:
                return R.drawable.azkar_page_3;
            case 4:
                return R.drawable.azkar_page_4;
            case 5:
                return R.drawable.azkar_page_5;
            case 6:
                return R.drawable.azkar_page_6;
           default:
               return R.drawable.azkar_page_7;
        }

    }
}
