package com.aflowz.ecommerce.UI.Admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.Datum;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.UI.ProductActivity.ProductDataSourceFactory;

import timber.log.Timber;

public class AdminViewModel extends ViewModel {
    LiveData<PagedList<Datum>> pagedListLiveData;
    LiveData<PageKeyedDataSource<Integer, Datum>> pageKeyedDataSourceLiveData;

    public AdminViewModel() {
        AdminDataSourceFactory adminDataSourceFactory = new AdminDataSourceFactory();
        pageKeyedDataSourceLiveData = adminDataSourceFactory.getPageKeyedDataSourceMutableLiveData();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        pagedListLiveData = (new LivePagedListBuilder(adminDataSourceFactory, pagedListConfig)).build();
    }

    void refresh() {
        pageKeyedDataSourceLiveData.getValue().invalidate();
        Timber.d("Refresh called");
    }
}
