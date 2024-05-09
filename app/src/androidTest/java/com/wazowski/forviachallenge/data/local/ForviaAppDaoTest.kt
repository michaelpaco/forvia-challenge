package com.wazowski.forviachallenge.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.wazowski.forviachallenge.common.allApps
import dagger.hilt.android.testing.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import javax.inject.*

@HiltAndroidTest
@SmallTest
class ForviaAppDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ForviaDatabase
    private lateinit var forviaAppDao: ForviaAppDao

    private val job = Job()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(job + testDispatcher)

    @Before
    fun setup() {
        hiltRule.inject()
        forviaAppDao = database.forviaAppDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAll() = testScope.runTest {
        val forviaApp = allApps.first()
        val forviaAppList = listOf(forviaApp)

        forviaAppDao.insertAll(forviaAppList)

        val elements = forviaAppDao.getAll().take(1).toList()
        assert(forviaAppList.contains(elements[0].first()))
    }
}