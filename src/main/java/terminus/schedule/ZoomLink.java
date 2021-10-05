package terminus.schedule;

import java.util.ArrayList;
import terminus.content.Link;
import terminus.content.LinkManager;


public class ZoomLink {

    private ArrayList<Link> schedules;

    private LinkManager linkManager;

    public ZoomLink() {
        linkManager = new LinkManager();
        schedules = new ArrayList<Link>();
    }

    public LinkManager getLinkManager() {
        return linkManager;
    }

    public ArrayList<Link> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Link> schedules) {
        this.schedules = schedules;
    }

    public <T> ArrayList<Link> get(T type) {
        ArrayList<Link> result = new ArrayList<>();
        if(type == Link.class){
            result = this.schedules;
        }else{
            // Error encountered
        }
        return result;
    }
}

