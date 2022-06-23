package com.gradle.shopifyapp.home.viewmodel

import android.content.Context
import android.os.Looper.getMainLooper
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gradle.shopifyapp.getOrAwaitValue
import com.gradle.shopifyapp.model.FakeRemoteSource
import com.gradle.shopifyapp.model.Repository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@Config(sdk =[30])
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@ExperimentalCoroutinesApi
class HomeViewModelTest() : TestCase() {
    private lateinit var remoteDataSource: FakeRemoteSource
    private lateinit var repo: Repository
    private lateinit var context: Context

    @Before
    fun createRepository() {
        context = ApplicationProvider.getApplicationContext<Context>()
        remoteDataSource = FakeRemoteSource()
        // Get a reference to the class under test
        repo = Repository.getRepoInstance(remoteDataSource, context)
    }


    @Test
    fun getAllVendors() = runBlockingTest {
        // When tasks are requested from the tasks repository
        val viewModel = HomeViewModel(repo,context)
        viewModel.getAllVendors(context)
        shadowOf(getMainLooper()).idle()
        val tasks = viewModel.liveVendorList.getOrAwaitValue()
        // Then tasks are loaded from the remote data source
        assertEquals(2,tasks.smart_collections.size)
    }

}
