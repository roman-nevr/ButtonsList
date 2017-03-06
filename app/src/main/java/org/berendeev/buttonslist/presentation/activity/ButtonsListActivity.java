package org.berendeev.buttonslist.presentation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.buttons_list_title);
    }

    @Override protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.setRouter(this);
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

    @Override public void moveToSettings() {
        SettingsActivity.start(this);
    }

    @Override public void moveToDeatils(int number) {
        DetailsActivity.start(this, number);
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

    @Override protected void onStop() {
        super.onStop();
        presenter.stop();
    }


}
