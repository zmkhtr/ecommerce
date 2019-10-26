package com.aflowz.ecommerce.UI.ProductActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;

public class ProductDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, ProductListDetailData>>
            pageKeyedDataSourceMutableLiveData = new MutableLiveData<>();


    @Override
    public DataSource<Integer, ProductListDetailData> create() {
        ProductDataSource productListDetailData = new ProductDataSource();
        pageKeyedDataSourceMutableLiveData.postValue(productListDetailData);

        return productListDetailData;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ProductListDetailData>> getPageKeyedDataSourceMutableLiveData() {
        return pageKeyedDataSourceMutableLiveData;
    }


}
