package com.aflowz.ecommerce.UI.ProductActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;

public class ProductDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, ProductListDetailData>>
            pageKeyedDataSourceMutableLiveData = new MutableLiveData<>();

    private String KEYWORD;
    private String SUB_CATEGORY;
    private String CATEGORY;
    private String SORT;
    private boolean RENT;

//    public ProductDataSourceFactory(String KEYWORD, String SUB_CATEGORY, String CATEGORY, String SORT, boolean RENT) {
//        this.KEYWORD = KEYWORD;
//        this.SUB_CATEGORY = SUB_CATEGORY;
//        this.CATEGORY = CATEGORY;
//        this.SORT = SORT;
//        this.RENT = RENT;
//    }

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
