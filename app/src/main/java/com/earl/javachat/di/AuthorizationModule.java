package com.earl.javachat.di;

import com.earl.javachat.data.Repository;
import com.earl.javachat.ui.chat.ChatPresenter;
import com.earl.javachat.ui.logIn.LogInFormValidation;
import com.earl.javachat.ui.logIn.LogInPresenter;
import com.earl.javachat.ui.register.RegisterFormValidation;
import com.earl.javachat.ui.register.RegisterPresenter;
import com.earl.javachat.ui.register.UserDetailsFormValidation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthorizationModule {

    @Provides
    @Singleton
    RegisterPresenter providePresenter(Repository.BaseRepository repository) {
        return new RegisterPresenter(repository);
    }

    @Provides
    @Singleton
    RegisterFormValidation providesValidation() {
        return new RegisterFormValidation.Base();
    }

    @Provides
    @Singleton
    LogInFormValidation provideLogInValidation() {
        return new LogInFormValidation.BaseValidation();
    }

    @Provides
    @Singleton
    LogInPresenter providesLogInPresenter(Repository.BaseRepository repository) {
        return new LogInPresenter(repository);
    }

    @Provides
    @Singleton
    ChatPresenter providesChatPresenter(Repository.BaseRepository repository) {
        return new ChatPresenter(repository);
    }

    @Provides
    @Singleton
    UserDetailsFormValidation providerUserDetailsValidation() {
        return new UserDetailsFormValidation.BaseUserDetailsValidation();
    }
}
