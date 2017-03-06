package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.interactor.Interactor;
import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class ButtonsListPresenter implements OnItemClick{

    private ButtonsListView view;
    private ButtonsListView.Router router;
    private Interactor getListInteractor;

    public ButtonsListPresenter(Interactor getListInteractor, ButtonsListView view, ButtonsListView.Router router) {
        this.getListInteractor = getListInteractor;
        this.view = view;
        this.router = router;
    }

    public void start(){
        getListInteractor.executeInteractor(null, new Interactor.Callback<List<Item>>() {

            @Override public void onSuccess(List<Item> responseValue) {
                view.showList(responseValue);
            }

            @Override public void onError(Throwable t) {
                view.showError();
            }
        });
    }

    public void setView(ButtonsListView view) {
        this.view = view;
    }

    public void setRouter(ButtonsListView.Router router) {
        this.router = router;
    }

    @Override public void onClick(int number) {
        router.moveToDeatils(number);
    }

    public void onSettingsClick() {
        router.moveToSettings();
    }

    public void stop() {
        DummyView dummyView = new DummyView();
        view = dummyView;
        router = dummyView;
    }
}
