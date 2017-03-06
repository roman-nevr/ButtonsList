package org.berendeev.buttonslist.di;

import android.content.Context;

import org.berendeev.buttonslist.data.RepositoryImpl;
import org.berendeev.buttonslist.data.datasource.DatabaseDataSource;
import org.berendeev.buttonslist.data.datasource.DatabaseOpenHelper;
import org.berendeev.buttonslist.domain.Repository;
import org.berendeev.buttonslist.domain.interactor.GetHistoryInteractor;
import org.berendeev.buttonslist.domain.interactor.GetItemInteractor;
import org.berendeev.buttonslist.domain.interactor.GetListInteractor;
import org.berendeev.buttonslist.domain.interactor.Interactor;
import org.berendeev.buttonslist.domain.interactor.InteractorExecutor;
import org.berendeev.buttonslist.domain.interactor.MainThreadInteractorExecutor;
import org.berendeev.buttonslist.domain.interactor.SaveItemInteractor;
import org.berendeev.buttonslist.domain.interactor.WorkInteractorExecutor;
import org.berendeev.buttonslist.domain.model.Item;

import java.util.List;

public class Injector {
    public static DatabaseOpenHelper provideDatabaseOpenHelper(Context context){
        Context appContext = context.getApplicationContext();
        return DatabaseOpenHelper.getInstance(appContext);
    }

    public static DatabaseDataSource provideDatabaseDataSource(Context context){
        Context appContext = context.getApplicationContext();
        return new DatabaseDataSource(provideDatabaseOpenHelper(appContext));
    }

    public static Repository provideRepository(Context context){
        Context appContext = context.getApplicationContext();
        return new RepositoryImpl(provideDatabaseDataSource(appContext));
    }

    public static InteractorExecutor provideWorkInteractorExecutor(){
        return WorkInteractorExecutor.getInstance();
    }

    public static InteractorExecutor provideMainThreadInteractorExecutor(){
        return MainThreadInteractorExecutor.getInstance();
    }

    public static Interactor<Void, List<Item>> provideGetListInteractor(Context context){
        Context appContext = context.getApplicationContext();
        return new GetListInteractor(provideWorkInteractorExecutor(), provideMainThreadInteractorExecutor(), provideRepository(appContext));
    }

    public static Interactor<Void, List<Item>> provideGetHistoryInteractor(Context context){
        Context appContext = context.getApplicationContext();
        return new GetHistoryInteractor(provideWorkInteractorExecutor(), provideMainThreadInteractorExecutor(), provideRepository(appContext));
    }

    public static Interactor<Item, Void> provideSaveItemInteractor(Context context){
        Context appContext = context.getApplicationContext();
        return new SaveItemInteractor(provideWorkInteractorExecutor(), provideMainThreadInteractorExecutor(), provideRepository(appContext));
    }

    public static Interactor<Integer, Item> provideGetItemInteractor(Context context){
        Context appContext = context.getApplicationContext();
        return new GetItemInteractor(provideWorkInteractorExecutor(), provideMainThreadInteractorExecutor(), provideRepository(appContext));
    }
}
