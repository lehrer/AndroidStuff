package be.gershon_lehrer.gettingcarassistance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import be.gershon_lehrer.gettingcarassistance.model.Garage;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by gershonlehrer on 25/04/2017.
 */

public interface GarageService {
    //TODO: correct this
    String SERVICE_ENDPOINT = "http://localhost.apiendpoint";

    Garage getGarage(UUID id);

    Observable<Garage> getGarageObservable();



}
