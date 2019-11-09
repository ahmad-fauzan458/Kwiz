package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.adapter.UserAdapter;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentLeaderboardBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.models.User;
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
        RecyclerView recyclerView = fragmentLeaderboardBinding.rvUserList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        UserViewModel userViewModel= ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });
    }
}
