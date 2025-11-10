package com.example.myactivities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {
    
    private EditText etUserName;
    private EditText etUserAge;
    private EditText etMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUserName = view.findViewById(R.id.et_user_name);
        etUserAge = view.findViewById(R.id.et_user_age);
        etMessage = view.findViewById(R.id.et_message);

        Bundle args = getArguments();
        if (args != null) {
            String userName = args.getString("user_name");
            int userAge = args.getInt("user_age", 0);
            
            if (userName != null && !userName.isEmpty()) {
                etUserName.setText(userName);
            }
            if (userAge > 0) {
                etUserAge.setText(String.valueOf(userAge));
            }
        }

        Button btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String ageText = etUserAge.getText().toString().trim();
            
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), "Пожалуйста, введите имя", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (TextUtils.isEmpty(ageText)) {
                Toast.makeText(getContext(), "Пожалуйста, введите возраст", Toast.LENGTH_SHORT).show();
                return;
            }
            
            try {
                int age = Integer.parseInt(ageText);
                if (age <= 0 || age > 150) {
                    Toast.makeText(getContext(), "Возраст должен быть от 1 до 150", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                String message = etMessage.getText().toString().trim();
                
                // Save result in ViewModel to be picked up by MainFragment in onResume
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).getResultViewModel().saveResult(name, age, message);
                }
                
                Navigation.findNavController(v).navigateUp();
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Возраст должен быть числом", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            // Save cancel result in ViewModel
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).getResultViewModel().saveCancelResult();
            }
            
            Navigation.findNavController(v).navigateUp();
        });

        Button btnOpenThird = view.findViewById(R.id.btn_open_third);
        btnOpenThird.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String ageText = etUserAge.getText().toString().trim();
            
            int age = 25;
            try {
                if (!TextUtils.isEmpty(ageText)) {
                    age = Integer.parseInt(ageText);
                }
            } catch (NumberFormatException e) {
                age = 25;
            }
            
            Bundle thirdArgs = new Bundle();
            thirdArgs.putString("user_name", TextUtils.isEmpty(name) ? "Иван Иванов" : name);
            thirdArgs.putInt("user_age", age);
            Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_thirdFragment, thirdArgs);
        });
    }
}

