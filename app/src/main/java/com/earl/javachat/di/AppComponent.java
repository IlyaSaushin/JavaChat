package com.earl.javachat.di;

import com.earl.javachat.data.retrofit.Client;
import com.earl.javachat.ui.chat.base.ChatBaseFragment;
import com.earl.javachat.ui.chat.contacts.ContactsFragment;
import com.earl.javachat.ui.chat.contacts.addNewContact.AddNewContactFragment;
import com.earl.javachat.ui.chat.profile.ProfileFragment;
import com.earl.javachat.ui.chat.rooms.RoomsFragment;
import com.earl.javachat.ui.chat.rooms.newMessage.ChooseContractFragment;
import com.earl.javachat.ui.logIn.LogInFragment;
import com.earl.javachat.ui.register.RegisterFragment;
import com.earl.javachat.ui.register.UserDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {
    AuthorizationModule.class,
        DataModule.class,
        ChatModule.class,
        Client.class
})
public interface AppComponent {

    void injectRegisterFragment(RegisterFragment fragment);

    void injectLogInFragment(LogInFragment fragment);

    void injectUserDetailsFragment(UserDetailsFragment fragment);

    void injectChatFragment(RoomsFragment fragment);

    void injectAddNewContactFragment(AddNewContactFragment fragment);

    void injectBaseChatFragment(ChatBaseFragment fragment);

    void injectChooseContactFragment(ChooseContractFragment fragment);

    void injectContactsFragment(ContactsFragment fragment);

    void injectProfileFragment(ProfileFragment fragment);
}
