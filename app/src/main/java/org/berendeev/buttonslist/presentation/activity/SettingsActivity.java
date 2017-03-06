package org.berendeev.buttonslist.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.berendeev.buttonslist.R;
import org.berendeev.buttonslist.di.Injector;
import org.berendeev.buttonslist.domain.model.Item;
import org.berendeev.buttonslist.presentation.SettingsPresenter;
import org.berendeev.buttonslist.presentation.SettingsView;
import org.berendeev.buttonslist.presentation.adapter.ButtonsListAdapter;

import java.util.List;


public class SettingsActivity extends AppCompatActivity implements SettingsView, SettingsView.Router {
    private SettingsPresenter presenter;
    private RecyclerView recyclerView;
    private ButtonsListAdapter adapter;
    private Button addButton;
    private EditText etRowNumber, etFillPercent;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDI();
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.settings);
        recyclerView = (RecyclerView) findViewById(R.id.history_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        etRowNumber = (EditText) findViewById(R.id.row_number);
        etFillPercent = (EditText) findViewById(R.id.fill_percent);
        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.onAddButtonClick(etRowNumber.getText().toString(), etFillPercent.getText().toString());
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.settings_title);
    }

    private void initDI() {
        Context context = getApplicationContext();
        presenter = new SettingsPresenter(Injector.provideGetHistoryInteractor(context),
                Injector.provideSaveItemInteractor(context), this, this);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.setRouter(this);
        presenter.start();
    }

    @Override protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override public void showHistory(List<Item> items) {
        if(adapter == null){
            adapter = new ButtonsListAdapter(items, presenter);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setItems(items);
        }
    }

    @Override public void addItem(Item item) {
        //adapter not null
        adapter.addItem(item);
    }

    @Override public void showError() {

    }

    @Override public void showInputError() {
        Snackbar.make(recyclerView, "Input Error", Snackbar.LENGTH_SHORT).show();
    }

    @Override public void clearInputFields() {
        etFillPercent.setText("");
        etRowNumber.setText("");
    }

    @Override public void moveToButtonsList() {
        onBackPressed();
    }

    public static void start(Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
