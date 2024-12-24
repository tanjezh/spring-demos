package com.tan.influx;

import com.tan.influx.delete.DeleteService2;
import com.tan.influx.insert.InsertService2;
import com.tan.influx.query.QueryService2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private InsertService2 insertService;
    private QueryService2 queryService;
//    private UpdateService updateService;
    private DeleteService2 deleteService;

    public Application(InsertService2 insertService, QueryService2 queryService,
                       DeleteService2 deleteService) {
        this.insertService = insertService;
        this.queryService = queryService;
//        this.updateService = updateService;
        this.deleteService = deleteService;


//        insert();
        query();
//        update();
//        delete();
    }

    private void insert() {
//        insertService.time();
//        insertService.writeRecord();
        insertService.writePOJO();
    }

    private void query() {
        queryService.query();
    }

//    private void update() {
//        updateService.update();
//    }

    private void delete() {
        deleteService.delete();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
