package be.gershon_lehrer.gettingcarassistance.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import be.gershon_lehrer.gettingcarassistance.App;
import be.gershon_lehrer.gettingcarassistance.model.Garage;
import be.gershon_lehrer.gettingcarassistance.service.GarageService;
import be.gershon_lehrer.gettingcarassistance.service.ServiceFactory;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import rx.Subscription;
import io.reactivex.schedulers.Schedulers;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;



import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by gershonlehrer on 25/04/2017.
 */

public class DataLinkLayer implements GarageService {
    private final static String TAG="DataLinkLayer";
    private GarageService mGarageService = ServiceFactory.buildRetrofitService();
    private Context mContext;

    public DataLinkLayer(Context context){
        mContext=context;
    }

    @Override
    public Garage getGarage(UUID id) {
        //this is a demo list used to simulate an online service
        List<Garage> garageList = new ArrayList<>();
        garageList.add(new Garage("Garage2018", "Consciensestraat 45", "b-2018", "Antwerpen", "Belgium"));
        garageList.add(new Garage("Garage Shimen", "Olijfstraat 12", "b-2018", "Knokke", "Belgium"));
        garageList.add(new Garage("GarageJan", "Haringrode 45", "b-2090", "Deurne", "Belgium"));
        garageList.add(new Garage("GarageTom", "Karel Oomstraat 3", "b-2030", "Antwerpen", "Belgium"));
        garageList.add(new Garage("GarageToyota", "Belgicastraat 12", "b-1000", "Brussel", "Belgium"));

        int num = new Random().nextInt(garageList.size());


        return garageList.get(num);
    }

    @Override
    public Observable<Garage> getGarageObservable() {
        final Observable<Garage> documentListObservable = mGarageService.getGarageObservable();

        if (!isNetworkAvailableAndConnectedWithToastMsg(mContext, "no network")) return null;

        //Create our subscription//
        documentListObservable.subscribe(mGarageObserver);

        return documentListObservable;
    }

    Observer<Garage> mGarageObserver = new Observer<Garage>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e(TAG, "onSubscribe: ");
        }

        @Override
        public void onNext(Garage garage) {
            Log.e(TAG, "onNext: " + garage);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ");
        }

        @Override
        public void onComplete() {
            Log.e(TAG, "onComplete: All Done!");
        }
    };







    public static boolean isNetworkAvailableAndConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

    public static boolean isNetworkAvailableAndConnectedWithToastMsg(Context context, String toastMessage) {

        boolean isNetworkConnected = isNetworkAvailableAndConnected(context);

        if (!isNetworkConnected && (toastMessage.length() > 0)) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }

        return isNetworkConnected;
    }
}
