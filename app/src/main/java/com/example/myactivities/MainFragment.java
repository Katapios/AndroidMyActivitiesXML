package com.example.myactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment {

    private TextView tvResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnOpenSecond = view.findViewById(R.id.btn_open_second);
        btnOpenSecond.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("user_name", "Иван Иванов");
            args.putInt("user_age", 25);
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_thirdFragment, args);
        });

        Button btnOpenWithResult = view.findViewById(R.id.btn_open_with_result);
        btnOpenWithResult.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("user_name", "Иван Иванов");
            args.putInt("user_age", 25);
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_secondFragment, args);
        });

        Button btnOpenTabs = view.findViewById(R.id.btn_open_tabs);
        btnOpenTabs.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_tabFragment);
        });

        tvResult = view.findViewById(R.id.tv_result);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Check for saved results from SecondFragment
        if (getActivity() instanceof MainActivity) {
            ResultViewModel viewModel = ((MainActivity) getActivity()).getResultViewModel();
            if (viewModel.hasResult()) {
                if (viewModel.isResultCancelled()) {
                    setCanceled();
                } else {
                    updateResult(
                        viewModel.getResultName(),
                        viewModel.getResultAge(),
                        viewModel.getResultMessage()
                    );
                }
                viewModel.clearResult();
            }
        }
    }

    public void updateResult(String name, int age, String message) {
        if (tvResult != null) {
            if (name != null && age > 0) {
                String displayText = String.format("Получены данные:\nИмя: %s\nВозраст: %d", name, age);
                if (message != null && !message.isEmpty()) {
                    displayText += "\nСообщение: " + message;
                }
                tvResult.setText(displayText);
                Toast.makeText(getContext(), "Данные успешно получены!", Toast.LENGTH_SHORT).show();
            } else {
                tvResult.setText(message != null ? "Результат: " + message : "Данные отсутствуют");
            }
        }
    }

    public void setCanceled() {
        if (tvResult != null) {
            tvResult.setText("Операция отменена");
            Toast.makeText(getContext(), "Операция отменена", Toast.LENGTH_SHORT).show();
        }
    }
}

