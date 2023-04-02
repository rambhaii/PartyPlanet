package com.app.partyplanets.DashBoad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.partyplanets.R;
import com.app.partyplanets.Utils.UtilsMethod;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentGateway extends AppCompatActivity implements PaymentResultListener
{
    private EditText amountEdt;
    private Button payBtn;
    String cartId;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    String price;
    int actualprice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        radioGroup = findViewById(R.id.groupradio);

        cartId=getIntent().getStringExtra("cartId");
        payBtn = findViewById(R.id.idBtnPay);
        price=getIntent().getStringExtra("price");
        actualprice= Integer.parseInt(price)*100;




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
                    Toast.makeText(PaymentGateway.this, ""+paymentType, Toast.LENGTH_SHORT).show();

                    if (paymentType.equals("Online"))
                    {
                        startPayment();
                    }
                    else
                    {
                        Toast.makeText(PaymentGateway.this, "Booking  successful ", Toast.LENGTH_SHORT).show();
                        UtilsMethod.INSTANCE.payment(PaymentGateway.this,cartId,"cash","ascdsadf","cash",price,"asdfds","sdvsasd");

                    }
                }
                else
                {
                    Toast.makeText(PaymentGateway.this, "Please select  Payment Type", Toast.LENGTH_SHORT).show();

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
        UtilsMethod.INSTANCE.payment(PaymentGateway.this,cartId,"online",s,"successful",price,"njdsfjj",s);

        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPaymentError(int i, String s)
    {

    //    Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
        UtilsMethod.INSTANCE.alertBox("","Payment Failed due to error"+s,PaymentGateway.this);
    }
}