package org.berendeev.buttonslist.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.berendeev.buttonslist.R;
import org.berendeev.buttonslist.di.Injector;
import org.berendeev.buttonslist.domain.model.Item;
import org.berendeev.buttonslist.presentation.DetailsPresenter;
import org.berendeev.buttonslist.presentation.DetailsView;
import org.berendeev.buttonslist.views.FilledButton;


public class DetailsActivity extends AppCompatActivity implements DetailsView, DetailsView.Router {

    public static final String NUMBER = "number";
    private DetailsPresenter presenter;
    private int number;
    private TextView label;
    private FilledButton filledButton;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readIntent();
        initDI();
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.details);
        ActionBar actionBar = getSupportActionBar();
        Resources resources = getResources();
        String text = String.format(resources.getString(R.string.details_title), number);
        actionBar.setTitle(text);

        label = (TextView) findViewById(R.id.label);
        filledButton = (FilledButton) findViewById(R.id.filled_button);
    }

    private void readIntent() {
        number = getIntent().getIntExtra(NUMBER, -1);
        if(number == -1 || number > 99 || number < 0){
            throw new IllegalArgumentException("number must be >= 0 and < 100");
        }
    }

    private void initDI() {
        presenter = new DetailsPresenter(Injector.provideGetItemInteractor(this), this, this, number);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.setRouter(this);
        presenter.setView(this);
        presenter.start();
    }

    @Override protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override public void showItem(Item item) {
        label.setText("" + item.getNumber());
        filledButton.setFill(item.getFill());
    }

    @Override public void showError() {

    }

    @Override public void moveToButtonsList() {
    }

    public static void start(Context context, int number){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(NUMBER, number);
        context.startActivity(intent);
    }
}
