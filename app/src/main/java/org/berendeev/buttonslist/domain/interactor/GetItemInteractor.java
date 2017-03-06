package org.berendeev.buttonslist.domain.interactor;

import org.berendeev.buttonslist.domain.Repository;
import org.berendeev.buttonslist.domain.model.Item;

public class GetItemInteractor extends Interactor<Integer, Item> {

    private Repository repository;

    public GetItemInteractor(InteractorExecutor requestExecutor, InteractorExecutor responseExecutor, Repository repository) {
        super(requestExecutor, responseExecutor);
        this.repository = repository;
    }

    @Override protected void operation(Integer number, Callback<Item> callback) {
        try {
            Item item = repository.getItem(number);
            callback.onSuccess(item);
        }catch (Exception e){
            callback.onError(e);
        }
    }
}
