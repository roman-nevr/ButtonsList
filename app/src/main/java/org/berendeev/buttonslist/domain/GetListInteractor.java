package org.berendeev.buttonslist.domain;

import java.util.ArrayList;
import java.util.List;

public class GetListInteractor extends Interactor<Void, List<Float>> {

    public GetListInteractor(InteractorExecutor requestExecutor, InteractorExecutor responseExecutor) {
        super(requestExecutor, responseExecutor);
    }

    @Override protected void operation(Void requestValue, Callback<List<Float>> callback) {
        //long operation
        //if success
        callback.onSuccess(new ArrayList<Float>());
    }
}
