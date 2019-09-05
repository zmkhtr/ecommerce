package web.id.azammukhtar.subico.UI.HomeFragment;

import android.util.Log;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.Utils.SessionManager;

public class HomeInteractor implements HomeContract.GetHomeInteractor{
    private static final String TAG = "HomeInteractor";

    @Override
    public void getProduct(OnFinishedListener onFinishedListener, PublishProcessor<Integer> pagination, CompositeDisposable compositeDisposable) {
        Log.d(TAG, "getProduct: executed ");
        Disposable disposable = pagination.onBackpressureDrop()
                .doOnNext(integer -> onFinishedListener.onNextShow())
                .concatMap((Function<Integer, Publisher<Response<Product>>>) HomeInteractor::getProductList)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(productResponse -> {
                    if (productResponse.isSuccessful()) {
                        onFinishedListener.onSuccess(productResponse);
                    }
                    onFinishedListener.onNextHide();
                })
                .doOnError(throwable -> {
                    if (throwable instanceof HttpException) {
                        Response<?> response = ((HttpException) throwable).response();
                        onFinishedListener.onError(response);
                    }
                })
                .subscribe();

        compositeDisposable.add(disposable);
        pagination.onNext(0);
    }

    private static Flowable<Response<Product>> getProductList(int page) {
        return ApiNetwork.getApiInterface().getProducts("Bearer " + SessionManager.getInstance().getUserToken(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
