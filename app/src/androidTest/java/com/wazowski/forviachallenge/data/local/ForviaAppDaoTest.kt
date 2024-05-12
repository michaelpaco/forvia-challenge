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
import kotlin.math.abs

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
        val forviaAppList = allApps.toList()
        forviaAppDao.insertAll(forviaAppList)
        val allApps = forviaAppDao.getAll(allApps.size).firstOrNull()
        assert(allApps != null && allApps.size == forviaAppList.size)
    }

    @Test
    fun getById() = testScope.runTest {
        val forviaAppList = allApps.toList()
        forviaAppDao.insertAll(forviaAppList)
        val found = forviaAppDao.getById(forviaAppList.first().id)
        assert(found.name == forviaAppList.first().name)
    }

    @Test
    fun deleteAll() = testScope.runTest {
        val forviaAppList = allApps.toList()
        forviaAppDao.insertAll(forviaAppList)
        forviaAppDao.deleteAll()
        val allApps = forviaAppDao.getAll(20).firstOrNull()
        assert(allApps.isNullOrEmpty())
    }

    @Test
    fun getMostDownloadedApps() = testScope.runTest {
        val forviaAppList = allApps.sortedByDescending { it.downloads }
        forviaAppDao.insertAll(forviaAppList)
        val mostDownloadedApps = forviaAppDao.getMostDownloadedApps().firstOrNull()

        assert(mostDownloadedApps != null && mostDownloadedApps == forviaAppList)
    }

    @Test
    fun getLatestAddedApps() = testScope.runTest {
        val forviaAppList = allApps.sortedByDescending { it.added }
        forviaAppDao.insertAll(forviaAppList)
        val latestAddedApps = forviaAppDao.getLatestAddedApps().firstOrNull()

        assert(latestAddedApps != null && latestAddedApps == forviaAppList)
    }

    @Test
    fun getAppsBySimilarRating() = testScope.runTest {
        val givenRating = 3.5f
        val threshold = 0.5f
        val similarRatingApps = allApps.filter { abs(it.rating - givenRating) <= threshold }
            .sortedByDescending { it.rating }
        forviaAppDao.insertAll(allApps)
        val appsBySimilarRating =
            forviaAppDao.getAppsBySimilarRating(givenRating, threshold).firstOrNull()
        assert(appsBySimilarRating != null && appsBySimilarRating == similarRatingApps)
    }

}