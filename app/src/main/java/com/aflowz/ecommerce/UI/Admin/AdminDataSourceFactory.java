package com.aflowz.ecommerce.UI.Admin;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.Datum;
public class AdminDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Datum>>
            pageKeyedDataSourceMutableLiveData = new MutableLiveData<>();
    @Override
    public DataSource<Integer, Datum> create() {
        AdminDataSource datum = new AdminDataSource();
        pageKeyedDataSourceMutableLiveData.postValue(datum);

        return datum;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Datum>> getPageKeyedDataSourceMutableLiveData() {
        return pageKeyedDataSourceMutableLiveData;
    }


}
