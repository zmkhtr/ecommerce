package web.id.azammukhtar.subico.UI.HomeFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;
import retrofit2.Response;
import web.id.azammukhtar.subico.Model.Product.Product;

public class HomePresenter implements HomeContract.Presenter,HomeContract.GetHomeInteractor.OnFinishedListener{
    private HomeContract.View homeView;
    private HomeContract.GetHomeInteractor  homeInteractor;

    public HomePresenter(HomeContract.View homeView, HomeContract.GetHomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void onSuccess(Response<Product> product) {
        if (homeView != null) {
            homeView.hideProgressBar();
            homeView.onGetSuccess("Success fetch data");
        }
    }

    @Override
    public void onError(Response<?> e) {
        if (homeView != null) {
            homeView.hideProgressBar();
            homeView.onGetError("Error");
        }
    }

    @Override
    public void onNextShow() {
        if (homeView != null) {
            homeView.showProgressBar();
        }
    }

    @Override
    public void onNextHide() {
        if (homeView != null) {
            homeView.showProgressBar();
        }
    }


    @Override
    public void getProductPresenter(PublishProcessor<Integer> pagination, CompositeDisposable compositeDisposable) {
        homeInteractor.getProduct(this, pagination, compositeDisposable);
    }
}
