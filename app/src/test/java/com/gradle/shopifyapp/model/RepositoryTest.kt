package com.gradle.shopifyapp.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Response

@Config(sdk =[30])
@RunWith(AndroidJUnit4::class)
class RepositoryTest() : TestCase() {
    private lateinit var remoteDataSource: FakeRemoteSource
    private lateinit var repo: Repository

    @Before
    fun createRepository() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        remoteDataSource = FakeRemoteSource()
        // Get a reference to the class under test
        repo = Repository(remoteDataSource, context)
    }

    @Test
    fun getAllVendors() = runBlocking {
        // When tasks are requested from the tasks repository
        val tasks = repo.getAllVendors()
        // Then tasks are loaded from the remote data source
        assertEquals(2, tasks.body()?.smart_collections?.size)
    }

    @Test
    fun getProductsByBrand() = runBlocking {
        val tasks = repo.getAllProductsByBrand("")
        assertEquals(1, tasks.body()?.products?.size)
    }

    @Test
    fun getAllDiscountCodes() = runBlocking {
        val tasks = repo.getAllDiscountCodes()
        assertEquals(1, tasks.body()?.discount_codes?.size)
    }

    @Test
    fun getDraftOrders() = runBlocking {
        val tasks = repo.getDraftOrders()
        assertEquals(1, tasks.body()?.draft_orders?.size)
    }
}