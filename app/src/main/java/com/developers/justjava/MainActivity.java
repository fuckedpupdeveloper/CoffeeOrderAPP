package com.developers.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


    }

    int x = 0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedbox = (CheckBox) findViewById( R.id.toppingsid );
        boolean haswhippedbox = whippedbox.isChecked();
        Log.v( "MainActivvity", " has topping " + haswhippedbox );

        CheckBox choclatebox = (CheckBox) findViewById( R.id.toppingsid2 );
        boolean haskaran = choclatebox.isChecked();
        Log.v( "MainActivity", "has topping" + haskaran );


        EditText text = (EditText) findViewById( R.id.nameid );
        String name = text.getText().toString();





        int price = calculateprice( haswhippedbox, haskaran );
        String pricemessage = createordersummary( name,price,haswhippedbox,haskaran );

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData( Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " +name );
        intent.putExtra( Intent.EXTRA_TEXT,pricemessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }






    }

    private int calculateprice(boolean addtopping, boolean haskar) {

        int baseprice = 5 ;


        if (addtopping) {
            return x * (baseprice + 1);

        }
        if (haskar) {
            return x * (baseprice + 2);


        }

        return x* baseprice ;
    }





    private String createordersummary ( String name ,  int price, boolean addtopping , boolean haskar)
    {
        String priceMessage = "NAME : " + name ;
        priceMessage = priceMessage + "\nWants Whippedcream topping : " + addtopping ;
        priceMessage = priceMessage + "\nWants Choclate topping : " + haskar ;
        priceMessage = priceMessage + "\nQuantity :" + x ;
        priceMessage = priceMessage + " \nTotal : $"  +price   ;
        priceMessage = priceMessage + "\n Thankyou ";

         return priceMessage ;

    }


    public void increment (View view)
    {

        if (x==20){

            Toast.makeText( this, "Cant have more than 20 cups of coffe" ,Toast.LENGTH_SHORT ).show();

            return;
        }
        x = x+1 ;
        displayquantity (x);
        displayPrice( x *5 );
    }

    public void decrement (View view) {

        if (x==0){

            Toast.makeText( this, "Cant have less than 1 cup of coffe", Toast.LENGTH_SHORT).show();
            return;
        }
        x= x-1 ;

        displayquantity( x);
        displayPrice( x*5 );
    }


    private void displayPrice(int numbr){

    }
    /**
     * This method displays the given quantity value on the screen.
     */



    private void displayquantity(int numd) {
        TextView quantityTextView = (TextView) findViewById( R.id.quantity_text_view );
        quantityTextView.setText( "" + numd );
    }

    /**
     * This method displays the given price on the screen.
     */


}