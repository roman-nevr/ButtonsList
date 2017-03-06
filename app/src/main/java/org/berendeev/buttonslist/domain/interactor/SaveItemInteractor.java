package org.berendeev.buttonslist.domain.interactor;

import org.berendeev.buttonslist.domain.Repository;
import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class SaveItemInteractor extends Interactor<Item, Void> {

    private Repository repository;

    public SaveItemInteractor(InteractorExecutor requestExecutor, InteractorExecutor responseExecutor, Repository repository) {
        super(requestExecutor, responseExecutor);
        this.repository = repository;
    }

    @Override protected void operation(Item requestValue, Callback<Void> callback) {
        try {
            repository.setItem(requestValue);
            callback.onSuccess(null);
        }catch (Exception e){
            callback.onError(e);
        }
    }
}
