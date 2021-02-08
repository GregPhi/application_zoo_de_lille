package com.example.zoodelille.data.repository.info;

import android.os.AsyncTask;

import com.example.zoodelille.data.api.object.info.Info;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;

import org.reactivestreams.Subscriber;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

public class InfoRepository {
    private final InfoLocalDataSource infoLocalDataSource;
    private final InfoRemoteDataSource infoRemoteDataSource;

    public InfoRepository(InfoLocalDataSource infoLocalDataSource, InfoRemoteDataSource infoRemoteDataSource) {
        this.infoLocalDataSource = infoLocalDataSource;
        this.infoRemoteDataSource = infoRemoteDataSource;
    }

    public Completable addInfo(InfoEntity zooEntity){
        return infoLocalDataSource.addInfo(zooEntity);
    }

    public Completable deleteInfoWithId(int id){
        return infoLocalDataSource.deleteInfoWithId(id);
    }

    public Flowable<List<InfoEntity>> getInfoEntity(){
        return infoLocalDataSource.getInfoEntity();
    }

    public Single<List<Info>> getAllInfos(){
        return infoRemoteDataSource.getAllInfos();
    }

    public boolean zooIsOpen(){
        boolean open = true;

        int whatDayIsToday = new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
        int whatNameDayIsToday = new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK);
        int whatMonthIsToday = new GregorianCalendar().get(GregorianCalendar.MONTH);
        int whatYearIsToday = new GregorianCalendar().get(GregorianCalendar.YEAR);

        String getAnnualClosureOldYear = infoLocalDataSource.getAnnualClosureOldYear();
        String getAnnualClosureNewYear = infoLocalDataSource.getAnnualClosureNewYear();
        String getExceptionalOpening = infoLocalDataSource.getExceptionalOpening();
        String[] except_open = getExceptionalOpening.split("\n");

        Date oldYear = new Date(getAnnualClosureOldYear);
        Date newYear = new Date(getAnnualClosureNewYear);

        if((whatNameDayIsToday == GregorianCalendar.THURSDAY) || (whatMonthIsToday == GregorianCalendar.JANUARY || whatMonthIsToday == GregorianCalendar.DECEMBER)){
            open = false;
        }else{
            if(oldYear.getYear() == whatYearIsToday){
                if(whatMonthIsToday == oldYear.getMonth() && whatDayIsToday > oldYear.getDay()){
                    open = false;
                }
            }
            if(newYear.getYear() == whatYearIsToday){
                if(whatMonthIsToday == newYear.getMonth() && whatDayIsToday > newYear.getDay()){
                    open = false;
                }
            }
            for(String e : except_open){
                Date e_op = new Date(e);
                if(whatMonthIsToday == e_op.getMonth() && whatDayIsToday > e_op.getDay()){
                    open = true;
                }
            }
        }
        return open;
    }
}
