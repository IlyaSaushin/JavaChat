package com.earl.javachat.di;

import com.earl.javachat.ui.chat.contacts.addNewContact.AddNewContactFragment;
import com.earl.javachat.ui.chat.messages.ChatFragment;
import com.earl.javachat.ui.logIn.LogInFragment;
import com.earl.javachat.ui.register.RegisterFragment;
import com.earl.javachat.ui.register.UserDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {
    AuthorizationModule.class,
        DataModule.class,
        ChatModule.class
})
public interface AppComponent {

    void injectRegisterFragment(RegisterFragment fragment);

    void injectLogInFragment(LogInFragment fragment);

    void injectUserDetailsFragment(UserDetailsFragment fragment);

    void injectChatFragment(ChatFragment fragment);

    void injectAddNewContactFragment(AddNewContactFragment fragment);
}
