package com.earl.javachat.di;

import com.earl.javachat.ui.register.RegisterFragment;
import com.earl.javachat.ui.register.UserDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {
    RegisterModule.class,
        DataModule.class
})
public interface AppComponent {

    void injectRegisterFragment(RegisterFragment fragment);

    void injectUserDetailsFragment(UserDetailsFragment fragment);
}
