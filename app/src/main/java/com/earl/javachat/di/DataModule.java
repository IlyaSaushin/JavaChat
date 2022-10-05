package com.earl.javachat.di;

import com.earl.javachat.data.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    Repository.BaseRepository provideRepository() {
        return new Repository.BaseRepository();
    }
}
