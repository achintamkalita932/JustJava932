package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)
    {
        /**
         * This code is for the checkbox View of whipped cream by creating an object
         * and using methods like isChecked().
         */
        CheckBox whippedCheckBox = (CheckBox)findViewById(R.id.whipped_cream_box);
        boolean hasWhippedCream = whippedCheckBox.isChecked();

        /**
         * This code is for the checkbox View of Chocolate cream by creating an object
         * and using methods like isChecked().
         */
        CheckBox chocolateCreamBox = (CheckBox)findViewById(R.id.chocolate_cream_box);
        boolean hasChocolateCream = chocolateCreamBox.isChecked();

        /**
         * The code is for the EditText View and using methods like getText() which
         * return Editable datatype and to change the datatype to String we use chaining
         *  with toString() method.
         */
        EditText nameText = (EditText)findViewById(R.id.Name_text);
        String name = nameText.getText().toString();

        /**
         * Variable used for storing total Price.
         */
        int totalPrice = calculatePrice(15, hasWhippedCream, hasChocolateCream);

        /**
         * Variable used for Storing the order Summary.
         */
        String message = createOrderSummary(totalPrice, hasWhippedCream, hasChocolateCream, name);

        /**
         * Intent is used to use other app properties and it is initialise by using other app
         * properties and in this app we are using email intent(common intent).
         */
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(message);
    }

    /**
     * this is the increment method().
     * @param view
     */
    public void increment(View view){
        if(numberOfCoffees <= 100){
            numberOfCoffees++;
            display(numberOfCoffees);
        }

    }

    /**
     * this is the decrement method().
     * @param view
     */
    public void decrement(View view){
        if(numberOfCoffees > 0) {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * this is a method to calculate Price.
     * @param PriceForEachCupOfCoffee
     * @param hasWhippedCream
     * @param hasChocolateCream
     * @return
     */
    private int calculatePrice(int PriceForEachCupOfCoffee, boolean hasWhippedCream, boolean hasChocolateCream){
        int price = PriceForEachCupOfCoffee;
        if(hasWhippedCream){
            price = price + 5;
        }

        if(hasChocolateCream){
            price = price+ 10;
        }

        price = price * numberOfCoffees;
        return price;
    }

    /**
     * this is the method for order Summary.
     * @param totalPrice
     * @param hasWhippedCream
     * @param hasChocolateCream
     * @param name
     * @return
     */
    private String createOrderSummary(int totalPrice,boolean hasWhippedCream,boolean hasChocolateCream, String name){
      String orderSummary = "Name:- " + name;
      orderSummary = orderSummary + "\nWhipped Cream in your order? " + hasWhippedCream;
      orderSummary = orderSummary + "\nChocolate Cream in your order? " + hasChocolateCream;
      orderSummary = orderSummary + "\nQuantity:- " + numberOfCoffees;
      orderSummary = orderSummary + "\nTotal Price:- " + totalPrice;
      orderSummary = orderSummary + "\n" + getString(R.string.thank_you);
      return orderSummary;
    }


}