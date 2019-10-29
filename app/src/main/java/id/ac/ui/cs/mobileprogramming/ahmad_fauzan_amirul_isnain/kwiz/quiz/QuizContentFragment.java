package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.quiz;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
                        answerTextView = view.findViewById(R.id.option_a_text);
                        break;

                    case R.id.radio_b:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_b);
                        answerTextView = view.findViewById(R.id.option_b_text);
                        break;

                    case R.id.radio_c:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_c);
                        answerTextView = view.findViewById(R.id.option_c_text);
                        break;

                    case R.id.radio_d:
                        if (checked)
                            clearPreviousRadioButton(view, R.id.radio_d);
                        answerTextView = view.findViewById(R.id.option_d_text);
                        break;
                }
            }

            private void clearPreviousRadioButton(View view, int radioButtonId){
                if (previousButton!=null){
                    previousButton.setChecked(false);
                }
                previousButton = view.findViewById(radioButtonId);
            }
        });

        view.findViewById(R.id.radio_a).setOnClickListener(radioButtonClickListener);
        view.findViewById(R.id.radio_b).setOnClickListener(radioButtonClickListener);
        view.findViewById(R.id.radio_c).setOnClickListener(radioButtonClickListener);
        view.findViewById(R.id.radio_d).setOnClickListener(radioButtonClickListener);
    }
}
