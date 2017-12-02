package mobileappcompany.weekonedailyfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class EMICalculatorActivity extends AppCompatActivity {

    public static final String LOAN_AMOUNT = "LOAN_AMOUNT";
    public static final String CUSTOM_INTEREST_RATE = "CUSTOM_INTEREST_RATE";

    private EditText etTenYearEditText;
    private EditText etLoanEditText;
    private EditText etFifteenTeenEditText;
    private EditText etThirtyYearEditText;
    private TextView tvCustomRateTextView;
    private TextView tvPercentRateTextView;
    private SeekBar customSeekBar;

    private double currentLoanAmount;
    private double currentCustomRate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emicalculator);

        //check if the app just started or it's been restore from memory
        if (savedInstanceState == null) {
            currentLoanAmount = 0.0;
            currentCustomRate = 5;
        } else {

            currentLoanAmount = savedInstanceState.getDouble(LOAN_AMOUNT);
            currentCustomRate = savedInstanceState.getDouble(CUSTOM_INTEREST_RATE);
        }

        tvCustomRateTextView = findViewById(R.id.tvCustomRateTextView);
        tvPercentRateTextView = findViewById(R.id.tvPercentRateTextView);


        etLoanEditText = findViewById(R.id.etLoanEditText);
        etLoanEditText.addTextChangedListener(loanEditTextWatcher);

        customSeekBar = findViewById(R.id.seekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);

        etTenYearEditText = findViewById(R.id.etTenYearEditText);
        etFifteenTeenEditText = findViewById(R.id.etFifteenYearEditText);
        etThirtyYearEditText = findViewById(R.id.etThirtyEditText);

    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveInstance) {
        super.onSaveInstanceState(onSaveInstance);

        onSaveInstance.putDouble(LOAN_AMOUNT, currentLoanAmount);
        onSaveInstance.putDouble(CUSTOM_INTEREST_RATE, currentCustomRate);
    }

    private double calculateMonthlyLoanAmount(double loanAmount, double rate, int term) {

        double lRate = rate / 100 / 12;
        double lTerm = term * 12;
        double lLoanAmount = loanAmount * (lRate * Math.pow(1 + lRate, lTerm)) /
                (Math.pow(1 + lRate, lTerm) - 1);
        return lLoanAmount;
    }

    private void updateMonthlyPayment() {
        double tenYearMonthlyPayment =
                calculateMonthlyLoanAmount(currentLoanAmount, currentCustomRate, 10);
        double fifteenYearMonthlyPayment =
                calculateMonthlyLoanAmount(currentLoanAmount, currentCustomRate, 15);
        double thirtyYearMonthlyPayment =
                calculateMonthlyLoanAmount(currentLoanAmount, currentCustomRate, 30);

        etTenYearEditText.setText("$" + String.format("%.0f", tenYearMonthlyPayment));
        etFifteenTeenEditText.setText("$" + String.format("%.0f", fifteenYearMonthlyPayment));
        etThirtyYearEditText.setText("$" + String.format("%.0f", thirtyYearMonthlyPayment));
    }

    private void updateCustomRate() {
        tvPercentRateTextView.setText(String.format("%.02f", currentCustomRate) + "%");
        updateMonthlyPayment();
    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                    //currentCustomRate = seekBar.getProgress() / 100.0;
                    currentCustomRate = progressValue / 100.0;
                    updateCustomRate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private TextWatcher loanEditTextWatcher =
            new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    try {
                        if(charSequence.length() != 0) {
                            currentLoanAmount = Double.parseDouble(charSequence.toString());
                        }
                    } catch (NumberFormatException e) {
                        currentLoanAmount = 0.0;
                    }

                    updateMonthlyPayment();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
            };
}
