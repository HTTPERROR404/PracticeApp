package com.example.pocpracticeapp.ui.datalist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocpracticeapp.domain.model.ApiError
import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.usecase.GetListingsUseCase
import com.example.pocpracticeapp.domain.usecase.base.UseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class DataListViewModel  @Inject constructor(
    private val getListingsUseCase: GetListingsUseCase) : ViewModel() {

    val postsData = MutableLiveData<ListingsRoot>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    init {
        showProgressbar.value = false
    }

    fun getPosts() {
        if(postsData.value == null) {
            showProgressbar.value = true
            getListingsUseCase.invoke(
                viewModelScope, null,
                object : UseCaseResponse<ListingsRoot> {
                    override fun onSuccess(result: ListingsRoot) {
                        Log.i(TAG, "result: $result")
                        postsData.value = result
                        showProgressbar.value = false
                    }

                    override fun onError(apiError: ApiError?) {
                        messageData.value = apiError?.getErrorMessage()
                        showProgressbar.value = false
                    }
                },
            )
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = DataListViewModel::class.java.name
    }

}