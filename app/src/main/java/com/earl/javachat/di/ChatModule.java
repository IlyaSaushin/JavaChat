package com.earl.javachat.di;

import com.earl.javachat.data.Repository;
import com.earl.javachat.ui.chat.base.ChatBaseFragmentPresenter;
import com.earl.javachat.ui.chat.contacts.ContactsPresenter;
import com.earl.javachat.ui.chat.contacts.addNewContact.AddNewContactPresenter;
import com.earl.javachat.ui.chat.rooms.newMessage.ChooseContactPresenter;

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

    @Provides
    @Singleton
    ChatBaseFragmentPresenter provideBaseChatFragmentPresenter(Repository.BaseRepository repository) {
        return new ChatBaseFragmentPresenter(repository);
    }

    @Provides
    @Singleton
    ChooseContactPresenter provideChooseContactPresenter(Repository.BaseRepository repository) {
        return new ChooseContactPresenter(repository);
    }

    @Provides
    @Singleton
    ContactsPresenter provideContactsPresenter(Repository.BaseRepository repository) {
        return new ContactsPresenter(repository);
    }
}
