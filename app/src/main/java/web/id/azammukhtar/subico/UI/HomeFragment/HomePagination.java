package web.id.azammukhtar.subico.UI.HomeFragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

abstract public class HomePagination extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;

    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    protected HomePagination(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.d(TAG, "onScrolled: scroll ");
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        int totalItemCount = layoutManager.getItemCount();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = 0;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        int visibleThreshold = 5;
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
            loading = true;
        }
    }

    abstract public void onLoadMore(int currentPage, int totalItemCount, View view);
}
