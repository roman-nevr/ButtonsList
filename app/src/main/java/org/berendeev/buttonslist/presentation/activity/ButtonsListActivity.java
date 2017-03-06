package org.berendeev.buttonslist.presentation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.berendeev.buttonslist.R;
import org.berendeev.buttonslist.di.Injector;
import org.berendeev.buttonslist.domain.model.Item;
import org.berendeev.buttonslist.presentation.ButtonsListPresenter;
import org.berendeev.buttonslist.presentation.ButtonsListView;
import org.berendeev.buttonslist.presentation.adapter.ButtonsListAdapter;

import java.util.List;


public class ButtonsListActivity extends AppCompatActivity implements ButtonsListView, ButtonsListView.Router{

    private ButtonsListPresenter presenter;
    private ButtonsListAdapter adapter;
    private RecyclerView recyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initDI();
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.start();
    }

    private void initDI() {
        presenter = new ButtonsListPresenter(Injector.provideGetListInteractor(getApplicationContext()), this, this);
    }

    @Override public void showList(List<Item> items) {
        if(adapter == null){
            adapter = new ButtonsListAdapter(items, presenter);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setItems(items);
        }
    }

    @Override public void showError() {

    }

    @Override public void showSettings() {
        SettingsActivity.start(this);
    }

    @Override public void showItem(int number) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_settings){
            presenter.onSettingsClick();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}
