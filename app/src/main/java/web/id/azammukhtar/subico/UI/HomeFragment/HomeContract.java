package web.id.azammukhtar.subico.UI.HomeFragment;

import org.json.JSONObject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;
import retrofit2.Response;
import web.id.azammukhtar.subico.Model.Product.Product;

public interface HomeContract {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void onGetSuccess(String message);
        void onGetError(String message);
    }

    interface GetHomeInteractor{
        interface OnFinishedListener{
            void onSuccess(Response<Product> product);
            void onError(Response<?> e);
            void onNextShow();
            void onNextHide();
        }
        void getProduct(OnFinishedListener onFinishedListener, PublishProcessor<Integer> pagination, CompositeDisposable compositeDisposable);
    }

    interface Presenter{
        void getProductPresenter(PublishProcessor<Integer> pagination, CompositeDisposable compositeDisposable);
    }
}
