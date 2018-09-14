package com.box8.box8home;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IceCreamMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IceCreamMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IceCreamMenuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private List<String> menu;

    private OnFragmentInteractionListener mListener;

    private RecyclerView iceCreamListView;
    private ProgressBar progressBar;

    public IceCreamMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param menu Parameter 1.
     * @return A new instance of fragment IceCreamMenuFragment.
     */
    public static IceCreamMenuFragment newInstance(ArrayList<String> menu) {
        IceCreamMenuFragment fragment = new IceCreamMenuFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, menu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menu = getArguments().getStringArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ice_cream_menu, container, false);
        iceCreamListView = view.findViewById(R.id.ice_cream_list);
        progressBar = view.findViewById(R.id.loading);
        iceCreamListView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        MenuAdapter menuAdapter = new MenuAdapter(getContext(), menu, new CallBack() {
            @Override
            public void onImageLoaded() {
                iceCreamListView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception exp) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), exp.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        iceCreamListView.addItemDecoration(new RecyclerView.ItemDecoration() {
            /**
             * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
             * the number of pixels that the item view should be inset by, similar to padding or margin.
             * The default implementation sets the bounds of outRect to 0 and returns.
             * <p>
             * <p>
             * If this ItemDecoration does not affect the positioning of item views, it should set
             * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
             * before returning.
             * <p>
             * <p>
             * If you need to access Adapter for additional data, you can call
             * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
             * View.
             *
             * @param outRect Rect to receive the output.
             * @param view    The child view to decorate
             * @param parent  RecyclerView this ItemDecoration is decorating
             * @param state   The current state of RecyclerView.
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildLayoutPosition(view);
                int margin = getResources().getDimensionPixelSize(R.dimen.margin);
                outRect.top = margin;
                outRect.left = margin;
                outRect.right = margin;
                if (position == menu.size() - 1) {
                    outRect.bottom = margin;
                }
            }
        });
        iceCreamListView.setLayoutManager(layoutManager);
        iceCreamListView.setAdapter(menuAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
