package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class info {


    private SimpleIntegerProperty id;
    private SimpleStringProperty url;
    private SimpleIntegerProperty code;
    private SimpleStringProperty server;
    private SimpleStringProperty title;

    public info(Integer id, String url, Integer code, String server, String title) {
        super();
        this.id = new SimpleIntegerProperty(id);
        this.url = new SimpleStringProperty(url);
        this.code = new SimpleIntegerProperty(code);
        this.server = new SimpleStringProperty(server);
        this.title = new SimpleStringProperty(title);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public Integer getCode() {
        return code.get();
    }

    public void setCode(Integer code) {
        this.code.set(code);;
    }

    public String getServer() {
        return server.get();
    }

    public void setServer(String server) {
        this.server.set(server);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }








}