package com.kelly.footballmatch.koin.modules

import com.kelly.footballmatch.data.network.service.LocalRepositoryApi
import org.koin.dsl.module.module

var Appmodule = module{
    single { LocalRepositoryApi(get()) }


}