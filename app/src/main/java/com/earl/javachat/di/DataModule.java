package com.earl.javachat.di;

import com.earl.javachat.data.Repository;
import com.earl.javachat.data.retrofit.Service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    Repository.BaseRepository provideRepository(Service service) {
        return new Repository.BaseRepository(service);
    }
}
