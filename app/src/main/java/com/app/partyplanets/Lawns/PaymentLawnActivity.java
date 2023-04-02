package com.app.partyplanets.Lawns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.partyplanets.LawnModel.Lawn;
import com.app.partyplanets.LawnModel.Lawndatum;
import com.app.partyplanets.R;
import com.app.partyplanets.Utils.UtilsMethod;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentLawnActivity extends AppCompatActivity implements PaymentResultListener, View.OnClickListener
{
    Lawn lawn;
    Lawndatum lawndatum;
    private EditText amountEdt;
    private Button payBtn;
    int cartId,amount;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    int actualprice;
    EditText note;
    TextView hotel;
    ImageView back;
    String data;
    TextView totalamount_tv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_lawn);
        data=getIntent().getStringExtra("data");
        Log.d("hjdgjhgjhjhdf",new Gson().toJson(data));

        lawn=new Gson().fromJson(data,Lawn.class);
        lawndatum=lawn.getData();
        cartId= Integer.parseInt(lawndatum.getCart_id());
        amount=lawndatum.getTotal_amount();

        radioGroup = findViewById(R.id.groupradio);
        payBtn = findViewById(R.id.idBtnPay);
        hotel = findViewById(R.id.hotel);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        hotel.setText("Payment Mode");
        note = findViewById(R.id.note);
        totalamount_tv = findViewById(R.id.totalamount_tv);

        totalamount_tv.setText(" ₹."+amount);
        actualprice=amount*100;

        payBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1)
                {
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    String paymentType = selectedRadioButton.getText().toString();


                    if (paymentType.equals("Online"))
                    {
                        startPayment();
                    }
                    else
                    { UtilsMethod.INSTANCE.lawnPayment(PaymentLawnActivity.this, String.valueOf(cartId),"cash","ascdsadf","cash", String.valueOf(amount),"asdfds","sdvsasd");
                    }
                }
                else
                {
                    Toast.makeText(PaymentLawnActivity.this, "Please select  Payment Type", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void startPayment()
    {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_Ujd8385mQjXqFH");

        checkout.setImage(R.drawable.abc);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", actualprice);//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {
        UtilsMethod.INSTANCE.lawnPayment(PaymentLawnActivity.this, String.valueOf(cartId),"online",s,"successful", String.valueOf(amount),"njdsfjj",s);

    }

    @Override
    public void onPaymentError(int i, String s)
    {
        UtilsMethod.INSTANCE.alertBox("","Payment Failed due to error"+s,PaymentLawnActivity.this);
    }

    @Override

    public void onClick(View v)
    {
        if (v==back)
        {
            finish();
        }
    }
}