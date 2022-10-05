package com.earl.javachat.di;

import com.earl.javachat.data.Repository;
import com.earl.javachat.ui.register.RegisterFormValidation;
import com.earl.javachat.ui.register.RegisterPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {

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
}
