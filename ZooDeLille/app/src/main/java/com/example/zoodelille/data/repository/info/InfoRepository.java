package com.example.zoodelille.data.repository.info;

import android.annotation.SuppressLint;

import com.example.zoodelille.data.api.object.info.Info;
import com.example.zoodelille.data.entity.info.InfoEntity;
import com.example.zoodelille.data.repository.info.local.InfoLocalDataSource;
import com.example.zoodelille.data.repository.info.remote.InfoRemoteDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

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

    public Single<Boolean> zooIsOpen(){
        Single<String> getAnnualClosureOldYear = infoLocalDataSource.getAnnualClosureOldYear();
        Single<String> getAnnualClosureNewYear = infoLocalDataSource.getAnnualClosureNewYear();
        Single<String> getExceptionalOpening = infoLocalDataSource.getExceptionalOpening();

        return Single.zip(getAnnualClosureOldYear, getAnnualClosureNewYear, getExceptionalOpening,
                new Function3<String, String, String, Boolean>() {
                    @Override
                    public Boolean apply(String closeO, String closeN, String exceptO) throws Exception {
                        boolean open = true;
                        int whatDayIsToday = new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
                        int whatNameDayIsToday = new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK);
                        int whatMonthIsToday = new GregorianCalendar().get(GregorianCalendar.MONTH);
                        int whatYearIsToday = new GregorianCalendar().get(GregorianCalendar.YEAR);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy");
                        Date oldYear = formater.parse(closeO);
                        Date newYear = formater.parse(closeN);
                        String[] except_open = exceptO.split("\n");
                        if(whatNameDayIsToday == 3){
                            return false;
                        }else if(whatMonthIsToday == GregorianCalendar.JANUARY || whatMonthIsToday == GregorianCalendar.DECEMBER){
                            return false;
                        }else{
                            if(oldYear!=null){
                                if((oldYear.getYear()-100) == (whatYearIsToday-2000)){
                                    if(whatMonthIsToday == oldYear.getMonth() && whatDayIsToday > oldYear.getDay()){
                                        open = false;
                                    }
                                }
                            }
                            if(newYear!=null){
                                if((newYear.getYear()-100) == (whatYearIsToday-2000)){
                                    if(whatMonthIsToday == newYear.getMonth() && whatDayIsToday > newYear.getDay()){
                                        open = false;
                                    }
                                }
                            }
                            for(String e : except_open){
                                Date e_op = formater.parse(e);
                                if(e_op!=null){
                                    if(whatMonthIsToday == e_op.getMonth() && whatDayIsToday > e_op.getDay()){
                                        open = true;
                                    }
                                }
                            }
                        }
                        return open;
                    }
                });
    }
}
