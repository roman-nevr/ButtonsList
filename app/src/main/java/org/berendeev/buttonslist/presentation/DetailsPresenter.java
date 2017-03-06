package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.interactor.Interactor;
import org.berendeev.buttonslist.domain.model.Item;

public class DetailsPresenter {

    private Interactor<Integer, Item> getItemInteractor;
    private DetailsView view;
    private DetailsView.Router router;
    private int number;

    public DetailsPresenter(Interactor<Integer, Item> getItemInteractor, DetailsView view, DetailsView.Router router, int number) {
        this.getItemInteractor = getItemInteractor;
        this.view = view;
        this.router = router;
        this.number = number;
    }

    public void start(){
        getItemInteractor.executeInteractor(number, new Interactor.Callback<Item>() {
            @Override public void onSuccess(Item item) {
                view.showItem(item);
            }

            @Override public void onError(Throwable t) {

            }
        });
    }

    public void stop(){
        DummyView dummyView = new DummyView();
        view = dummyView;
        router = dummyView;
    }

    public void setView(DetailsView view) {
        this.view = view;
    }

    public void setRouter(DetailsView.Router router) {
        this.router = router;
    }
}
