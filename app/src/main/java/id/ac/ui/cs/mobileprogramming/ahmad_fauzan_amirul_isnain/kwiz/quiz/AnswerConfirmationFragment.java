package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerConfirmationFragment extends Fragment {
    private QuizContentViewModel viewModel;
    private TextView yourAnswerText;
    private TextView yourNoteText;

    public static AnswerConfirmationFragment newInstance() {
        return new AnswerConfirmationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(QuizContentViewModel.class);

        yourAnswerText = getView().findViewById(R.id.yourAnswerText);
        yourAnswerText.setText(viewModel.getAnswer());

        yourNoteText = getView().findViewById(R.id.yourNoteText);
        yourNoteText.setText(viewModel.getNote());
    }
}
