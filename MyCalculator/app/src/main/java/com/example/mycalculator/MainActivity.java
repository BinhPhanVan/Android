package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mycalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.edtInput.setShowSoftInputOnFocus(false);

        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnFour.setOnClickListener(this);
        binding.btnFive.setOnClickListener(this);
        binding.btnSix.setOnClickListener(this);
        binding.btnSeven.setOnClickListener(this);
        binding.btnEight.setOnClickListener(this);
        binding.btnNine.setOnClickListener(this);
        binding.btnZero.setOnClickListener(this);

        binding.btnDelete.setOnClickListener(this);
        binding.btnDot.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnMove.setOnClickListener(this);
        binding.btnPlus.setOnClickListener(this);
        binding.btnPlusremo.setOnClickListener(this);
        binding.btnDevide.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);
        binding.btnMul.setOnClickListener(this);


    }
    private void updateText(String strToAdd)
    {
        String  oldStr = binding.edtInput.getText().toString();
        int cursorPos  = binding.edtInput.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        binding.edtInput.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));

    }
    public static double eval(final String str) {
        return new Object()
        {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
    @Override
    public void onClick(View view) {
        Button value = (Button)view;
        String st = value.getText().toString();
        switch (st){
            case "=":
            {
                try
                {
                    String tmp = binding.edtInput.getText().toString();
                    double result = eval(tmp);
                    binding.edtInput.setText(String.valueOf(result));
                    binding.tvHistory.setText(tmp + " = " + result);
                }
                catch (Exception e)
                {
                    binding.edtInput.setText("");
                    binding.tvHistory.setText("Đầu vào không hợp lệ");
                }

                break;
            }
            case "C":
            {
                binding.edtInput.setText("");
                binding.tvHistory.setText("History");
                break;
            }
            case "->":
            {
                String tmp = binding.edtInput.getText().toString();
                binding.edtInput.setText(tmp.substring(0, tmp.length()-1));
                break;
            }
            default:
            {
                binding.edtInput.append(st);
                break;
            }

        }


    }

}