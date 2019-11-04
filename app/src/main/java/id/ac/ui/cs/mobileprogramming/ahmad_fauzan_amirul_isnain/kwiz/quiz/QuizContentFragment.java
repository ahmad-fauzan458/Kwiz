package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.R;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.databinding.FragmentQuizContentBinding;
import id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.viewmodels.QuizContentViewModel;

public class QuizContentFragment extends Fragment {

    private TextView answerTextView;
    private RadioButton previousButton;
    private QuizContentViewModel viewModel;

    public static QuizContentFragment newInstance() {
        return new QuizContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentQuizContentBinding binding =
                FragmentQuizContentBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(QuizContentViewModel.class);
        binding.setQuizContentViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View.OnClickListener radioButtonClickListener = (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (previousButton != null && previousButton.getId() == view.getId()){
                    answerTextView = null;
                    previousButton.setChecked(false);
                    previousButton = null;
                    return;
                }

                changePreviousButton(view);

                switch(view.getId()) {
                    case R.id.radio_a:
                        if (checked) {
                            answerTextView = getView().findViewById(R.id.option_a_text);
                        }
                        break;

                    case R.id.radio_b:
                        if (checked) {
                            answerTextView = getView().findViewById(R.id.option_b_text);
                        }
                        break;

                    case R.id.radio_c:
                        if (checked) {
                            answerTextView = getView().findViewById(R.id.option_c_text);
                        }
                        break;

                    case R.id.radio_d:
                        if (checked) {
                            answerTextView = getView().findViewById(R.id.option_d_text);
                        }
                        break;
                }
            }

            private void changePreviousButton(View view){
                if (previousButton != null){
                    previousButton.setChecked(false);
                }
                previousButton = (RadioButton) view;
            }
        });

        getView().findViewById(R.id.radio_a).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_b).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_c).setOnClickListener(radioButtonClickListener);
        getView().findViewById(R.id.radio_d).setOnClickListener(radioButtonClickListener);

        getView().findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerTextView == null) {
                    showErrorAnswerNotChosen();
                    return;
                }
                viewModel.setAnswer(answerTextView.getText().toString());
                getFragmentManager().beginTransaction()
                        .replace(R.id.quizContent, AnswerConfirmationFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void showErrorAnswerNotChosen(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(getResources().getString(R.string.no_answer));
        alertDialog.setMessage(getResources().getString(R.string.no_answer_content));

        alertDialog.setNegativeButton(getResources().getString(R.string.back_to_quiz),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (previousButton != null) {
            previousButton = getView().findViewById(previousButton.getId());
        }
    }
}
