package com.example.lab_07;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.lab_07.Fragments.DetallesFragment;
import com.example.lab_07.Fragments.FormularioFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistrar, btnVerDetalles;
    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnVerDetalles = findViewById(R.id.btnVerDetalles);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(new FormularioFragment());
            }
        });

        btnVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFragment(new DetallesFragment());
            }
        });
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        isFragmentDisplayed = true;
    }

    @Override
    public void onBackPressed() {
        if (isFragmentDisplayed) {
            getSupportFragmentManager().popBackStack();
            findViewById(R.id.fragment_container).setVisibility(View.GONE);
            isFragmentDisplayed = false;
        } else {
            super.onBackPressed();
        }
    }
}
