package org.berendeev.buttonslist.presentation;

import org.berendeev.buttonslist.domain.model.Item;

public interface DetailsView {
    void showItem(Item item);
    void showError();

    public interface Router{
        void moveToButtonsList();
    }
}
