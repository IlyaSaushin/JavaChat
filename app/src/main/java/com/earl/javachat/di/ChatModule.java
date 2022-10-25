package com.earl.javachat.di;

import com.earl.javachat.data.Repository;
import com.earl.javachat.ui.chat.contacts.addNewContact.AddNewContactPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatModule {

    @Provides
    @Singleton
    AddNewContactPresenter provideAddNewContactPresenter(Repository.BaseRepository repository) {
        return new AddNewContactPresenter(repository);
    }
}
