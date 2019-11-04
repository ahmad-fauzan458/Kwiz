package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentHomeBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.HomeInterface;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz.QuizActivity;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.UserViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeInterface {
    private UserViewModel userViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding
                = FragmentHomeBinding.inflate(inflater, container, false);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        binding.setUserViewModel(userViewModel);
        binding.setLifecycleOwner(getActivity());
        binding.setHomeInterface(this);
        return binding.getRoot();
    }

    public void showLeaderboard() {
        getFragmentManager().beginTransaction()
                .replace(R.id.activityMain, LeaderboardFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void exit() {
        getActivity().finish();
    }

    public void start() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("username", userViewModel.getName().getValue());
        startActivity(intent);
    }
}
