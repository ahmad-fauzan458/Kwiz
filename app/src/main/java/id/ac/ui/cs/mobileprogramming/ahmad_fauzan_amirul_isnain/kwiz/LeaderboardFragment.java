package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.adapter.UserAdapter;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentLeaderboardBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;

public class LeaderboardFragment extends Fragment {

    private FragmentLeaderboardBinding fragmentLeaderboardBinding;
    public static LeaderboardFragment newInstance() {
        return new LeaderboardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLeaderboardBinding =
                FragmentLeaderboardBinding.inflate(inflater, container, false);
        setUpRecyclerView();
        return fragmentLeaderboardBinding.getRoot();
    }


    private void setUpRecyclerView() {
        RecyclerView recyclerView = fragmentLeaderboardBinding.rvUserList; // In xml we have given id rv_movie_list to RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        UserViewModel userViewModel= ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        UserAdapter adapter = new UserAdapter(userViewModel.getAllUsers());
        recyclerView.setAdapter(adapter);
    }
}
