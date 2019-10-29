package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizContentFragment extends Fragment {

    private TextView answerTextView;
    private RadioButton previousButton;
    private EditText noteEditText;
    private QuizContentViewModel viewModel;

    public static QuizContentFragment newInstance() {
        return new QuizContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener radioButtonClickListener = (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                switch(view.getId()) {
                    case R.id.radio_a:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_a);
                        answerTextView = getView().findViewById(R.id.option_a_text);
                        break;

                    case R.id.radio_b:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_b);
                        answerTextView = getView().findViewById(R.id.option_b_text);
                        break;

                    case R.id.radio_c:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_c);
                        answerTextView = getView().findViewById(R.id.option_c_text);
                        break;

                    case R.id.radio_d:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_d);
                        answerTextView = getView().findViewById(R.id.option_d_text);
                        break;
                }
            }

            private void clearPreviousRadioButton(View view, int radioButtonId){
                if (previousButton!=null){
                    previousButton.setChecked(false);
                }
                previousButton = getView().findViewById(radioButtonId);
            }
        });

        getView().findViewById(R.id.radio_a).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_b).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_c).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_d).setOnClickListener(radioButtonClickListener);

        noteEditText = getView().findViewById(R.id.noteEditText);
        viewModel = ViewModelProviders.of(getActivity()).get(QuizContentViewModel.class);

        getView().findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setNote(noteEditText.getText().toString());
                viewModel.setAnswer(answerTextView.getText().toString());
                getFragmentManager().beginTransaction()
                        .replace(R.id.quizContent, AnswerConfirmationFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
