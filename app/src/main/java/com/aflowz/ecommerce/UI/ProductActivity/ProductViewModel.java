package com.aflowz.ecommerce.UI.ProductActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;

public class ProductViewModel extends ViewModel {
    LiveData<PagedList<ProductListDetailData>> pagedListLiveData;
    LiveData<PageKeyedDataSource<Integer, ProductListDetailData>> pageKeyedDataSourceLiveData;


    public ProductViewModel() {
        ProductDataSourceFactory productDataSource = new ProductDataSourceFactory();
        pageKeyedDataSourceLiveData = productDataSource.getPageKeyedDataSourceMutableLiveData();

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        pagedListLiveData = (new LivePagedListBuilder(productDataSource, pagedListConfig)).build();
    }


    void refresh() {
        pagedListLiveData.getValue().getDataSource().invalidate();
    }
}
