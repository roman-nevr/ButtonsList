package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.interactor.Interactor;
import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class SettingsPresenter implements OnItemClick {
    private Interactor<Void, List<Item>> getHistoryInteractor;
    private Interactor<Item, Void> saveItemInteractor;

    private SettingsView view;
    private SettingsView.Router router;

    public SettingsPresenter(Interactor<Void, List<Item>> getHistoryInteractor,
                             Interactor<Item, Void> saveItemInteractor,
                             SettingsView view, SettingsView.Router router) {
        this.getHistoryInteractor = getHistoryInteractor;
        this.saveItemInteractor = saveItemInteractor;
        this.view = view;
        this.router = router;
    }

    public void start(){
        getHistoryInteractor.executeInteractor(null, new Interactor.Callback<List<Item>>() {
            @Override public void onSuccess(List<Item> responseValue) {
                view.showHistory(responseValue);
            }

            @Override public void onError(Throwable t) {

            }
        });
    }

    private void saveItem(Item item){
        saveItemInteractor.executeInteractor(item, new Interactor.Callback<Void>() {
            @Override public void onSuccess(Void responseValue) {
                //
            }

            @Override public void onError(Throwable t) {
                view.showError();
            }
        });
    }

    @Override public void onClick(int number) {
        //empty
    }

    public void onAddButtonClick(String numberString, String fillString) {
        try {
            int number = Integer.parseInt(numberString);
            float fill = Float.parseFloat(fillString);
            saveItem(new Item(number, fill));
        }catch (NumberFormatException e){
            view.showError();
        }
    }
}
