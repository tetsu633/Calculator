package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Integer[] mNumber = new Integer[15];
    private String[] mSigns = new String[15];
    private TextView mTotalTextView;
    private TextView mCalcTextView;
    private String mCurrentInput;
    private int mLength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get textview layout
        mTotalTextView = findViewById(R.id.totalTextView);
        mCalcTextView = findViewById(R.id.calcTextView);

        // init TextView
        mCalcTextView.setText("");
        mCurrentInput = "";

        // get button layout
        Button acButton = findViewById(R.id.ACButton);
        Button signChangeButton = findViewById(R.id.signChangeButton);
        Button surplusButton = findViewById(R.id.surplusButton);
        Button multiplyButton = findViewById(R.id.multiplyButton);
        Button minusButton = findViewById(R.id.minusButton);
        Button plusButton = findViewById(R.id.plusButton);
        Button equalButton = findViewById(R.id.equalButton);
        Button number0Button = findViewById(R.id.number0Button);
        Button number1Button = findViewById(R.id.number1Button);
        Button number2Button = findViewById(R.id.number2Button);
        Button number3Button = findViewById(R.id.number3Button);
        Button number4Button = findViewById(R.id.number4Button);
        Button number5Button = findViewById(R.id.number5Button);
        Button number6Button = findViewById(R.id.number6Button);
        Button number7Button = findViewById(R.id.number7Button);
        Button number8Button = findViewById(R.id.number8Button);
        Button number9Button = findViewById(R.id.number9Button);

        // set button onclick listener
        number0Button.setOnClickListener(view -> {
            mCurrentInput += "0";
            SetTextView();
        });

        number1Button.setOnClickListener(view -> {
            mCurrentInput += "1";
            SetTextView();
        });

        number2Button.setOnClickListener(view -> {
            mCurrentInput += "2";
            SetTextView();
        });

        number3Button.setOnClickListener(view -> {
            mCurrentInput += "3";
            SetTextView();
        });

        number4Button.setOnClickListener(view -> {
            mCurrentInput += "4";
            SetTextView();
        });

        number5Button.setOnClickListener(view -> {
            mCurrentInput += "5";
            SetTextView();
        });

        number6Button.setOnClickListener(view -> {
            mCurrentInput += "6";
            SetTextView();
        });

        number7Button.setOnClickListener(view -> {
            mCurrentInput += "7";
            SetTextView();
        });

        number8Button.setOnClickListener(view -> {
            mCurrentInput += "8";
            SetTextView();
        });

        number9Button.setOnClickListener(view -> {
            mCurrentInput += "9";
            SetTextView();
        });

        plusButton.setOnClickListener(view -> SetSign("+"));
        minusButton.setOnClickListener(view -> SetSign("-"));
        multiplyButton.setOnClickListener(view -> SetSign("×"));
        surplusButton.setOnClickListener(view -> SetSign("/"));

        acButton.setOnClickListener(view -> {
            mLength = 0;
            mCurrentInput = "";
            mNumber = new Integer[15];
            mSigns = new String[15];

            mTotalTextView.setText("");
            mCalcTextView.setText("0");
        });

        signChangeButton.setOnClickListener(view -> {
            if (mLength == 0 && !mCurrentInput.isEmpty() && !mCurrentInput.equals("0")) {
                mCurrentInput = String.valueOf(-Integer.parseInt(mCurrentInput));
            }
            SetTextView();
            mTotalTextView.setText(mCurrentInput);
        });

        equalButton.setOnClickListener(view -> {
            if (mCurrentInput.isEmpty() || mLength == 0) return;

            mNumber[mLength] = Integer.parseInt(mCurrentInput);

            int i = 0;
            int total = 0;
            while (!Objects.isNull(mSigns[i])) {
                switch (mSigns[i]) {
                    case "+":
                        total = i > 0 ? total + mNumber[i + 1] : mNumber[i] + mNumber[i + 1];
                        break;

                    case "-":
                        total = i > 0 ? total - mNumber[i + 1] : mNumber[i] - mNumber[i + 1];
                        break;

                    case "×":
                        total = i > 0 ? total * mNumber[i + 1] : mNumber[i] * mNumber[i + 1];
                        break;

                    case "/":
                        total = i > 0 ? total / mNumber[i + 1] : mNumber[i] / mNumber[i + 1];
                        break;

                    default:
                        break;
                }

                mTotalTextView.setText(String.valueOf(total));
                mCalcTextView.setText(String.valueOf(total));
                i++;
            }

            mLength = 0;
            mNumber = new Integer[15];
            mSigns = new String[15];
            mCurrentInput = String.valueOf(total);
        });
    }

    // Set Sign
    protected void SetSign(String sign) {
        if (!mCurrentInput.isEmpty()) {
            mNumber[mLength] = Integer.parseInt(mCurrentInput);

            mCurrentInput = "";
            mSigns[mLength] = sign;

            mLength++;
        }
    }

    // Set InputText TextView
    protected void SetTextView() {
        int i = 0;
        StringBuilder strBuilder = new StringBuilder();
        while (!Objects.isNull(mSigns[i])) {
            strBuilder.append(mNumber[i].toString());
            strBuilder.append(mSigns[i]);
            i++;
        }
        strBuilder.append(mCurrentInput);
        mCalcTextView.setText(strBuilder.toString());
    }
}