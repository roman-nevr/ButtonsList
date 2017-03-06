package org.berendeev.buttonslist.domain.interactor;

import org.berendeev.buttonslist.domain.Repository;
import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class GetHistoryInteractor extends Interactor<Void, List<Item>> {

    private Repository repository;

    public GetHistoryInteractor(InteractorExecutor requestExecutor, InteractorExecutor responseExecutor, Repository repository) {
        super(requestExecutor, responseExecutor);
        this.repository = repository;
    }

    @Override protected void operation(Void requestValue, Callback<List<Item>> callback) {
        try {
            List<Item> items = repository.getHistory();
            callback.onSuccess(items);
        }catch (Exception e){
            callback.onError(e);
        }
    }
}
