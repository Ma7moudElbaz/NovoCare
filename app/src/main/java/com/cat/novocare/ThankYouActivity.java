package com.cat.novocare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;

public class ThankYouActivity extends LocalizationActivity {

    Button dismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        dismiss = findViewById(R.id.dismiss);
        dismiss.setOnClickListener(v -> onBackPressed());
    }
}