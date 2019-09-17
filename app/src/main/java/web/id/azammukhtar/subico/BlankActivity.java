package web.id.azammukhtar.subico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import web.id.azammukhtar.subico.UI.CartFragment.CartFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeATKFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomePriaFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeWanitaFragment;

import static web.id.azammukhtar.subico.MainMenuActivity.CART_SCENARIO;
import static web.id.azammukhtar.subico.MainMenuActivity.SCENARIO;

public class BlankActivity extends AppCompatActivity {
    private static final String TAG = "BlankActivity";
    private String CODE_SCENARIO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        Intent intent = getIntent();
        CODE_SCENARIO = intent.getStringExtra(SCENARIO);

        getLayout();

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Your Cart");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getLayout(){
        if (CODE_SCENARIO.equals(CART_SCENARIO)) {
            getSupportFragmentManager().beginTransaction().add(R.id.blankContainer, new CartFragment(), "1").commit();
        }
        if (CODE_SCENARIO.equals("Woman")) {
            getSupportFragmentManager().beginTransaction().add(R.id.blankContainer, new HomeWanitaFragment(), "2").commit();
        }
        if (CODE_SCENARIO.equals("Men")) {
            getSupportFragmentManager().beginTransaction().add(R.id.blankContainer, new HomePriaFragment(), "2").commit();
        }
        if (CODE_SCENARIO.equals("ATK")) {
            getSupportFragmentManager().beginTransaction().add(R.id.blankContainer, new HomeATKFragment(), "2").commit();
        }
    }


}
