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

    private void saveItem(final Item item){
        saveItemInteractor.executeInteractor(item, new Interactor.Callback<Void>() {
            @Override public void onSuccess(Void responseValue) {
                //
                view.addItem(item);
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
            if(fill >= 0 && fill<=1 && number >=0 && number <100){
                saveItem(new Item(number, fill));
            }else {
                view.showInputError();
            }
        }catch (NumberFormatException e){
            view.showInputError();
        }
    }

    public void setView(SettingsView view) {
        this.view = view;
    }

    public void setRouter(SettingsView.Router router) {
        this.router = router;
    }

    public void stop() {
        DummyView dummyView = new DummyView();
        view = dummyView;
        router = dummyView;
    }
}
