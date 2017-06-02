package layout.org.testlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import jm.org.itemlayout.XItemScrollLayout;

public class MainActivity extends AppCompatActivity {
    XItemScrollLayout iLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iLayout= (XItemScrollLayout) findViewById(R.id.itemLayout);
        View viewL= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_left,null);
        View viewR= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_right,null);
        iLayout.addLeftView(viewL);
        iLayout.addRightView(viewR);
    }
}
