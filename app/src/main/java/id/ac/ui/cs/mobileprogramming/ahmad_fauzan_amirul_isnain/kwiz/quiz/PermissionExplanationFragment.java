package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentPermissionExplanationBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.interfaces.PermissionExplanationInterface;

public class PermissionExplanationFragment extends Fragment
        implements PermissionExplanationInterface {

    public static PermissionExplanationFragment newInstance() {
        return new PermissionExplanationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPermissionExplanationBinding binding =
                FragmentPermissionExplanationBinding.inflate(inflater, container, false);
        binding.setPermissionExplanationInterface(this);
        return binding.getRoot();
    }

    @Override
    public void back() {
        QuizActivity quizActivity = (QuizActivity)getActivity();
        quizActivity.back();
    }
}
