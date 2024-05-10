package com.wazowski.forviachallenge.data.repository

import com.wazowski.forviachallenge.common.Resource
import com.wazowski.forviachallenge.data.local.ForviaAppDao
import com.wazowski.forviachallenge.data.mappers.toForviaApp
import com.wazowski.forviachallenge.data.remote.*
import com.wazowski.forviachallenge.domain.model.ForviaApp
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ForviaApiRepositoryImplTest {

    @Mock
    private lateinit var api: ForviaApi
    private lateinit var repository: ForviaApiRepositoryImpl

    private val mockAppDto = ForviaAppDto(
        id = 67566705,
        name = "App name",
        packageName = "com.mypackage.reader",
        storeId = 2497975,
        storeName = "storeName",
        vername = "24.4.0.33145",
        vercode = 1930233145,
        md5sum = "5e0872a6bd167738165419f0fcd9f4a3",
        apkTags = emptyList(),
        size = 114039746,
        downloads = 9651901,
        pdownloads = 9651901,
        added = "2011-09-23 01:51:42",
        modified = "2024-05-06 07:15:09",
        updated = "2024-05-07 23:26:14",
        rating = 0f,
        icon = "FAKE_URL",
        graphic = "FAKE_URL",
        uptype = "aptuploader"
    )

    private val mockApp = mockAppDto.toForviaApp()

    private val mockDataset = Datasets(
        All(
            Info("OK", Time(0.03159499168395996, "32 milliseconds")), Data(
                total = 1, offset = 0, limit = 1, next = 1, hidden = 0, list = listOf(mockAppDto)
            )
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ForviaApiRepositoryImpl(api)
    }

    @Test
    fun `getAppsList success test`() = runBlocking {
        val offset = 0
        `when`(api.getAppsList(offset)).thenReturn(ForviaDatasetDto(mockDataset))
        val result = repository.getAllApps(offset)

        assertEquals(
            Resource.Success(listOf(mockApp)).data?.first()?.name, result.data?.first()?.name
        )
    }

    @Test
    fun `getAppsList error test`() = runBlocking {
        val offset = 0
        val errorMsg = "Some error occurred"
        `when`(api.getAppsList(offset)).thenThrow(RuntimeException(errorMsg))
        val result = repository.getAllApps(offset)
        assertEquals(Resource.Error<List<ForviaApp>>(errorMsg).message, result.message)
    }
}