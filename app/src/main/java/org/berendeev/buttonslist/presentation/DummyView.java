package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class DummyView implements ButtonsListView, ButtonsListView.Router, SettingsView, SettingsView.Router, DetailsView, DetailsView.Router {
    @Override public void showItem(Item item) {

    }

    @Override public void showHistory(List<Item> items) {

    }

    @Override public void addItem(Item item) {

    }

    @Override public void showList(List<Item> items) {

    }

    @Override public void showError() {

    }

    @Override public void showInputError() {

    }

    @Override public void clearInputFields() {

    }

    @Override public void moveToSettings() {

    }

    @Override public void moveToDeatils(int number) {

    }

    @Override public void moveToButtonsList() {

    }
}
